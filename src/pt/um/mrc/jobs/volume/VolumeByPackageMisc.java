package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JClassFileInputFormat;

public class VolumeByPackageMisc implements JobInformable
{

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JClassFileInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeMiscMapper.class;
    }

    @Override
    public Class<?> getMapperKeyClass()
    {
        return Text.class;
    }

    @Override
    public Class<?> getMapperValueClass()
    {
        return IntWritable.class;
    }

    @Override
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByPackageReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: VolumeByPkgMisc <sourceFiles> <output>";
    }
    
    public static void main(String[] args)
    {
        VolumeByPackageMisc me = new VolumeByPackageMisc();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }

	@Override
	public int getArgCount() {
		return 2;
	}

}
