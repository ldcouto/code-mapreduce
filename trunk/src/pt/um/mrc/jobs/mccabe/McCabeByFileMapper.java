package pt.um.mrc.jobs.mccabe;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;

/**
 * This class is the Mapper for the job that relates files with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByFileMapper extends Mapper<Text, Text, Text, IntWritable>
{
    private final IntWritable one = new IntWritable(1);
    private Text filename = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        filename.set(key);

        ArrayList<String> cStatements = McCabeHelper.findControlStatements(value.toString());

        for (int i = 0; i < cStatements.size(); i++)
        {
            context.write(filename, one);
        }
    }
}