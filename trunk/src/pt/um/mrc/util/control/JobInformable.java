package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

// TODO: Auto-generated Javadoc
/**
 * The Interface JobInformable.
 */
public interface JobInformable
{

    public abstract int getArgCount();

    /**
     * Gets the usage message.
     * 
     * @return the usage message
     */
    public abstract String getUsage();

    /**
     * Gets the mapper class.
     * 
     * @return the mapper class
     */
    public abstract Class<? extends Mapper<?, ?, ?, ?>> getMapperClass();

    /**
     * Gets the reducer class.
     * 
     * @return the reducer class
     */
    public abstract Class<? extends Reducer<?, ?, ?, ?>> getReducerClass();

    /**
     * Gets the mapper output key class.
     * 
     * @return the mapper output key class
     */
    public abstract Class<?> getMapperKeyOutClass();

    /**
     * Gets the mapper output value class.
     * 
     * @return the mapper output value class
     */
    public abstract Class<?> getMapperValueOutClass();

    /**
     * Gets the InputFormatClass.
     * 
     * @return the InputFormatClass
     */
    public abstract Class<? extends InputFormat<?, ?>> getInputFormatClass();
}