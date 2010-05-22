package pt.um.mrc.util.mappers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Mapper;

public class PackageInfoMapper<KI, VI, KO, VO> extends Mapper<KI, VI, KO, VO>
{

    protected ArrayList<String> internalPackages = new ArrayList<String>();

    public ArrayList<String> getInternalPackages()
    {
        return internalPackages;
    }

    public void setInternalPackages(ArrayList<String> internalPackages)
    {
        this.internalPackages = internalPackages;
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException
    {
        Path[] localFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());

        // TODO Should this guard stay?
        if (localFiles != null)
        {
            FileReader fr = new FileReader(localFiles[0].toString());
            BufferedReader br = new BufferedReader(fr);

            String aux;

            while ((aux = br.readLine()) != null)
            {
                internalPackages.add(aux.trim());
            }

            br.close();
        }
        super.setup(context);
    }

}
