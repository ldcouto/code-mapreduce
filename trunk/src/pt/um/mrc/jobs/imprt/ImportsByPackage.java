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
 * This class contains the configuration for the job that relates packages with
 * the packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackage
{

    static Configuration conf = new Configuration();
    static FileSystem fs;
    static Path tmp = new Path("tmp/");

    public static void main(String[] args) throws Exception
    {

        // Initialize FS
        fs = FileSystem.get(conf);

        // Check Args
        CheckedJobInfo cji = new CheckedJobInfo(conf, "Usage: ImportsByPackage <in> <our>");
        String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

        runJob1(otherArgs);

        // Set up the Cache
        for (FileStatus fstatus : fs.listStatus(tmp))
        {
            if (!fstatus.isDir())
            {
                DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
            }
        }

        runJob2(otherArgs);

    }

    private static void runJob1(String[] otherArgs) throws Exception
    {
        Job job1 = new Job(conf, "find project's internal packages");

        JobConfigurer jc1 = new JobConfigurer(ImportsByPackage.class, JavaFileInputFormat.class,
                new Path(otherArgs[0]), tmp);

        MapperConfigurer mc1 = new MapperConfigurer(FindPackagesMapper.class, Text.class,
                Text.class);

        HadoopJobControl.configureSimpleJob(job1, jc1, mc1, FindPackagesReducer.class);

        boolean statusJob1 = job1.waitForCompletion(true);

        if (!statusJob1)
            System.exit(1);
    }

    private static void runJob2(String[] otherArgs) throws Exception
    {
        Job job2 = new Job(conf, "find packages imported by a file");

        JobConfigurer jc2 = new JobConfigurer(ImportsByPackage.class, JavaFileInputFormat.class,
                new Path(otherArgs[0]), new Path(otherArgs[1]));

        MapperConfigurer mc2 = new MapperConfigurer(ImportsByPackageMapper.class, Text.class,
                Text.class);

        HadoopJobControl.configureSimpleJob(job2, jc2, mc2, ImportsByPackageReducer.class);

        boolean statusJob2 = job2.waitForCompletion(true);

        fs.delete(tmp, true);

        System.exit(statusJob2 ? 0 : 1);
    }
}