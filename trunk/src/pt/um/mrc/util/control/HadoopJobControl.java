package pt.um.mrc.util.control;

import java.io.IOException;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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

        // Configure generic Job stuff
        job.setJarByClass(jc.getClassJar());
        job.setInputFormatClass(jc.getIntputFormat());

        FileInputFormat.setInputPaths(job, jc.getInputPath());
        FileOutputFormat.setOutputPath(job, jc.getOutputPath());

        //TODO uncomment me when the loop works
//        configureInputs(job, jc.getInputPath());

        // Configure the Mapper Stuff
        job.setMapperClass(mc.getMapperClass());
        job.setMapOutputKeyClass(mc.getMapOutKey());
        job.setMapOutputValueClass(mc.getMapOutValue());

        // Configure the Reducer Class
        job.setReducerClass(reducer);

    }

    //FIXME This doesn't work!
//    private static void configureInputs(Job job, Path inputPath)
//            throws IOException
//    {
//        FileSystem fs = FileSystem.get(job.getConfiguration());
//        
//        for (FileStatus fstatus : fs.listStatus(inputPath))
//        {
//            if (fstatus.isDir());
//            {
//                FileInputFormat.addInputPath(job, fstatus.getPath());
//                configureInputs(job, fstatus.getPath());
//            }
//        }
//    }

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
