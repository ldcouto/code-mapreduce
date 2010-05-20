package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

import pt.um.mrc.util.control.HadoopJobControl;
import pt.um.mrc.util.control.JobConfigurer;
import pt.um.mrc.util.control.MapperConfigurer;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByFile {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: McCabeByFile <in> <out>");
			System.exit(2);
		}

		// // Create a new Job
		Job job = new Job(conf, "find the McCabe number for each file");

		JobConfigurer jc =
			new JobConfigurer(McCabeByFile.class, JavaFileInputFormat.class,
				new Path(otherArgs[0]), new Path(otherArgs[1]));

		MapperConfigurer mc =
			new MapperConfigurer(McCabeByFileMapper.class, Text.class, IntWritable.class);

		HadoopJobControl.configureSimpleJob(job, jc, mc, McCabeByFileReducer.class);
		
		// Close the Job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}