package pt.um.mrc.util.control;

import org.apache.hadoop.conf.Configuration;

/**
 * The Class CheckedJobInfo is used as an auxiliary abstraction to configure a
 * Hadoop job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class CheckedJobInfo
{

    /** How the Hadoop job should be used. */
    private String usageMessage;

    /** The configuration for the job. */
    private Configuration conf;

    /**
     * Instantiates a new checked job info.
     * 
     * @param configuration
     *            the configuration for the job
     * @param usegeMessage
     *            the use mesage
     */
    public CheckedJobInfo(Configuration conf, String uM)
    {
        this.conf = conf;
        this.usageMessage = uM;
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
