package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This class contains the configuration for the job that relates packages with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByPackage implements JobInformable
{
    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: McCabeByPackage <in> <out>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByPackageMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperKeyClass()
     */
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperValueClass()
     */
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getInputFormatClass()
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return TextInputFormat.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByPackageReducer.class;
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments from the command line
     * @throws Exception
     *             the exception
     */
    public static void main(String[] args)
    {
        McCabeByPackage me = new McCabeByPackage();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }

    public int getArgCount()
    {
        return 2;
    }
}
