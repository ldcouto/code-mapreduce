package pt.um.mrc.jobs.volume2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.PckgHelper;

public class VolumeByFileMapper extends VolumeCommonMapper<Text, Text, Text, IntWritable>
{

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException
    {
        String filename = key.toString();
        String packageName = PckgHelper.findPackage(value.toString());
        Text newKey = new Text(packageName + "-" + filename);  
        super.map(newKey, value, context);
    }
}
