package pt.um.mrc.jobs.volume2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.jobs.volume.VolumeByFile;
import pt.um.mrc.lib.PckgHelper;
import pt.um.mrc.lib.VolumeHelper;

/**
 * This class is the mapper for the {@link VolumeByFile} job. It extends a standard Hadoop Mapper.
 * Since it is not fed by a specialized RecordReader, this Mapper must discard the input key and
 * manually construct a new one.
 */
public class VolumeByFileMapper extends Mapper<Text, Text, Text, IntWritable> {

	/**
	 * This overrides the default map method in order to compute the lines of
	 * code in the given value.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             Signals that an Interrpution has occurred
	 */
	protected void map(Text key, Text value, Context context) throws IOException,
			InterruptedException {
		String filename = key.toString();
		String packageName = PckgHelper.findPackage(value.toString());
		Text newKey = new Text(packageName + "-" + filename);
		lines.set(VolumeHelper.countLoCUnformatted(value.toString()));
		context.write(newKey, lines);
	}

	/** The number of lines of code in the body of a method. */
	private IntWritable lines = new IntWritable();

}
