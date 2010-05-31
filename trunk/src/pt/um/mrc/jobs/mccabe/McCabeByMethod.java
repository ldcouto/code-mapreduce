package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.ByMethodJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates methods with their cyclomatic complexity. <br>
 * 
 * It takes two parameters. The input and the output folder. The input folder
 * must contain a set of source files. And the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME-FILENAME-CLASSNAME-METHODNAME[ARGS] CYCLOMATIC_COMPLEXITY
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeByMethod extends ByMethodJob implements JobInformable
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
     *            the arguments from the command line, the input and the output
     *            folders
     */
    public static void main(String[] args)
    {
        McCabeByMethod me = new McCabeByMethod();
        System.exit(JobRunner.startJob(args, me));
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
}