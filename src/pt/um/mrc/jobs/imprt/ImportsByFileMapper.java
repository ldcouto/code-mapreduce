package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.PckgHelper;

/**
 * This class is the mapper for the {@link ImportsByFile} job. It extends
 * {@link ImportsCommonMapper} which handles the map step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFileMapper extends ImportsCommonMapper<Text, Text, Text, Text>
{
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException
    {
        String filename = key.toString();
        String packageName = PckgHelper.findPackage(value.toString());
        Text newKey = new Text(packageName + "." + filename);  
        super.map(newKey, value, context);
    }
}