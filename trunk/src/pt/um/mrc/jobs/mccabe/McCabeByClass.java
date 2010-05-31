package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates classes with their cyclomatic complexity. <br>
 * 
 * It takes three parameters. The folder with the calculations returned by the
 * {@link McCabeByMethod} job, the input and the output folder. The input folder
 * must contain a set of source files. And the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME-FILENAME-CLASSNAME CYCLOMATIC_COMPLEXITY
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

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getArgCount()
     */
    public int getArgCount()
    {
        return 3;
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments from the command line, the location of the
     *            method calculations, the input and the output
     */
    public static void main(String[] args)
    {
        McCabeByClassMisc j1 = new McCabeByClassMisc();
        McCabeByClass j2 = new McCabeByClass();
        String tempFolder = "tmpClsMcCabe/";

        System.exit(JobRunner.startJob(args, j1, j2, tempFolder));
    }
}