package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import pt.um.mrc.util.control.CheckedJobInfo;
import pt.um.mrc.util.control.HadoopJobControl;
import pt.um.mrc.util.control.JobConfigurer;
import pt.um.mrc.util.control.MapperConfigurer;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFile {

	static Configuration conf = new Configuration();
	static FileSystem fs;
	static Path tmp = new Path("tmp/");

	public static void main(String[] args) throws Exception {
		// Initialize some stuff
		fs = FileSystem.get(conf);

		// Check Arguments
		CheckedJobInfo cji = new CheckedJobInfo(conf, "Usage: ImportsByFile <in> <our>");
		String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

		runJob1(otherArgs);

		// Prepare the Cache for the second Job

		for (FileStatus fstatus : fs.listStatus(tmp)) {
			if (!fstatus.isDir()) {
				DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
			}
		}

		runJob2(otherArgs);
	}

	private static void runJob1(String[] otherArgs) throws Exception {

		// Create and Set up the (1st) Job
		Job job1 = new Job(conf, "find project's internal packages");

		JobConfigurer jc1 =
			new JobConfigurer(ImportsByFile.class, JavaFileInputFormat.class, new Path(otherArgs[0]),
				tmp);

		MapperConfigurer mc1 = new MapperConfigurer(FindPackagesMapper.class, Text.class, Text.class);

		HadoopJobControl.configureSimpleJob(job1, jc1, mc1, FindPackagesReducer.class);

		// Run the (1st) Job
		boolean statusJob1 = job1.waitForCompletion(true);

		if (!statusJob1)
			System.exit(1);

	}

	private static void runJob2(String[] otherArgs) throws Exception {
		// Create and set up the (2nd) Job
		Job job2 = new Job(conf, "find packages imported by a file");

		JobConfigurer jc2 =
			new JobConfigurer(ImportsByFile.class, JavaFileInputFormat.class, new Path(otherArgs[0]),
				new Path(otherArgs[1]));

		MapperConfigurer mc2 = new MapperConfigurer(ImportsByFileMapper.class, Text.class, Text.class);

		HadoopJobControl.configureSimpleJob(job2, jc2, mc2, ImportsByFileReducer.class);

		// Run the (2nd) Job
		boolean statusJob2 = job2.waitForCompletion(true);

		// Clean up
		fs.delete(tmp, true);
		System.exit(statusJob2 ? 0 : 1);

	}
}