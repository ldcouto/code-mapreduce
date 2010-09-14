package pt.um.mrc.jobs.allmetrics;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

public class AMJob implements JobInformable {

	@Override
	public int getArgCount() {
		return 2;
	}

	@Override
	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return JEverythingInputFormat.class;
	}

	@Override
	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return AllMetricsMapper.class;
	}

	@Override
	public Class<?> getMapperKeyOutClass() {
		return ElemID.class;
	}

	@Override
	public Class<?> getMapperValueOutClass() {
		return MetricValue.class;
	}

	@Override
	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
		return AllMetricsReducer.class;
	}

	@Override
	public String getUsage() {
		return "Usage: AMJob <in> <out>";
	}

	public static void main(String[] args) {
		AMJob me = new AMJob();
		System.exit(JobRunner.startJob(args, me));
	}
}
