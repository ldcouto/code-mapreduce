package everythingStuff;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;
import pt.um.mrc.util.datatypes.IDType;

/**
 * This class is the Mapper for the job that relates methods with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeEverythingMapper extends Mapper<GeneralID, Text, GeneralID, IntWritable> {

	/** The number of lines of code in the body of a method. */
	private IntWritable lines = new IntWritable();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN,
	 * org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	protected void map(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		IDType type = key.getType();

//		TODO CLear me!
//		System.out.println("vou processar o " + key.toString() );

		switch (type) {
		case METHOD:
			writeAllMethodInfo(key, value, context);
			break;
		case CLASS:
			writeAllClassInfo(key, value, context);
			break;
		case FILE:
			writeAllFileInfo(key, value, context);
			break;
		case PACKAGE:
			writePackageInfo(key, value, context);
			break;
		}
	}

	private void writeAllFileInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		writePackageInfo(key, value, context);
		writeFileInfo(key, value, context);
	}

	private void writeAllClassInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		writePackageInfo(key, value, context);
		writeFileInfo(key, value, context);
		writeClassInfo(key, value, context);
	}

	private void writeAllMethodInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		writePackageInfo(key, value, context);
		writeFileInfo(key, value, context);
		writeClassInfo(key, value, context);
		writeMethodInfo(key, value, context);
	}

	private void writePackageInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		GeneralID pkgAux = new GeneralID();
		pkgAux.setType(IDType.PACKAGE);
		pkgAux.setPackageName(key.getPackageName());
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(pkgAux, lines);
//		System.out.println("escrevi o " + value );
	}

	private void writeFileInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		GeneralID fileAux = new GeneralID();
		fileAux.setType(IDType.FILE);
		fileAux.setPackageName(key.getPackageName());
		fileAux.setFileName(key.getFileName());
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(fileAux, lines);
//		System.out.println("escrevi o " + value );
		
	}

	private void writeClassInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		GeneralID classAux = new GeneralID();
		classAux.setType(IDType.CLASS);
		classAux.setPackageName(key.getPackageName());
		classAux.setFileName(key.getFileName());
		classAux.setClassName(key.getClassName());
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(classAux, lines);
	//	System.out.println("escrevi o " + value );
		
	}

	private void writeMethodInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(key, lines);
//		System.out.println("escrevi o " + value );
		
	}

}