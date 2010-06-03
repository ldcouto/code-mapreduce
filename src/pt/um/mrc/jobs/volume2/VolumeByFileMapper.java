package pt.um.mrc.jobs.volume2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;
import pt.um.mrc.lib.VolumeHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class VolumeByFileMapper.
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
		super.map(newKey, value, context);
		lines.set(VolumeHelper.countLoCUnformatted(value.toString()));
		context.write(key, lines);
	}

	/** The number of lines of code in the body of a method. */
	private IntWritable lines = new IntWritable();

}
