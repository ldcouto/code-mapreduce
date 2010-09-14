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
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class HadoopJobControl {

	/** The LOG. */
	private static Log LOG = LogFactory.getLog(HadoopJobControl.class);

	/**
	 * Configures a simple hadoop job.
	 * 
	 * @param job
	 *            the job to be configured
	 * @param jc
	 *            the holder of the job configuration
	 * @param mc
	 *            the holder of the mapper configuration
	 * @param reducer
	 *            the reducer class to be used
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void configureSimpleJob(Job job, JobConfigHolder jc, MapperConfigHolder mc,
			Class<? extends Reducer<?, ?, ?, ?>> reducer) throws IOException {

		// Configure generic Job stuff
		job.setJarByClass(jc.getClassJar());
		job.setInputFormatClass(jc.getIntputFormat());
		if (jc.getOutputFormat() != null)
			job.setOutputFormatClass(jc.getOutputFormat());
		int i;
		for (i = 0; i < jc.getPaths().length - 1; i++) {

			FileInputFormat.addInputPath(job, new Path(jc.getPaths()[i]));
		}
		FileOutputFormat.setOutputPath(job, new Path(jc.getPaths()[i]));

		// Configure the Mapper Stuff
		job.setMapperClass(mc.getMapperClass());
		job.setMapOutputKeyClass(mc.getMapOutKey());
		job.setMapOutputValueClass(mc.getMapOutValue());

		// Configure the Reducer Class
		job.setReducerClass(reducer);

		// Change the outputformat
	}

	/**
	 * Checks that the given arguments are correct.
	 * 
	 * @param args
	 *            the arguments to be checked
	 * @param cji
	 *            the correct configuration of the arguments
	 * @return the valid arguments
	 */
	public static String[] checkArguments(String[] args, CheckedJobInfo cji) {
		String[] otherArgs = new GenericOptionsParser(cji.getConf(), args).getRemainingArgs();

		if (otherArgs.length != cji.getArgNum()) {
			LOG.fatal(cji.getUsageMessage());
			System.exit(2);
		}

		return otherArgs;
	}
}
