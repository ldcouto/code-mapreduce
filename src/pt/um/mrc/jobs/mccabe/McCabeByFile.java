package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

/**
 * This class contains the configuration for the job that relates files with
 * their McCabe number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByFile implements JobInformable
{
    public String getUsage()
    {
        return "Usage: McCabeByFile <in> <out>";
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return McCabeByFileMapper.class;
    }

    public Class<?> getMapperKeyClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueClass()
    {
        return IntWritable.class;
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return TextInputFormat.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return McCabeByFileReducer.class;
    }

    public static void main(String[] args) throws Exception
    {
        McCabeByFile me = new McCabeByFile();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }
}