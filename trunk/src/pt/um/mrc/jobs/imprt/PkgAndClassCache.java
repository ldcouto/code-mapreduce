package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JClassAndPkgInputFormat;

public class PkgAndClassCache implements JobInformable
{
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JClassAndPkgInputFormat.class;
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return PkgAndClassMapper.class;
    }

    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueOutClass()
    {
        return Text.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return PkgAndClassReducer.class;
    }

    public String getUsage()
    {
        return "Usage: PkgAndClassCache <in> <cache>";
    }

    public int getArgCount()
    {
        return 2;
    }
}
