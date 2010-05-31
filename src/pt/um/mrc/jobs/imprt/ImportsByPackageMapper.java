package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.PckgHelper;

/**
 * This class is the Mapper for the {@link ImportsByPackage} job. It extends
 * {@link ImportsCommonMapper}, which handles the map step of this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackageMapper extends ImportsCommonMapper<Text, Text, Text, Text>
{

    /**
     * This mapper overrides {@link ImportsCommonMapper}'s default map method in
     * order to replace the default key with the package name.
     */
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException
    {
        // Set the key to the package name and call the common mapper
        super.map(new Text(PckgHelper.findPackage(value.toString())), value, context);
    }

}