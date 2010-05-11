package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;

public class FindPackagesMapper extends Mapper<Text, Text, Text, Text>
{
    private Text packge = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        packge.set(PckgHelper.findPackage(value.toString()));
        
        context.write(packge, new Text(""));
    }
}
