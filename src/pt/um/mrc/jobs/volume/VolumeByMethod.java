package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.datatypes.MethodID;

/**
 * This class contains the configuration for the job that relates methods with
 * their lines of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByMethod implements JobInformable
{

    public String getUsage()
    {
        return "Usage: VolumeByMethod <in> <out>";
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByMethodMapper.class;
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
        return TextInputFormat.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByMethodReducer.class;
    }

    public static void main(String[] args) throws Exception
    {
        VolumeByMethod me = new VolumeByMethod();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}