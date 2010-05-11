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
    private Text packge = new Text();

    private Text filename = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        filename.set(key);
        packge.set(PckgHelper.findPackage(value.toString()));

        context.write(filename, packge);
    }
}