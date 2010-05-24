package pt.um.mrc.util.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
     *            the JobConfigurer to use in configuration of the Job
     * @param mc
     *            the mc
     * @param reducer
     *            the reducer
     * @throws Exception 
     * @throws Exception
     *             the exception
     */
    public static void configureSimpleJob(Job job, JobConfigurer jc, MapperConfigurer mc,
            Class<? extends Reducer<?, ?, ?, ?>> reducer) throws Exception
    {

        // Configure generic Job stuff
        job.setJarByClass(jc.getClassJar());
        job.setInputFormatClass(jc.getIntputFormat());

        FileInputFormat.setInputPaths(job, jc.getInputPath());
        FileOutputFormat.setOutputPath(job, jc.getOutputPath());

        // TODO uncomment me when the loop works
        // configureInputs(job, jc.getInputPath());

        // Configure the Mapper Stuff
        job.setMapperClass(mc.getMapperClass());
        job.setMapOutputKeyClass(mc.getMapOutKey());
        job.setMapOutputValueClass(mc.getMapOutValue());

        // Configure the Reducer Class
        job.setReducerClass(reducer);

    }

    // FIXME This doesn't work!
    // private static void configureInputs(Job job, Path inputPath)
    // throws IOException
    // {
    // FileSystem fs = FileSystem.get(job.getConfiguration());
    //        
    // for (FileStatus fstatus : fs.listStatus(inputPath))
    // {
    // if (fstatus.isDir());
    // {
    // FileInputFormat.addInputPath(job, fstatus.getPath());
    // configureInputs(job, fstatus.getPath());
    // }
    // }
    // }

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

        if (otherArgs.length != 2)
        {
            LOG.fatal(cji.getUsageMessage());
            System.exit(2);
        }

        return otherArgs;
    }
}
