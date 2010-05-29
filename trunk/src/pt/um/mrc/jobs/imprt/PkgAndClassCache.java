package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.JClassAndPkgInputFormat;

public class PkgAndClassCache implements JobInformable
{
    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JClassAndPkgInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return PkgAndClassMapper.class;
    }

    @Override
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    @Override
    public Class<?> getMapperValueOutClass()
    {
        return Text.class;
    }

    @Override
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return PkgAndClassReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: PkgAndClassCache <in> <cache>";
    }

    @Override
    public int getArgCount()
    {
        return 2;
    }
    
    // public static void main(String[] args) {
    // PkgAndClassCache me = new PkgAndClassCache();
    // JobRunner.setJob(args, me);
    // JobRunner.runJob();
    // }
}
