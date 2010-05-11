package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public abstract class HadoopJobControl
{
    // TODO: I am still unsure if this will be needed.
    public static void configureSimpleJob(Job job, Class<?> classJar,
            Class<? extends Mapper> mapper, Class<?> mapOutKey, Class<?> mapOutValue,
            Class<? extends Reducer> reducer, Class<? extends InputFormat> inputFormat)
            throws Exception
    {
        job.setJarByClass(classJar);
        job.setMapperClass(mapper);
        job.setReducerClass(reducer);

        job.setMapOutputKeyClass(mapOutKey);
        job.setMapOutputValueClass(mapOutValue);

        job.setInputFormatClass(inputFormat);
    }
}
