package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates files with their cyclomatic complexity. <br>
 * 
 * It takes two parameters. The input and the output folder. The input folder
 * must contain the calculations computed by the {@link McCabeByClass} job. And
 * the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME-FILENAME CYCLOMATIC_COMPLEXITY
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeByFile extends Text2IntJob implements JobInformable
{
    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: McCabeByFile <in> <out>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByFileMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByFileReducer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getArgCount()
     */
    public int getArgCount()
    {
        return 2;
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments from the command line, the input and the output
     *            folders
     */
    public static void main(String[] args)
    {
        McCabeByFile me = new McCabeByFile();
        System.exit(JobRunner.startJob(args, me));
    }
}