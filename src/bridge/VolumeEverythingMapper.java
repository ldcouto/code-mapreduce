package bridge;

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
			writeMethodInfo(key, value, context);
			break;
		case CLASS:
			writeClassInfo(key, value, context);
			break;
		case FILE:
			writeFileInfo(key, value, context);
			break;
		case PACKAGE:
			break;
		}
	}


	private void writeFileInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		GeneralID idAux = new GeneralID();
		idAux.setType(IDType.METHOD);
		idAux.setPackageName(key.getPackageName());
		idAux.setFileName(key.getFileName());
		idAux.setClassName(BridgeConstants.acumClass);
		idAux.setMethodName(BridgeConstants.acumMethod);
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(idAux, lines);
		//	System.out.println("escrevi o " + value );

	}

	private void writeClassInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		GeneralID idAux = new GeneralID();
		idAux.setType(IDType.METHOD);
		idAux.setPackageName(key.getPackageName());
		idAux.setFileName(key.getFileName());
		idAux.setClassName(key.getClassName());
		idAux.setMethodName(BridgeConstants.acumMethod);
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(idAux, lines);
	//	System.out.println("escrevi o " + value );
		
	}

	private void writeMethodInfo(GeneralID key, Text value, Context context) throws IOException,
			InterruptedException {
		lines.set(VolumeHelper.countLinesOfCode(value.toString()));
		context.write(key, lines);
//		System.out.println("escrevi o " + value );
		
	}

}