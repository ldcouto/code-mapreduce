package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFile implements JobInformable
{
    private static String cache = "tmpCache/";

    public static void main(String[] args) throws Exception
    {
        PkgAndClassChache job1 = new PkgAndClassChache();
        ImportsByFile job2 = new ImportsByFile();

        int status = JobRunner.runCachedJob(job1, job2, cache, args);
        System.exit(status);
    }

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return ImportsByFileMapper.class;
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
        return ImportsByFileReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: ImportsByFile <cache> <out>";
    }

    @Override
    public int getArgCount()
    {
        return 2;
    }

}