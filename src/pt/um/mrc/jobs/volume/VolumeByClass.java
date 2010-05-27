package pt.um.mrc.jobs.volume;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

public class VolumeByClass implements JobInformable {

	@Override
	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
		return TextInputFormat.class;
	}

	@Override
	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return VolumeByClassMapper.class;
	}

	@Override
	public Class<?> getMapperKeyClass() {
		return Text.class;
	}

	@Override
	public Class<?> getMapperValueClass() {
		return IntWritable.class;
	}

	@Override
	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
		return VolumeByClassReducer.class;
	}

	@Override
	public String getUsage() {
		return "Usage: VolumeByClass <methodloc> <in> <out>";
	}

	@Override
	public int getArgCount() {
		return 3;
	}

	public static void main(String[] args) throws Exception {
		VolumeByClassMisc j1 = new VolumeByClassMisc();
		VolumeByClass j2 = new VolumeByClass();
		String tempFolder = "tmpCls/";
		
		int status = JobRunner.runDoubleJob(j1, j2, tempFolder, args);
		FileSystem.get(JobRunner.getConf()).delete(new Path(tempFolder), true);

		System.exit(status);
		
		
//		VolumeByClassMisc me1 = new VolumeByClassMisc();
//		String tmpFolder = "tmpCls/";
//		String[] argsJob1 = { args[1], tmpFolder };
//		JobRunner.setJob(argsJob1, me1);
//
//		int jobStatus1 = JobRunner.runJob();
//		if (jobStatus1 != 0)
//			System.exit(jobStatus1);
//
//		VolumeByClass me2 = new VolumeByClass();
//		String[] argsJob2 = { args[0], tmpFolder, args[2] };
//		JobRunner.setJob(argsJob2, me2);
//
//		int jobStatus2 = JobRunner.runJob();
//		FileSystem.get(JobRunner.getConf()).delete(new Path(tmpFolder), true);
//
//		System.exit(jobStatus2);
	}

}
