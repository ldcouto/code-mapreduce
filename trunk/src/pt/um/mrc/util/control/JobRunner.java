package pt.um.mrc.util.control;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

import pt.um.mrc.jobs.allmetrics.JobInformableTextFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class JobRunner is an auxiliary class with methods to configure and run a
 * Hadoop Job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JobRunner {

	/** The LOG. */
	private static Log LOG = LogFactory.getLog(JobRunner.class);

	/** The configuration. */
	protected static Configuration conf;

	/** The auxiliary CheckedJobInfo. */
	protected static CheckedJobInfo cji;

	/** The auxiliary JobConfigHolder. */
	protected static JobConfigHolder jc;

	/** The auxiliary MapperConfigHolder. */
	protected static MapperConfigHolder mc;

	/** The Job to configure. */
	protected static Job job;

	/**
	 * Gets the conf.
	 * 
	 * @return the conf
	 */
	public static Configuration getConf() {
		return conf;
	}

	/**
	 * Sets up a job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param ji
	 *            the JobInformaer of the job to configure
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void setJob(String[] args, JobInformable ji) throws IOException {
		conf = new Configuration();
		setJob(args, ji, conf);
	}

	/**
	 * Sets up a job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param ji
	 *            the JobInformaer of the job to configure
	 * @param conf
	 *            the configuration to use
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void setJob(String[] args, JobInformable ji, Configuration conf)
			throws IOException {
		cji = new CheckedJobInfo(ji.getUsage(), conf, ji.getArgCount());

		String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

		mc =
				new MapperConfigHolder(ji.getMapperClass(), ji.getMapperKeyOutClass(), ji
						.getMapperValueOutClass());
		if (ji instanceof JobInformableTextFormat){
			jc = new JobConfigHolder(ji.getClass(), ji.getInputFormatClass(), ((JobInformableTextFormat) ji).getOutputFormatClass(), otherArgs);
		}
		else
			jc = new JobConfigHolder(ji.getClass(), ji.getInputFormatClass(), null, otherArgs);

		job = new Job(conf);
		HadoopJobControl.configureSimpleJob(job, jc, mc, ji.getReducerClass());
	}

	/**
	 * Run job.
	 * 
	 * @return the status of the job that was executed
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static int runJob() throws IOException, InterruptedException, ClassNotFoundException {
		int r = 2;

		r = job.waitForCompletion(true) ? 0 : 1;

		return r;
	}

	/**
	 * Run two jobs in a chain.
	 * 
	 * @param job1
	 *            the first job to run
	 * @param job2
	 *            the second job to run
	 * @param tempFolder
	 *            the temporary folder to pass information from the first job to
	 *            the second job
	 * @param args
	 *            the arguments from the command line
	 * @return the status of the job that was executed
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static int runDoubleJob(JobInformable job1, JobInformable job2, String tempFolder,
			String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		String[] argsJob1 = { args[1], tempFolder };
		JobRunner.setJob(argsJob1, job1);

		int status = JobRunner.runJob();
		if (status != 0)
			return status;

		String[] argsJob2 = { args[0], tempFolder, args[2] };
		JobRunner.setJob(argsJob2, job2);

		status = JobRunner.runJob();
		FileSystem.get(JobRunner.getConf()).delete(new Path(tempFolder), true);

		return status;
	}

	/**
	 * Runs two jobs in a row, used when the second job needs information
	 * returned by the first job.
	 * 
	 * @param job1
	 *            the first job to run
	 * @param job2
	 *            the second job to run
	 * @param cacheFolder
	 *            the temporary cache folder
	 * @param args
	 *            the arguments from the command line
	 * @return the status of the job that was executed
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public static int runCachedJob(JobInformable job1, JobInformable job2, Path cacheFolder,
			String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		String[] j1Args = args.clone();
		j1Args[1] = cacheFolder.getName();

		JobRunner.setJob(j1Args, job1);
		int status = JobRunner.runJob();
		if (status != 0)
			return status;

		// Prepare the Cache for the second Job
		JobRunner.configureDistCache(cacheFolder);

		String[] j2Args = args.clone();

		JobRunner.setJob(j2Args, job2, JobRunner.getConf());
		status = JobRunner.runJob();
		FileSystem.get(JobRunner.getConf()).delete(cacheFolder, true);

		return status;
	}

	public static int runCachedJobTF2(JobInformable job1, JobInformable job2, Path cacheFolder,
			String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		String[] j1Args = args.clone();
		j1Args[1] = cacheFolder.getName();

		JobRunner.setJob(j1Args, job1);
		int status = JobRunner.runJob();
		if (status != 0)
			return status;

		// Prepare the Cache for the second Job
		JobRunner.configureDistCache(cacheFolder);

		String[] j2Args = args.clone();

		JobRunner.setJob(j2Args, job2, JobRunner.getConf());
		status = JobRunner.runJob();
		FileSystem.get(JobRunner.getConf()).delete(cacheFolder, true);

		return status;
	}

	/**
	 * Configure the distributed cache.
	 * 
	 * @param path
	 *            the path to the where the distributed cache is located
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void configureDistCache(Path path) throws IOException {
		for (FileStatus fstatus : FileSystem.get(conf).listStatus(path)) {
			if (!fstatus.isDir()) {
				DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
			}
		}
	}

	/**
	 * Start a simple job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param me
	 *            the JobInformable of the job to run.
	 * @return the status of the just executed job
	 */
	public static int startJob(String[] args, JobInformable me) {
		return catcher(args, me, null, null, null);
	}

	/**
	 * Start double job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param j1
	 *            the first job to run
	 * @param j2
	 *            the the second job to run
	 * @param tempFolder
	 *            the temporary folder
	 * @return the status of the job that was just executed
	 */
	public static int startJob(String[] args, JobInformable j1, JobInformable j2, String tempFolder) {
		return catcher(args, j1, j2, null, tempFolder);
	}

	/**
	 * Start cached job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param j1
	 *            the first job to run
	 * @param j2
	 *            the the second job to run
	 * @param cache
	 *            the path to use as the location of the cache
	 * @return the status of the job that was just executed
	 */
	public static int startCachedJob(String[] args, JobInformable job1, JobInformable job2,
			Path cache) {
		return catcher(args, job1, job2, cache, null);
	}
	private static int catcher(String[] args, JobInformable job1, JobInformable job2, Path cache,
			String folder) {

		int status = -1;
		try {
				if (job2 == null) {
					JobRunner.setJob(args, job1);
					status = JobRunner.runJob();
				} else if (cache == null)
					status = JobRunner.runDoubleJob(job1, job2, folder, args);
				else if (folder == null)
					status = JobRunner.runCachedJob(job1, job2, cache, args);
		} catch (IOException e) {
			LOG.fatal(e.getMessage());
		} catch (InterruptedException e) {
			LOG.fatal(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.fatal(e.getMessage());
		}
		return status;
	}

}
