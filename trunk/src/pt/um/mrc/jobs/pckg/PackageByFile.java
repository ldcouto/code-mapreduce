package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

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
        
        // Define the Mapper and Reducer classes
        job.setJarByClass(PackageByFile.class);
        job.setMapperClass(PackageByFileMapper.class);
        job.setReducerClass(PackageByFileReducer.class);
        
        // the map output is Text, Text
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        // the reduce output is Text, Text
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        // Set the input format to JavaFileInputFormat
        job.setInputFormatClass(JavaFileInputFormat.class);

        // Define the input and output paths
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        // Close the Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}