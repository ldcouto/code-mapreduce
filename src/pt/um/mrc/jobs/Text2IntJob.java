package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public abstract class Text2IntJob {

	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return TextInputFormat.class;
	}

	public Class<?> getMapperKeyOutClass() {
		return Text.class;
	}

	public Class<?> getMapperValueOutClass() {
		return IntWritable.class;
	}

}
