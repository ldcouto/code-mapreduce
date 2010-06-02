package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;

import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

public abstract class NewVolumeJobsCommon
{
    public int getArgCount()
    {
        return 2;
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }
    
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }
}
