package pt.um.mrc.util.mappers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.jobs.imprt.ImportsCommonMapper;

/**
 * This mapper server as an auxiliary to the {@link ImportsCommonMapper}. It
 * reads information from the {@link DistributedCache} and stores it in an
 * auxiliary structure for easier access.
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
public class CachedPackageInfoMapper<KI, VI, KO, VO> extends Mapper<KI, VI, KO, VO>
{
    /** The internal class and package info. */
    protected Map<String, ArrayList<String>> internalClassPkgInfo = new HashMap<String, ArrayList<String>>();

    /**
     * Gets the internal packages.
     * 
     * @return the internal packages
     */
    public Map<String, ArrayList<String>> getInternalPackages()
    {
        return internalClassPkgInfo;
    }

    /**
     * Sets the internal packages.
     * 
     * @param internalPackages
     *            the new internal packages
     */
    public void setInternalPackages(Map<String, ArrayList<String>> internalPackages)
    {
        this.internalClassPkgInfo = internalPackages;
    }

    /**
     * This overriden setup method loads up the the files from the distributed
     * cache and passes them on to an auxiliary method to process.
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException
    {
        Path[] localFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());

        buildCache(localFiles);

        super.setup(context);
    }

    /**
     * Processes the files from the distributed cache and builds the internal
     * package and class information.
     * 
     * @param localFiles
     *            the local files
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    protected void buildCache(Path[] localFiles) throws IOException
    {
        if (localFiles != null)
        {
            FileReader fr = new FileReader(localFiles[0].toString());
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] pkgAndClass;
            ArrayList<String> classes;

            while ((line = br.readLine()) != null)
            {
                pkgAndClass = line.split("\\t");

                classes = new ArrayList<String>(Arrays.asList(pkgAndClass[1].replaceAll("\\{|\\}", "").trim().split(" ")));
                internalClassPkgInfo.put(pkgAndClass[0], classes);
            }
            br.close();
        }
    }

}
