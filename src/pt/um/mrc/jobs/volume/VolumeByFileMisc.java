package pt.um.mrc.jobs.volume;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.VolumeMiscJob;
import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JFileInputFormat;

public class VolumeByFileMisc extends VolumeMiscJob implements JobInformable
{
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JFileInputFormat.class;
    }


    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByFileReducer.class;
    }

    public String getUsage()
    {
        return "Usage: VolumeByFileMisc <sourceFiles> <output>";
    }

    public int getArgCount()
    {
        return 2;
    }
}
