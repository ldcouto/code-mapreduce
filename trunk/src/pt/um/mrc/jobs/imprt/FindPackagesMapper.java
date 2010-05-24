package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPackagesMapper.
 */
public class FindPackagesMapper extends Mapper<Text, Text, Text, Text>
{
    
    /** The packge. */
    private Text packge = new Text();

    /* 
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        packge.set(PckgHelper.findPackage(value.toString()));
        
        context.write(packge, new Text(""));
    }
}
