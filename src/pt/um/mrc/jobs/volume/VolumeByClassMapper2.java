package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;
import pt.um.mrc.util.datatypes.ClassID;

public class VolumeByClassMapper2 extends Mapper<ClassID, Text, Text, IntWritable>
{
    private Text keyOut = new Text();
    private IntWritable valueOut = new IntWritable();
    
    @Override
    protected void map(ClassID key, Text value, Context context)
            throws IOException, InterruptedException
    {
        keyOut.set(key.toString());
        valueOut.set(VolumeHelper.countLinesOfCode(value.toString()));
        
        context.write(keyOut, valueOut);
    }
    
}
