package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;

import pt.um.mrc.jobs.mccabe.McCabeByMethod;
import pt.um.mrc.jobs.volume.VolumeByMethod;
import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.iformats.JMethodInputFormat;

/**
 * This class contains common information for the the {@link McCabeByMethod} and
 * {@link VolumeByMethod} jobs.
 */
public abstract class ByMethodJob
{

    /**
     * Gets the class of the output key given by the defined mapper.
     * 
     * @return the class of the output key given by the defined mapper
     */
    public Class<?> getMapperKeyOutClass()
    {
        return MethodID.class;
    }

    /**
     * Gets the class of the output value given by the defined mapper.
     * 
     * @return the class of the output value given by the defined mapper
     */
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    /**
     * Gets the class of the InputFormat used in this job
     * 
     * @return the InputFormat class used in this job
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JMethodInputFormat.class;
    }
}
