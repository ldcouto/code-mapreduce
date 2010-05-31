package pt.um.mrc.util.control;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * The Class HadoopJobControl contains some helper methods to configure an
 * Hadoop job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class HadoopJobControl
{
    protected static Log LOG = LogFactory.getLog(HadoopJobControl.class);

    /**
     * Configures a simple job.
     * 
     * @param job
     *            the Job to be configured
     * @param jc
     *            the JobConfigHolder to use in configuration of the Job
     * @param mc
     *            the mc
     * @param reducer
     *            the reducer
     * 
     *            the exception
     * @throws IOException
     */
    public static void configureSimpleJob(Job job, JobConfigHolder jc, MapperConfigHolder mc, Class<? extends Reducer<?, ?, ?, ?>> reducer)
            throws IOException
    {

        // Configure generic Job stuff
        job.setJarByClass(jc.getClassJar());
        job.setInputFormatClass(jc.getIntputFormat());

        int i;
        for (i = 0; i < jc.getPaths().length - 1; i++)
        {

            FileInputFormat.addInputPath(job, new Path(jc.getPaths()[i]));
        }
        FileOutputFormat.setOutputPath(job, new Path(jc.getPaths()[i]));

        // Configure the Mapper Stuff
        job.setMapperClass(mc.getMapperClass());
        job.setMapOutputKeyClass(mc.getMapOutKey());
        job.setMapOutputValueClass(mc.getMapOutValue());

        // Configure the Reducer Class
        job.setReducerClass(reducer);

    }

    /**
     * Checks arguments.
     * 
     * @param args
     *            the argument array to be checked
     * @param cji
     *            the CheckedJobInfo with the configuration for the job.
     * @return the string[]
     */
    public static String[] checkArguments(String[] args, CheckedJobInfo cji)
    {
        String[] otherArgs = new GenericOptionsParser(cji.getConf(), args).getRemainingArgs();

        if (otherArgs.length != cji.getArgNum())
        {
            LOG.fatal(cji.getUsageMessage());
            System.exit(2);
        }

        return otherArgs;
    }
}
