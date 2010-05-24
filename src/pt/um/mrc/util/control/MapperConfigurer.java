package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.Mapper;

// TODO: Auto-generated Javadoc
/**
 * The Class MapperConfigurer is an auxiliary class to provide the configuration
 * parameters of a Mapper.
 */
public class MapperConfigurer
{

    /** The Mapper class. */
    private Class<? extends Mapper<?, ?, ?, ?>> mapperClass;

    /** The Map Output Key class. */
    private Class<?> mapOutKey;

    /** The Map Output Value class. */
    private Class<?> mapOutValue;

    /**
     * Instantiates a new MapperConfigurer.
     * 
     * @param mapper
     *            the Mapper class
     * @param mapOutKey
     *            the Map Output Key
     * @param mapOutValue
     *            the Map Output Value
     */
    public MapperConfigurer(Class<? extends Mapper<?, ?, ?, ?>> mapper, Class<?> mapOutKey,
            Class<?> mapOutValue)
    {
        this.mapperClass = mapper;
        this.mapOutKey = mapOutKey;
        this.mapOutValue = mapOutValue;
    }

    /**
     * Gets the Mapper class.
     * 
     * @return the Mapper class
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return mapperClass;
    }

    /**
     * Gets the Map Output Key.
     * 
     * @return the Map Output Key
     */
    public Class<?> getMapOutKey()
    {
        return mapOutKey;
    }

    /**
     * Gets the Map Output Value.
     * 
     * @return the Map Output Value
     */
    public Class<?> getMapOutValue()
    {
        return mapOutValue;
    }
}
