package pt.um.mrc.jobs.allmetrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.ImprtHelper;
import pt.um.mrc.lib.McCabeHelper;
import pt.um.mrc.lib.VolumeHelper;
import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.mappers.CachedPackageInfoMapper;

public class AllMetricsMapper extends CachedPackageInfoMapper<ElemID, Text, ElemID, MetricValue> {

	MetricValue mv = new MetricValue();

	@Override
	protected void map(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {

		IDType type = key.getIDType();

		// TODO CLear me!
		// System.out.println("vou processar o " + key.toString() );

		switch (type) {
		case METHOD:
			mapAllMethodInfo(key, value, context);
			break;
		case CLASS:
			mapAllClassInfo(key, value, context);
			break;
		case FILE:
			mapAllFileInfo(key, value, context);
			break;
		case PACKAGE:
			mapAllPackageInfo(key, value, context);
			break;
		}
	}

	private void mapAllFileInfo(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		ElemID pkgAux = new ElemID();
		pkgAux.setIDType(IDType.PACKAGE);
		pkgAux.setPackageName(key.getPackageName());
		importCalc(key, value, MetricType.IMPORTS_BY_PACKAGE, context);

		ElemID fileAux = new ElemID();
		fileAux.setIDType(IDType.FILE);
		fileAux.setPackageName(key.getPackageName());
		fileAux.setFileName(key.getFileName());
		importCalc(key, value, MetricType.IMPORTS_BY_FILE, context);
	}

	private void importCalc(ElemID key, Text value, MetricType mt, Context context) throws IOException,
			InterruptedException {

		key.setMetricType(mt);
		mv.setIsText(true);
		mv.setIntw(null);		
		
		// Find the imported packages
		List<String> imports = ImprtHelper.findImports(value.toString());

		// Write the respective pairs
		List<String> aux = new ArrayList<String>();

		for (String s : imports) {
			aux = ImprtHelper.compImportedClasses(s, super.internalClassPkgInfo);
			if (aux != null) {
				for (String s2 : aux) {
					mv.setText(s2);
					context.write(key, mv);
				}
			}
		}

	}

	private void mapAllPackageInfo(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		countPackage(key, value, context);
	}

	private void mapAllClassInfo(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		countClass(key, value, context);
		countPackage(key, value, context);
	}

	private void mapAllMethodInfo(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		countMethod(key, value, context);
		countClass(key, value, context);
		countPackage(key, value, context);
	}

	private void countPackage(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		ElemID pkgAux = new ElemID();
		pkgAux.setIDType(IDType.PACKAGE);
		pkgAux.setPackageName(key.getPackageName());

		countWriter(pkgAux, value, MetricType.VOLUME_BY_PACKAGE, MetricType.MCCABE_BY_PACKAGE,
				context);
	}

	public void countClass(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		ElemID classAux = new ElemID();
		classAux.setIDType(IDType.CLASS);
		classAux.setPackageName(key.getPackageName());
		classAux.setFileName(key.getFileName());
		classAux.setClassName(key.getClassName());

		countWriter(classAux, value, MetricType.VOLUME_BY_CLASS, MetricType.MCCABE_BY_CLASS,
				context);
	}

	public void countMethod(ElemID key, Text value, Context context) throws IOException,
			InterruptedException {
		ElemID mtdAux = new ElemID();
		mtdAux.setIDType(IDType.METHOD);
		mtdAux.setPackageName(key.getPackageName());
		mtdAux.setFileName(key.getFileName());
		mtdAux.setClassName(key.getClassName());
		mtdAux.setMethodName(key.getMethodName());

		countWriter(mtdAux, value, MetricType.VOLUME_BY_METHOD, MetricType.MCCABE_BY_METHOD,
				context);
	}

	private void countWriter(ElemID key, Text value, MetricType mtv, MetricType mtm, Context context)
			throws IOException, InterruptedException {

		String source = value.toString();
		mv.setIsText(false);
		mv.setText(null);

		mv.setIntw(VolumeHelper.countLinesOfCode(source));
		key.setMetricType(mtv);
		context.write(key, mv);

		mv.setIntw(McCabeHelper.countMcCabe(source));
		key.setMetricType(mtm);
		context.write(key, mv);
	}

}
