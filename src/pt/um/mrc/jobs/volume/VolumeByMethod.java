package pt.um.mrc.jobs.volume;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

import pt.um.mrc.util.control.HadoopJobControl;
import pt.um.mrc.util.control.JobConfigurer;
import pt.um.mrc.util.control.MapperConfigurer;
import pt.um.mrc.util.io.JMethodInputFormat;

/**
 * This class contains the configuration for the job that relates methods with
 * their lines of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByMethod {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: VolumeByMethod <in> <out>");
			System.exit(2);
		}

		// // Create a new Job
		Job job = new Job(conf, "compute the LoC volume for each method");

		JobConfigurer jc =
			new JobConfigurer(VolumeByMethod.class, JMethodInputFormat.class, new Path(otherArgs[0]),
				new Path(otherArgs[1]));

		MapperConfigurer mc =
			new MapperConfigurer(VolumeByMethodMapper.class, Text.class, IntWritable.class);

		HadoopJobControl.configureSimpleJob(job, jc, mc, VolumeByMethodReducer.class);


		// Close the Job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}