package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.util.mappers.CachedPackageInfoMapper;

public class ImportsCommonMapper<KI, VI, KO, VO> extends CachedPackageInfoMapper<Text, Text, Text, Text>
{
    private Text importedPackage = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException
    {
        // Find the imported packages
        List<String> imports = ImprtHelper.findImports(value.toString());

        List<String> aux = new ArrayList<String>();

        for (String s : imports)
        {
            aux = ImprtHelper.compImportedClasses(s, super.internalClassPkgInfo);
            if (aux != null)
            {
                for (String s2 : aux)
                {
                    importedPackage.set(s2);
                    context.write(key, importedPackage);
                }
            }
        }
    }
}
