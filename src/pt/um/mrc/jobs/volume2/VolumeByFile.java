package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

public class VolumeByFile implements JobInformable
{

    public int getArgCount()
    {
        return 2;
    }

    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JavaFileInputFormat.class;
    }

    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByFileMapper.class;
    }

    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByFileReducer.class;
    }

    public String getUsage()
    {
        return "Usage: VolumeByFile <in> <out>";
    }
    
    /**
     * The main method.
     * 
     * @param args
     *            the arguments from the command line, the input and the output
     *            folders
     */
    public static void main(String[] args)
    {
        VolumeByFile me = new VolumeByFile();
        System.exit(JobRunner.startJob(args, me));
    }

}
