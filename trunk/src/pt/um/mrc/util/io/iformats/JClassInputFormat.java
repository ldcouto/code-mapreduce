package pt.um.mrc.util.io.iformats;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.rr.JClassRecordReader;
/**
 * The Class JClassInputFormat defines an  {@link InputFormat} that takes Java Files and
 * reads static blocks and other initializers that are present inside the file's
 * classes.
 *
 *  <br>
 * <br>
 * It also prevents Haddop from splitting the file during the set up phase.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JClassInputFormat extends FileInputFormat<ClassID, Text> {

	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}

	@Override
	public RecordReader<ClassID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		return new JClassRecordReader();
	}

}
