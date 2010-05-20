package pt.um.mrc.util.mappers;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;

public class ImportsCommonMapper<KI, VI, KO, VO> extends PackageInfoMapper<Text, Text, Text, Text>
{
    private Text filename = new Text();
    private Text importedPackage = new Text();

    
    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        // Set the key to the filename
        filename.set(key);
        
        // Find the imported packages
        List<String> importedPackages = ImprtHelper.findImportedPackages(value.toString());

        // Write to the output.
        for (String imprtPckg : importedPackages)
        {
            for (String intrnPckg : internalPackages)
            {
                if (imprtPckg.indexOf(intrnPckg) >= 0)
                {
                    importedPackage.set(imprtPckg);
                    context.write(filename, importedPackage);    
                }  
            }
        }
    }
}
