package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates packages with
 * the packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackage implements JobInformable
{
    private static Path cache = new Path("tmpCacheIBP/");

    public static void main(String[] args) throws Exception
    {
        PkgAndClassCache job1 = new PkgAndClassCache();
        ImportsByPackage job2 = new ImportsByPackage();

        int status = JobRunner.runCachedJob(job1, job2, cache, args);
        System.exit(status);

    }

    @Override
    public int getArgCount()
    {
        return 2;
    }

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return ImportsByPackageMapper.class;
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
        return ImportsByPackageReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: ImportsByPackage <cache> <out>";
    }
}