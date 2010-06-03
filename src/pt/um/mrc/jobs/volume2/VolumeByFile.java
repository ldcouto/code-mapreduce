package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.NewVolumeJobsCommon;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

// TODO: Auto-generated Javadoc
/**
 * The Class VolumeByFile.
 */
public class VolumeByFile extends NewVolumeJobsCommon implements JobInformable
{
    
    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByFileMapper.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByFileReducer.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: VolumeByFile <in> <out>";
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
        VolumeByFile me = new VolumeByFile();
        System.exit(JobRunner.startJob(args, me));
    }

}
