package pt.um.mrc.jobs.imprt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.lib.PckgHelper;

/**
 * This class is the Mapper for the job that relates packages with the packages
 * they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackageMapper extends Mapper<Text, Text, Text, Text>
{
    private Text packagename = new Text();
    private Text importedPackage = new Text();
    private ArrayList<String> internalPackages = new ArrayList<String>();
    
    @Override
    protected void setup(Context context) throws IOException, InterruptedException
    {
        Path[] localFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
        
        FileReader fr = new FileReader(localFiles[0].toString());
        BufferedReader br = new BufferedReader(fr);

        String aux;

        while ((aux = br.readLine()) != null)
        {
            internalPackages.add(aux.trim());
        }

        br.close();
        
        super.setup(context);
    }

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
            for (String intrnPckg : internalPackages)
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