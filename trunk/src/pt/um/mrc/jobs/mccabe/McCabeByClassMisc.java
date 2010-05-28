package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JMcClassInputFormat;

public class McCabeByClassMisc implements JobInformable
{

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JMcClassInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeMiscMapper.class;
    }

    @Override
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    @Override
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    @Override
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByClassReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: McCabeByClassMisc <in> <out>";
    }
    
    public static void main(String[] args)
    {
        McCabeByClassMisc me = new McCabeByClassMisc();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }

	@Override
	public int getArgCount() {
		return 2;
	}

}
