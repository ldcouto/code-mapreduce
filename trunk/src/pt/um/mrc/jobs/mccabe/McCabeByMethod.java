package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.JMethodInputFormat;

/**
 * This class contains the configuration for the job that relates methods with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByMethod implements JobInformable
{
    public String getUsage()
    {
        return "Usage: McCabeByMethod <in> <out>";
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByMethodMapper.class;
    }

    public Class<?> getMapperKeyClass()
    {
        return MethodID.class;
    }

    public Class<?> getMapperValueClass()
    {
        return IntWritable.class;
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JMethodInputFormat.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByMethodReducer.class;
    }

    public static void main(String[] args) throws Exception
    {
        McCabeByMethod me = new McCabeByMethod();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}