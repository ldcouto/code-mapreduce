package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.JMethodInputFormat;

/**
 * This class contains the configuration for the job that relates methods with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByMethod implements JobInformable
{

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: McCabeByMethod <in> <out>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByMethodMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperKeyClass()
     */
    public Class<?> getMapperKeyClass()
    {
        return MethodID.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperValueClass()
     */
    public Class<?> getMapperValueClass()
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
        return JMethodInputFormat.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByMethodReducer.class;
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
        McCabeByMethod me = new McCabeByMethod();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}