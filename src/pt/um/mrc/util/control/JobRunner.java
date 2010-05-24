package pt.um.mrc.util.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

/**
 * The Class JobRunner is an auxiliary class to configure and run a Hadoop Job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JobRunner
{
    protected static Log LOG = LogFactory.getLog(JobRunner.class);
    
    /** The configuration. */
    protected static Configuration conf;

    /** The auxiliary CheckedJobInfo. */
    protected static CheckedJobInfo cji;

    /** The auxiliary JobConfigurer. */
    protected static JobConfigurer jc;

    /** The auxiliary MapperConfigurer. */
    protected static MapperConfigurer mc;

    /** The Job to configure. */
    protected static Job job;

    /**
     * Sets up a job.
     * 
     * @param args
     *            the arguments from the command line
     * @param ji
     *            the JobInformaer for the job to configure
     * @throws Exception 
     * @throws Exception
     *             the exception
     */
    public static void setJob(String[] args, JobInformable ji)
    {
        conf = new Configuration();
        cji = new CheckedJobInfo(conf, ji.getUsage());

        String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

        mc = new MapperConfigurer(ji.getMapperClass(), ji.getMapperKeyClass(), ji
                .getMapperValueClass());

        jc = new JobConfigurer(ji.getClass(), ji.getInputFormatClass(), new Path(otherArgs[0]),
                new Path(otherArgs[1]));

        try
        {
            job = new Job(conf);
            HadoopJobControl.configureSimpleJob(job, jc, mc, ji.getReducerClass());

        } catch (Exception e)
        {
            LOG.fatal(e.getMessage());
            System.exit(2);
        }
    }

    /**
     * Run job.
     * 
     * @throws Exception
     *             the exception
     */
    public static void runJob()
    {
        try{
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            LOG.fatal(e.getMessage());
            System.exit(2);
        }
    }

}
