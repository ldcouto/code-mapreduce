package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates packages with their cyclomatic complexity. <br>
 * 
 * It takes two parameters. The input and the output folder. The input folder
 * must contain the calculations computed by the {@link McCabeByFile} job. And
 * the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME CYCLOMATIC_COMPLEXITY
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeByPackage extends Text2IntJob implements JobInformable
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
     *            the arguments from the command line, the input and the output
     *            folders
     */
    public static void main(String[] args)
    {
        McCabeByPackage me = new McCabeByPackage();
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
