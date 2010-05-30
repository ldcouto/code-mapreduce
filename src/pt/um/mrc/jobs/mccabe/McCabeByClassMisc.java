package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JMcClassInputFormat;

public class McCabeByClassMisc implements JobInformable
{
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JMcClassInputFormat.class;
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeMiscMapper.class;
    }

    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByClassReducer.class;
    }

    public String getUsage()
    {
        return "Usage: McCabeByClassMisc <in> <out>";
    }

    public int getArgCount()
    {
        return 2;
    }
}
