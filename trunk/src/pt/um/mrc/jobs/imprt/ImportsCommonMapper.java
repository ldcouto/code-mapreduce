package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.util.mappers.CachedPackageInfoMapper;

public class ImportsCommonMapper<KI, VI, KO, VO> extends
                                                 CachedPackageInfoMapper<Text, Text, Text, Text>
{

    private Text importedPackage = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException,
            InterruptedException
    {
        // Find the imported packages
        List<String> imports = ImprtHelper.findImports(value.toString());

        List<String> aux = new ArrayList<String>();

        for (String s : imports)
        {
            aux = ImprtHelper.compImportedClasses(s, internalClassPkgInfo);
            for (String s2 : aux)
            {
                importedPackage.set(s2);
                context.write(key, importedPackage);
            }
        }
        //		
        // List<String s> = ImprtHelper.fetchImportedClasses()
        //		
        // for (String s : imports)
        // if (ImprtHelper.importMatcher(s, imports)){
        //				
        // context.write(key, new Text(s))
        // }
        // for (String s : internalClassPkgInfo) {
        // if (ImprtHelper.importMatcher(s, internalClassPkgInfo)) {
        // importedPackage.set(s);
        // context.write(key, importedPackage);
        // }
        // }
        // // Write to the output.
        // for (String imprtPckg : importedPackages) {
        //
        // imprtPckg = imprtPckg.replaceAll("\\*", "");
        // for (String intrnPckg : internalClassPkgInfo) {
        // if (imprtPckg.indexOf(intrnPckg) >= 0) {
        // importedPackage.set(imprtPckg);
        // context.write(key, importedPackage);
        // }
        //
        // if (intrnPckg.indexOf(imprtPckg) >= 0) {
        // importedPackage.set(intrnPckg);
        // context.write(key, importedPackage);
        // }
        // }
        // }
    }
}
