package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

/**
 * This job relates files with their volume. <br>
 * 
 * It takes two parameters. The folder, the input and the output folder. The
 * input folder must contain a set of source files. And the output folder cannot
 * exist. The main difference between this job and the
 * {@link pt.um.mrc.jobs.volume.VolumeByFile} is that this job calculates the
 * Lines of Code volume for unformatted source files, while the other job takes
 * that into account.<br>
 * 
 * <br>
 * The output produced comes in the form: <br>
 * <br>
 * 
 * PACKAGENAME-FILENAME VOLUME
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeByFile implements JobInformable {

	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return VolumeByFileMapper.class;
	}

	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
		return VolumeByFileReducer.class;
	}

	public String getUsage() {
		return "Usage: VolumeByFile <in> <out>";
	}

	public int getArgCount() {
		return 2;
	}

	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return JavaFileInputFormat.class;
	}

	public Class<?> getMapperKeyOutClass() {
		return Text.class;
	}

	public Class<?> getMapperValueOutClass() {
		return IntWritable.class;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments from the command line, the input and the output
	 *            folders
	 */
	public static void main(String[] args) {
		VolumeByFile me = new VolumeByFile();
		System.exit(JobRunner.startJob(args, me));
	}

}
