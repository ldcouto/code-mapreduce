package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This class contains the configuration for the job that relates classes with
 * their lines of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByClass implements JobInformable
{
    public String getUsage()
    {
        return "Usage: VolumeByClass <in> <out>";
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByClassMapper.class;
    }

    public Class<?> getMapperKeyClass()
    {
        return Text.class;
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
        return VolumeByClassReducer.class;
    }

    public static void main(String[] args) throws Exception
    {
        VolumeByClass me = new VolumeByClass();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}
