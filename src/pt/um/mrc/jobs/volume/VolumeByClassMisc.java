package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.JClassInputFormat;

public class VolumeByClassMisc implements JobInformable
{

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JClassInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeMiscMapper.class;
    }

    @Override
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    @Override
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    @Override
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByClassReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: VolumeByClassMisc <in> <out>";
    }

    // public static void main(String[] args)
    // {
    // VolumeByClassMisc me = new VolumeByClassMisc();
    // JobRunner.setJob(args, me);
    // JobRunner.runJob();
    // }

    @Override
    public int getArgCount()
    {
        return 2;
    }
}
