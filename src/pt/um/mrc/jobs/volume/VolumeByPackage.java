package pt.um.mrc.jobs.volume;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates packages with their volume. <br>
 * 
 * It takes two parameters. The input and the output folder. The input folder
 * must contain the calculations computed by the {@link VolumeByFile} job. And
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
public class VolumeByPackage extends Text2IntJob implements JobInformable
{
    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: VolumeByPackage <in> <out>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByPackageMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByPackageReducer.class;
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
        VolumeByPackage me = new VolumeByPackage();
        System.exit(JobRunner.startJob(args, me));
    }
}