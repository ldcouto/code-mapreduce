package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.OutputFormat;

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

    private Class<? extends OutputFormat<?, ?>> outputFormat;
    
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
    public JobConfigHolder(Class<?> classjar, Class<? extends InputFormat<?, ?>> inputFormat, Class<? extends OutputFormat<?, ?>> outputFormat, String[] args)
    {
        this.classJar = classjar;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.paths = args;
    }

    
	/**
     * Gets the defined OutpurFormat class.
     * 
     * @return the OutputFormat
     */
	public Class<? extends OutputFormat<?, ?>> getOutputFormat() {
		return outputFormat;
	}

	/**
     * Gets the defined IntputFormat class.
     * 
     * @return the IntputFormat
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
