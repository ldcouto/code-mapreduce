package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.conf.Configuration;
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
 * package they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class PackageByFile
{

    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();

        CheckedJobInfo cji = new CheckedJobInfo(conf, "Usage: ByFile <in> <out>");
        String[] otherArgs = HadoopJobControl.checkArguments(args, cji);
        
        // // Create a new Job
        Job job = new Job(conf,
                "find defined packages, and list for each file what package it defines");

        JobConfigurer jc = new JobConfigurer(PackageByFile.class, JavaFileInputFormat.class,
                new Path(otherArgs[0]), new Path(otherArgs[1]));

        MapperConfigurer mc = new MapperConfigurer(PackageByFileMapper.class, Text.class,
                Text.class);

        HadoopJobControl.configureSimpleJob(job, jc, mc, PackageByFileReducer.class);

        // Close the Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}