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

public class CachedPackageInfoMapper<KI, VI, KO, VO> extends Mapper<KI, VI, KO, VO> {

	protected Map<String, ArrayList<String>> internalClassPkgInfo =
		new HashMap<String, ArrayList<String>>();

	public Map<String, ArrayList<String>> getInternalPackages() {
		return internalClassPkgInfo;
	}

	public void setInternalPackages(Map<String, ArrayList<String>> internalPackages) {
		this.internalClassPkgInfo = internalPackages;
	}

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Path[] localFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());

		buildCache(localFiles);

		super.setup(context);
	}

	public void buildCache(Path[] localFiles) throws IOException {

		// TODO Should this guard stay?
		if (localFiles != null) {
			FileReader fr = new FileReader(localFiles[0].toString());
			BufferedReader br = new BufferedReader(fr);

			String line;
			String[] pkgAndClass;
			ArrayList<String> classes;

			while ((line = br.readLine()) != null) {
				pkgAndClass = line.split("\\t");

				System.out.println(pkgAndClass[0]);

				classes =
					new ArrayList<String>(Arrays.asList(pkgAndClass[1].replaceAll("\\{|\\}", "")
						.trim().split(" ")));
				internalClassPkgInfo.put(pkgAndClass[0], classes);
			}
			br.close();
		}
	}

}
