package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This class is the Mapper for the {@link ImportsByPackage} job. It extends
 * {@link ImportsCommonMapper}, which handles the map step of this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackageMapper extends Mapper<LongWritable, Text, Text, Text>
{

    /**
     * This mapper overrides {@link ImportsCommonMapper}'s default map method in
     * order to replace the default key with the package name.
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
    	String[] arr = value.toString().split("\t");
     	Text newKey = new Text(arr[0].split("-")[0]);
    	Pattern p = Pattern.compile("(\\{ )|\\}");
    	Matcher m = p.matcher(arr[1].toString());
    	String[] newValues = m.replaceAll("").split("\\s");
    	for (String s : newValues)
    		context.write(newKey, new Text(s));
        // Set the key to the package name and call the common mapper
//        super.map(new Text(PckgHelper.findPackage(value.toString())), value, context);
    }

}