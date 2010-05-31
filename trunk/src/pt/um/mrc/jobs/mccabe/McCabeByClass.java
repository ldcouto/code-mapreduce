package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This class contains the configuration for the job that relates classes with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeByClass extends Text2IntJob implements JobInformable
{

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: McCabeByClass <methodloc> <in> <out>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByClassMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByClassReducer.class;
    }

    @Override
    public int getArgCount()
    {
        return 3;
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments from the command line
     * @throws Exception
     *             the exception
     */
    public static void main(String[] args) throws Exception
    {
        McCabeByClassMisc j1 = new McCabeByClassMisc();
        McCabeByClass j2 = new McCabeByClass();
        String tempFolder = "tmpClsMcCabe/";
        
        System.exit(JobRunner.startJob(args, j1, j2, tempFolder));
    }
}