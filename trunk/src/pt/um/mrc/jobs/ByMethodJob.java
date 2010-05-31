package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.iformats.JMethodInputFormat;

public abstract class ByMethodJob {

	public Class<?> getMapperKeyOutClass() {
		return MethodID.class;
	}

	public Class<?> getMapperValueOutClass() {
		return IntWritable.class;
	}

	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return JMethodInputFormat.class;
	}
}
