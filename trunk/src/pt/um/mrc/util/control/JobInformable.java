package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * The Interface JobInformable is implemented by all job configuration classes.
 * 
 * The classes that implement this interface provide all the information
 * required to set up and run a job, which is in turn used by {@link JobRunner}
 * to set up and run the job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public interface JobInformable
{

    /**
     * Gets the valid number of arguments for the job.
     * 
     * @return the valid number of arguments
     */
    public abstract int getArgCount();

    /**
     * Gets the defined usage message for the job.
     * 
     * @return the defined usage message for the job
     */
    public abstract String getUsage();

    /**
     * Gets the defined mapper class for the job.
     * 
     * @return the the defined mapper class for the job
     */
    public abstract Class<? extends Mapper<?, ?, ?, ?>> getMapperClass();

    /**
     * Gets the defined reducer class for the job.
     * 
     * @return the the defined reducer class for the job
     */
    public abstract Class<? extends Reducer<?, ?, ?, ?>> getReducerClass();

    /**
     * Gets the class of the keys outputted by the mapper. Needed to ensure that
     * the job is properly typed.
     * 
     * @return the class of the keys outputted by the mapper
     */
    public abstract Class<?> getMapperKeyOutClass();

    /**
     * Gets the class of the values outputted by the mapper. Needed to ensure
     * that the job is properly typed.
     * 
     * @return the class of the values outputted by the mapper
     */
    public abstract Class<?> getMapperValueOutClass();

    /**
     * Gets the class of the {@link InputFormat} of the key/value pairs used in this
     * job.
     * 
     * @return the InputFormat class used in this job
     */
    public abstract Class<? extends InputFormat<?, ?>> getInputFormatClass();
}