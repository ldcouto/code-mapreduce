package pt.um.mrc.jobs.allmetrics;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.jobs.imprt.PkgAndClassCache;
import pt.um.mrc.util.control.JobRunner;

public class AMJob implements JobInformableTextFormat {

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
	
	@Override
	public Class<? extends OutputFormat<?, ?>> getOutputFormatClass() {
		return CSTextOutputFormat.class;
	}

	public static void main(String[] args) {
		PkgAndClassCache job1 = new PkgAndClassCache();
		AMJob job2 = new AMJob();
		Path cache = new Path("tmpCache/");

		System.exit(JobRunner.startCachedJobTextFormat2(args, job1, job2, cache));
	}


}
