package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;
import pt.um.mrc.util.datatypes.MethodID;

/**
 * This class is the Mapper for the job that relates methods with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByMethodMapper extends Mapper<MethodID, Text, MethodID, IntWritable>
{
    
    /** The number of lines of code in the body of a method. */
    private IntWritable lines = new IntWritable();

    /* (non-Javadoc)
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(MethodID key, Text value, Context context) throws IOException,
            InterruptedException
    {
        lines.set(VolumeHelper.countLinesOfCode(value.toString()));
        context.write(key, lines);
    }

}