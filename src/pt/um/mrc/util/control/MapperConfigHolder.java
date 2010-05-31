package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.Mapper;

/**
 * The Class MapperConfigHolder is an auxiliary class to provide the
 * configuration parameters of a Mapper.
 * 
 * 
 */
public class MapperConfigHolder
{
    private Class<? extends Mapper<?, ?, ?, ?>> mapperClass;

    private Class<?> mapOutKey;

    private Class<?> mapOutValue;

    /**
     * Instantiates a new MapperConfigHolder.
     * 
     * @param mapper
     *            the Mapper class
     * @param mapOutKey
     *            the Map Output Key
     * @param mapOutValue
     *            the Map Output Value
     */
    public MapperConfigHolder(Class<? extends Mapper<?, ?, ?, ?>> mapper, Class<?> mapOutKey, Class<?> mapOutValue)
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
