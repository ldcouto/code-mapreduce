package pt.um.mrc.util.control;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class HadoopJobControl
{
    public static void configureSimpleJob(Job job, JobConfigurer jc, MapperConfigurer mc,
            Class<? extends Reducer<?, ?, ?, ?>> reducer) throws Exception
    {

        FileSystem fs = FileSystem.get(job.getConfiguration());

        // Configure generic Job stuff
        job.setJarByClass(jc.getClassJar());
        job.setInputFormatClass(jc.getIntputFormat());

        FileInputFormat.setInputPaths(job, jc.getInputPath());
        FileOutputFormat.setOutputPath(job, jc.getOutputPath());

        // TODO NOT CHECKED YET!
//        for (FileStatus fstatus : fs.listStatus(jc.getInputPath()))
//        {
//            if (fstatus.isDir())
//            {
//                FileInputFormat.addInputPath(job, fstatus.getPath());
//            }
//        }

        // Configure the Mapper Stuff
        job.setMapperClass(mc.getMapperClass());
        job.setMapOutputKeyClass(mc.getMapOutKey());
        job.setMapOutputValueClass(mc.getMapOutValue());

        // Configure the Reducer Class
        job.setReducerClass(reducer);

    }

    public static String[] checkArguments(String[] args, CheckedJobInfo cji)
    {
        String[] otherArgs = new GenericOptionsParser(cji.getConf(), args).getRemainingArgs();

        if (otherArgs.length != 2)
        {
            // TODO replace with log4j
            System.err.println(cji.getUsageMessage());
            System.exit(2);
        }

        return otherArgs;
    }
}
