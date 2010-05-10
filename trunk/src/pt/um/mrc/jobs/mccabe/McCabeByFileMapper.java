package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This class is the Mapper for the job that relates files with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByFileMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException
    {
        // TODO Auto-generated method stub
        super.map(key, value, context);
    }

}