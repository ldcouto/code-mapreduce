package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;
import pt.um.mrc.util.datatypes.MethodID;

/**
 * This class is the Mapper for the job that relates methods with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByMethodMapper extends Mapper<MethodID, Text, MethodID, IntWritable>
{
    /** The McCabe number. */
    private IntWritable mcCabeNumber = new IntWritable();

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN,
     * org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(MethodID key, Text value, Context context) throws IOException, InterruptedException
    {
        mcCabeNumber.set(McCabeHelper.countMcCabe(value.toString()));
        context.write(key, mcCabeNumber);
    }
}