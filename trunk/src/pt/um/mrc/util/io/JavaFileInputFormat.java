package pt.um.mrc.util.io;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * This class defines a InputFormat specifically for Java files. It prevents
 * files from being split. It an entire file at once.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */

public class JavaFileInputFormat extends FileInputFormat<Text, Text>
{

	@Override
	public RecordReader<Text, Text> createRecordReader(InputSplit arg0,
			TaskAttemptContext arg1) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
