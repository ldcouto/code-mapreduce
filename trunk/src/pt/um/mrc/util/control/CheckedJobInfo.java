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

    private int argNum;

    public CheckedJobInfo(String usageMessage, Configuration conf, int argNum)
    {
        super();
        this.usageMessage = usageMessage;
        this.conf = conf;
        this.argNum = argNum;
    }

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
