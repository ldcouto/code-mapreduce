package pt.um.mrc.jobs.volume;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.Text2IntJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This job relates files with their volume. <br>
 * 
 * It takes three parameters. The folder with the calculations returned by the
 * {@link VolumeByClass} job, the input and the output folder. The input folder
 * must contain a set of source files. And the output folder cannot exist. <br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME-FILENAME VOLUME
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeByFile extends Text2IntJob implements JobInformable
{

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: VolumeByFile <classVolumeLoc> <sourceFiles> <output>";
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByFileMapper.class;
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
     * @see pt.um.mrc.util.control.JobInformable#getArgCount()
     */
    @Override
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
        VolumeByFileMisc j1 = new VolumeByFileMisc();
        VolumeByFile j2 = new VolumeByFile();
        String tempFolder = "tmpFile/";

        System.exit(JobRunner.startJob(args, j1, j2, tempFolder));
    }

}