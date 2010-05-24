package pt.um.mrc.util.control;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

public class JobRunner {

	static Configuration conf;
	static CheckedJobInfo cji;
	static JobConfigurer jc;
	static MapperConfigurer mc;
	static Job job;
	
	public static int setJob(String[] args, JobInformable ji) throws Exception {

		conf = new Configuration();
		cji = new CheckedJobInfo(conf, ji.getUsage());

		String[] otherArgs = HadoopJobControl.checkArguments(args, cji);

		mc = new MapperConfigurer(ji.getMapperClass(), ji.getMapperKeyClass(), ji.getMapperValueClass());
		
		jc =
			new JobConfigurer(ji.getClass(), ji.getInputFormatClass(), new Path(otherArgs[0]),
				new Path(otherArgs[1]));
		
		job = new Job(conf);
		HadoopJobControl.configureSimpleJob(job, jc, mc, ji.getReducerClass());
		
		return 0;
	}

	public static void runJob() throws Exception {
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
