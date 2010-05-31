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

/**
 * The Class JobRunner is an auxiliary class to configure and run a Hadoop Job.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JobRunner {

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

	public static Configuration getConf() {
		return conf;
	}

	/**
	 * Sets up a job.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @param ji
	 *            the JobInformaer for the job to configure
	 * @throws IOException
	 *             the exception
	 */
	public static void setJob(String[] args, JobInformable ji) throws IOException {
		conf = new Configuration();
		setJob(args, ji, conf);
	}

	public static void setJob(String[] args, JobInformable ji, Configuration conf)
			throws IOException {
		cji = new CheckedJobInfo(ji.getUsage(), conf, ji.getArgCount());

		String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

		mc =
				new MapperConfigHolder(ji.getMapperClass(), ji.getMapperKeyOutClass(), ji
						.getMapperValueOutClass());

		jc = new JobConfigHolder(ji.getClass(), ji.getInputFormatClass(), otherArgs);

		job = new Job(conf);
		HadoopJobControl.configureSimpleJob(job, jc, mc, ji.getReducerClass());
	}

	/**
	 * Run job.
	 * 
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static int runJob() throws IOException, InterruptedException, ClassNotFoundException {
		int r = 2;

		r = job.waitForCompletion(true) ? 0 : 1;

		return r;
	}

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

	public static void configureDistCache(Path path) throws IOException {
		for (FileStatus fstatus : FileSystem.get(conf).listStatus(path)) {
			if (!fstatus.isDir()) {
				DistributedCache.addCacheFile(fstatus.getPath().toUri(), conf);
			}
		}
	}

	public static int startJob(String[] args, JobInformable me) {
		return catcher(args, me, null, null, null);
	}

	public static int startJob(String[] args, JobInformable j1, JobInformable j2, String tempFolder) {
		return catcher(args, j1, j2, null, tempFolder);
	}

	public static int startCachedJob(String[] args, JobInformable job1, JobInformable job2,
			Path cache) {
		return catcher(args, job1, job2, cache, null);
	}

	private static int catcher(String[] args, JobInformable job1, JobInformable job2, Path cache,
			String folder) {
		
		int status = -1;
		try {
			if (job2 == null){
				JobRunner.setJob(args, job1);
				status = JobRunner.runJob();
			}
			else if (cache == null)
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
