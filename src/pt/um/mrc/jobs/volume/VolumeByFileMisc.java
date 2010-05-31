package pt.um.mrc.jobs.volume;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.VolumeMiscJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JFileInputFormat;

/**
 * This is an auxiliary job to the {@link VolumeByClass} job. <br>
 * 
 * This job computes the cyclomatic complexity of all static blocks inside a
 * class with the exception of methods.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeByFileMisc extends VolumeMiscJob implements JobInformable
{

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getInputFormatClass()
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JFileInputFormat.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByFileReducer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: VolumeByFileMisc <sourceFiles> <output>";
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
