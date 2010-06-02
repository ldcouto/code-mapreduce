package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.NewVolumeJobsCommon;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

public class VolumeByPackage extends NewVolumeJobsCommon implements JobInformable
{
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByPackageMapper.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByPackageReducer.class;
    }

    public String getUsage()
    {
        return "Usage: VolumeByPackage <in> <out>";
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
