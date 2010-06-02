package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.jobs.volume.VolumeByClassMisc;
import pt.um.mrc.jobs.volume.VolumeByFileMisc;
import pt.um.mrc.jobs.volume.VolumeMiscMapper;

/**
 * This class contains common information between the {@link VolumeByClassMisc}
 * and {@link VolumeByFileMisc} auxiliary jobs.
 *
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
 
public abstract class VolumeMiscJob
{

    /**
     * Gets the defined mapper for the job.
     * 
     * @return the the defined mapper for the job
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeMiscMapper.class;
    }

    /**
     * Gets the class of the output key given by the defined mapper.
     * 
     * @return the class of the output key given by the defined mapper
     */
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
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
}
