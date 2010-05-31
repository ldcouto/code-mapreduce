package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;

/**
 * The class JobConfigHolder holds configuration information of a given job.
 * 
 * It is used as an auxiliary to abstract the configuration of a job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JobConfigHolder
{

    private String[] paths;

    private Class<?> classJar;

    private Class<? extends InputFormat<?, ?>> inputFormat;

    /**
     * Instantiates a new JobConfigHolder.
     * 
     * @param classjar
     *            the class where the job is defined
     * @param inputFormat
     *            the {@link InputFormat} to be used
     * @param args
     *            the input/output paths to be used
     */
    public JobConfigHolder(Class<?> classjar, Class<? extends InputFormat<?, ?>> inputFormat, String[] args)
    {
        this.classJar = classjar;
        this.inputFormat = inputFormat;
        this.paths = args;
    }

    /**
     * Gets the defined IntputFormat class.
     * 
     * @return the IntputIormat
     */
    public Class<? extends InputFormat<?, ?>> getIntputFormat()
    {
        return inputFormat;
    }

    /**
     * Gets the class where the job is defined.
     * 
     * @return the class where the job is defined
     */
    public Class<?> getClassJar()
    {
        return classJar;
    }

    /**
     * Gets the input/output paths to be used.
     * 
     * @return the input/output paths to be used
     */
    public String[] getPaths()
    {
        return paths;
    }
}
