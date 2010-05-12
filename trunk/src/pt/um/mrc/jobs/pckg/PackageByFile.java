package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import pt.um.mrc.util.control.HadoopJobControl;
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
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2)
        {
            System.err.println("Usage: ByFile <in> <out>");
            System.exit(2);
        }

        // // Create a new Job
        Job job = new Job(conf,
                "find defined packages, and list for each file what package it defines");

        HadoopJobControl.configureSimpleJob(job, PackageByFile.class, PackageByFileMapper.class,
                Text.class, Text.class, PackageByFileReducer.class, JavaFileInputFormat.class);
        
        // Define the input and output paths
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        // Close the Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}