package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;

public class McCabeMiscMapper extends Mapper<WritableComparable<?>, Text, Text, IntWritable>
{
    private Text keyOut = new Text();
    private IntWritable mcCabeNumber = new IntWritable();
    
    @Override
    protected void map(WritableComparable<?> key, Text value, Context context)
            throws IOException, InterruptedException
    {
        keyOut.set(key.toString());
        mcCabeNumber.set(McCabeHelper.countMcCabe(value.toString()));
        
        context.write(keyOut, mcCabeNumber);
    }
}
