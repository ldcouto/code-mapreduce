package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.util.mappers.CachedPackageInfoMapper;

/**
 * This is the common mapper between the imports by file and imports by package
 * jobs. It extends the {@link CachedPackageInfoMapper} in order to obtain the
 * internal classes of the project being analyzed.
 * 
 * @param <KI>
 *            the generic type
 * @param <VI>
 *            the generic type
 * @param <KO>
 *            the generic type
 * @param <VO>
 *            the generic type
 */
public class ImportsCommonMapper<KI, VI, KO, VO> extends CachedPackageInfoMapper<Text, Text, Text, Text>
{

    /** The imported package. */
    private Text importedPackage = new Text();

    /**
     * This is the map function for this mapper. 
     * 
     * The key is left unchanged, so it's passed directly to the next step.
     * 
     * On the other hand the values, the file contents, are processed in order
     * to find out which classes are being imported and match them with the
     * project's internal classes and outputs only the ones that return a
     * positive match.
     */
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
