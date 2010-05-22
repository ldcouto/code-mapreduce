package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.CheckedJobInfo;
import pt.um.mrc.util.control.HadoopJobControl;
import pt.um.mrc.util.control.JobConfigurer;
import pt.um.mrc.util.control.MapperConfigurer;

/**
 * This class contains the configuration for the job that relates classes with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByClass
{
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
            
        CheckedJobInfo cji = new CheckedJobInfo(conf, "Usage: VolumeByClass <in> <out>");
        String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

        // // Create a new Job
        Job job = new Job(conf, "compute the LoC volume for each class");

        JobConfigurer jc = new JobConfigurer(McCabeByClass.class, TextInputFormat.class, new Path(
                otherArgs[0]), new Path(otherArgs[1]));

        MapperConfigurer mc = new MapperConfigurer(McCabeByClassMapper.class, Text.class,
                IntWritable.class);

        HadoopJobControl.configureSimpleJob(job, jc, mc, McCabeByClassReducer.class);

        // Close the Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}