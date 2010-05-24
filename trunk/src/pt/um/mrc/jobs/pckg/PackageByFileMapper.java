package pt.um.mrc.jobs.pckg;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;

/**
 * This class is the Mapper for the job that relates files with the package
 * they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PackageByFileMapper extends Mapper<Text, Text, Text, Text>
{
    
    /** The packge. */
    private Text packge = new Text();

    /** The filename. */
    private Text filename = new Text();

    /* (non-Javadoc)
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        filename.set(key);
        packge.set(PckgHelper.findPackage(value.toString()));

        context.write(filename, packge);
    }
}