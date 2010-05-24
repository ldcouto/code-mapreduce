package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public interface JobInformable {

	public abstract String getUsage();

	public abstract Class<? extends Mapper<?,?,?,?>> getMapperClass();
	
	public abstract Class<? extends Reducer<?,?,?,?>> getReducerClass();

	public abstract Class<?> getMapperKeyClass();

	public abstract Class<?> getMapperValueClass();
	
	public abstract Class<? extends InputFormat<?,?>> getInputFormatClass();

}