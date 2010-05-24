package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * package they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class PackageByFile implements JobInformable
{
    public String getUsage()
    {
        return "Usage: PackageByFile <in> <out>";
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return PackageByFileMapper.class;
    }

    public Class<?> getMapperKeyClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueClass()
    {
        return Text.class;
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return PackageByFileReducer.class;
    }

    public static void main(String[] args) throws Exception
    {
        PackageByFile me = new PackageByFile();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}