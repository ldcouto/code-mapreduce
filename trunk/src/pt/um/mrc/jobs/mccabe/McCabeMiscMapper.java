package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;
import pt.um.mrc.util.datatypes.ClassID;

public class McCabeMiscMapper extends Mapper<ClassID, Text, Text, IntWritable>
{
    private Text keyOut = new Text();
    private IntWritable mcCabeNumber = new IntWritable();
    
    @Override
    protected void map(ClassID key, Text value, Context context)
            throws IOException, InterruptedException
    {
    //	System.out.println("Vou processar "+ key.toString() + " " + value.toString());
        keyOut.set(key.toString());
        mcCabeNumber.set(McCabeHelper.countMcCabe(value.toString()));
        
        context.write(keyOut, mcCabeNumber);
    }
    
}
