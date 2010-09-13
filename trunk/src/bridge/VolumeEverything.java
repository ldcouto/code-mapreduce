package bridge;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This class contains the configuration for the job that relates methods with
 * their lines of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeEverything implements JobInformable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getUsage()
	 */
	public String getUsage() {
		return "Usage: VolumeEverything <in> <out>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
	 */
	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return VolumeEverythingMapper.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getMapperKeyClass()
	 */
	public Class<?> getMapperKeyOutClass() {
		return GeneralID.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getMapperValueClass()
	 */
	public Class<?> getMapperValueOutClass() {
		return IntWritable.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getInputFormatClass()
	 */
	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return CopyOfJEverythingInputFormat.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
	 */
	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
		return VolumeEverythingReducer.class;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments from the command line
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) {
		VolumeEverything me = new VolumeEverything();
		System.exit(JobRunner.startJob(args, me));
	}

	@Override
	public int getArgCount() {
		return 2;
	}
}