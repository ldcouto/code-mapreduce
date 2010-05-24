package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.mappers.LineValuesMapper;

/**
 * This class is the Mapper for the job that relates files with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByFileMapper extends LineValuesMapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		lineContents = LineType.FILE;
		super.setup(context);
	}
}