package pt.um.mrc.util.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
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

    public static Configuration getConf()
    {
        return conf;
    }

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
        cji = new CheckedJobInfo(ji.getUsage(), conf, ji.getArgCount());

        String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

        mc = new MapperConfigurer(ji.getMapperClass(), ji.getMapperKeyClass(), ji
                .getMapperValueClass());

        jc = new JobConfigurer(ji.getClass(), ji.getInputFormatClass(), otherArgs);

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
    public static int runJob()
    {
        int r = 2;

        try
        {
            r = job.waitForCompletion(true) ? 0 : 1;
        } catch (Exception e)
        {
            LOG.fatal(e.getMessage());
            System.exit(2);
        }

        return r;
    }

    public static int runDoubleJob(JobInformable job1, JobInformable job2, String tempFolder,
            String[] args)
    {
        String[] argsJob1 = { args[1], tempFolder };
        JobRunner.setJob(argsJob1, job1);

        int status = JobRunner.runJob();
        if (status != 0)
            return status;

        String[] argsJob2 = { args[0], tempFolder, args[2] };
        JobRunner.setJob(argsJob2, job2);

        status = JobRunner.runJob();

        return status;
    }

    public static int runCachedJob(JobInformable job1, JobInformable job2, String cacheFolder,
            String[] args) throws Exception
    {
        String[] j1Args = args;
        j1Args[0] = cacheFolder;

        JobRunner.setJob(j1Args, job1);
        int status = JobRunner.runJob();
        if (status != 0)
            return status;

        // Prepare the Cache for the second Job
        JobRunner.configureDistCache(new Path(cacheFolder));

        String[] j2Args = args;
        j2Args[1] = cacheFolder;

        JobRunner.setJob(j2Args, job2);
        status = JobRunner.runJob();
        FileSystem.get(JobRunner.getConf()).delete(new Path(cacheFolder), true);

        return status;
    }

    public static void configureDistCache(Path path) throws Exception
    {
        for (FileStatus fstatus : FileSystem.get(conf).listStatus(path))
        {
            if (!fstatus.isDir())
            {
                DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
            }
        }
    }

}
