package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;

/**
 * The Class JobConfigHolder is used as an auxiliary class to configure a Hadoop
 * job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JobConfigHolder
{

    private String[] paths;

    /** The class jar. */
    private Class<?> classJar;

    /** The input format. */
    private Class<? extends InputFormat<?, ?>> inputFormat;

    public JobConfigHolder(Class<?> classjar, Class<? extends InputFormat<?, ?>> inputFormat,
            String[] args)
    {
        this.classJar = classjar;
        this.inputFormat = inputFormat;
        this.paths = args;
    }

    /**
     * Gets the intput format.
     * 
     * @return the intput format
     */
    public Class<? extends InputFormat<?, ?>> getIntputFormat()
    {
        return inputFormat;
    }

    /**
     * Gets the class jar.
     * 
     * @return the class jar
     */
    public Class<?> getClassJar()
    {
        return classJar;
    }

    public String[] getPaths()
    {
        return paths;
    }

}
