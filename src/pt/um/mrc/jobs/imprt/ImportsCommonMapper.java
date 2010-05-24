package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.util.mappers.CachedInfoMapper;

public class ImportsCommonMapper<KI, VI, KO, VO> extends CachedInfoMapper<Text, Text, Text, Text> {

	private Text importedPackage = new Text();

	@Override
	protected void map(Text key, Text value, Context context) throws IOException,
		InterruptedException {
		// Find the imported packages
//		List<String> importedPackages = ImprtHelper.findImportedPackages(value.toString());

		for (String s : internalPackages) {
			if (ImprtHelper.importMatcher(s, internalPackages)) {
				importedPackage.set(s);
				context.write(key, importedPackage);
			}
		}
//		// Write to the output.
//		for (String imprtPckg : importedPackages) {
//
//			imprtPckg = imprtPckg.replaceAll("\\*", "");
//			for (String intrnPckg : internalPackages) {
//				if (imprtPckg.indexOf(intrnPckg) >= 0) {
//					importedPackage.set(imprtPckg);
//					context.write(key, importedPackage);
//				}
//
//				if (intrnPckg.indexOf(imprtPckg) >= 0) {
//					importedPackage.set(intrnPckg);
//					context.write(key, importedPackage);
//				}
//			}
//		}
	}
}
