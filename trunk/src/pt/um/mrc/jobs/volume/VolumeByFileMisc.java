package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JFileInputFormat;

public class VolumeByFileMisc implements JobInformable
{

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JFileInputFormat.class;
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
        return VolumeByFileReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: VolumeByFileMisc <sourceFiles> <output>";
    }

    // public static void main(String[] args)
    // {
    // VolumeByFileMisc me = new VolumeByFileMisc();
    // JobRunner.setJob(args, me);
    // JobRunner.runJob();
    // }

    @Override
    public int getArgCount()
    {
        return 2;
    }

}
