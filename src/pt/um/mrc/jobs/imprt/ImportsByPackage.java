package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import pt.um.mrc.util.control.HadoopJobControl;
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
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path tmp = new Path("tmp/");

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2)
        {
            System.err.println("Usage: ImportsByPackage <in> <out>");
            System.exit(2);
        }

        Job job1 = new Job(conf, "find project's internal packages");

        HadoopJobControl.configureSimpleJob(job1, ImportsByPackage.class, FindPackagesMapper.class,
                Text.class, Text.class, FindPackagesReducer.class, JavaFileInputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job1, tmp);

        boolean statusJob1 = job1.waitForCompletion(true);

        if (!statusJob1)
            System.exit(1);

        for (FileStatus fstatus : fs.listStatus(tmp))
        {
            if (!fstatus.isDir())
            {
                DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
            }
        }

        Job job2 = new Job(conf, "find packages imported by a file");

        HadoopJobControl.configureSimpleJob(job2, ImportsByPackage.class,
                ImportsByPackageMapper.class, Text.class, Text.class,
                ImportsByPackageReducer.class, JavaFileInputFormat.class);

        FileInputFormat.addInputPath(job2, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job2, new Path(otherArgs[1]));

        boolean statusJob2 = job2.waitForCompletion(true);

        fs.delete(tmp, true);

        System.exit(statusJob2 ? 0 : 1);
    }
}