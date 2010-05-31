package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFile implements JobInformable
{
    public static void main(String[] args)
    {
        PkgAndClassCache job1 = new PkgAndClassCache();
        ImportsByFile job2 = new ImportsByFile();
        Path cache = new Path("tmpCache/");
        
        System.exit(JobRunner.startCachedJob(args, job1, job2, cache));
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return ImportsByFileMapper.class;
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
        return ImportsByFileReducer.class;
    }

    public String getUsage()
    {
        return "Usage: ImportsByFile <in> <out>";
    }

    public int getArgCount()
    {
        return 2;
    }
}