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
import pt.um.mrc.util.io.rr.JMcClassRecordReader;

/**
 * The Class JMcClassInputFormat is used to define an  {@link InputFormat} that takes Java files 
 * and only reads static blocks inside a class for the purpose of Cyclomatic Complexity calculation.
 * 
 * 
 *  <br>
 * <br>
 * It also prevents Haddop from splitting the file during the set up phase.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JMcClassInputFormat extends FileInputFormat<ClassID, Text>
{

    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    @Override
    public RecordReader<ClassID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException
    {
        return new JMcClassRecordReader();
    }

}
