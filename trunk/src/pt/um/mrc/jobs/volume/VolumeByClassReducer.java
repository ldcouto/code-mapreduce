package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * This class is the Reducer for the job that relates classes with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByClassReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException
    {
        // TODO Auto-generated method stub
        super.reduce(key, values, context);
    }

}