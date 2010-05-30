package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.io.iformats.JClassInputFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class VolumeByClassMisc.
 */
public class VolumeByClassMisc implements JobInformable
{

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getInputFormatClass()
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JClassInputFormat.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeMiscMapper.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperKeyOutClass()
     */
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getMapperValueOutClass()
     */
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByClassReducer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: VolumeByClassMisc <in> <out>";
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
