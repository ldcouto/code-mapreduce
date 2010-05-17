package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.lib.PckgHelper;
import pt.um.mrc.util.mappers.DistributedCacheSetUpMapper;

/**
 * This class is the Mapper for the job that relates packages with the packages
 * they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackageMapper extends DistributedCacheSetUpMapper<Text, Text, Text, Text>
{
    private Text packagename = new Text();
    private Text importedPackage = new Text();
    
    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        // Set the key to the package name
        packagename.set(PckgHelper.findPackage(value.toString()));
        
        // Find the imported packages
        List<String> importedPackages = ImprtHelper.findImportedPackages(value.toString());

        // Write to the output.
        for (String imprtPckg : importedPackages)
        {
            for (String intrnPckg : super.internalPackages)
            {
                if (imprtPckg.indexOf(intrnPckg) >= 0)
                {
                    importedPackage.set(imprtPckg);
                    context.write(packagename, importedPackage);    
                }  
            }
        }
    }
}