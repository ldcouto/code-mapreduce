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
 * This job relates packages with the classes they import. <br>
 * 
 * It takes two parameters. The input and the output folder. The input folder
 * must contain a set of source files. And the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br><br>
 * 
 * PACKAGENAME { LIST_OF_IMPORTED_CLASSES}
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackage implements JobInformable
{

    /**
     * The main method.
     * 
     * @param args
     *            the command line arguments, the input and out put folders
     */
    public static void main(String[] args)
    {
        PkgAndClassCache job1 = new PkgAndClassCache();
        ImportsByPackage job2 = new ImportsByPackage();
        Path cache = new Path("tmpCacheIBP/");

        System.exit(JobRunner.startCachedJob(args, job1, job2, cache));
    }


    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }


    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return ImportsByPackageMapper.class;
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
        return ImportsByPackageReducer.class;
    }

    public String getUsage()
    {
        return "Usage: ImportsByPackage <in> <out>";
    }

    public int getArgCount()
    {
        return 2;
    }
}