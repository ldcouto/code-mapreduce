package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This class is the Mapper for the job that relates classes with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByClassMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outKey = new Text();
	private IntWritable outValue = new IntWritable();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException,
		InterruptedException {
		
		String[] aux1 = value.toString().split("\t");
		String[] aux2 = aux1[0].split("\\-");
		
		StringBuilder sb = new StringBuilder();
		sb.append(aux2[0]);
		sb.append('-');
		sb.append(aux2[1]);
		sb.append('-');
		sb.append(aux2[2]);

		outKey.set(sb.toString());
		outValue.set(Integer.parseInt(aux1[1]));

		context.write(outKey, outValue);
	}

}