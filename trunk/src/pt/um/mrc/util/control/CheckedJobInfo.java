package pt.um.mrc.util.control;

import org.apache.hadoop.conf.Configuration;

/**
 * The class CheckedJobInfo is where the correct information for job arguments
 * is stored.
 * 
 * It is used as an abstraction to configure a job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class CheckedJobInfo
{
    /** How the Hadoop job should be used. */
    private String usageMessage;

    /** The configuration for the job. */
    private Configuration conf;

    /** The number of arguments. */
    private int argNum;

    /**
     * Instantiates a new CheckedJobInfo.
     * 
     * @param usageMessage
     *            the usage message to be used in case wrong arguments are
     *            passed
     * @param conf
     *            the configuration to be used in the job
     * @param argNum
     *            the valida number of arguments
     */
    public CheckedJobInfo(String usageMessage, Configuration conf, int argNum)
    {
        this.usageMessage = usageMessage;
        this.conf = conf;
        this.argNum = argNum;
    }

    /**
     * Gets the number of valid arguments.
     * 
     * @return the the number of valid arguments
     */
    public int getArgNum()
    {
        return argNum;
    }

    /**
     * Gets the usage message.
     * 
     * @return the usage message
     */
    public String getUsageMessage()
    {
        return usageMessage;
    }

    /**
     * Gets the configuration.
     * 
     * @return the configuration
     */
    public Configuration getConf()
    {
        return conf;
    }

}
