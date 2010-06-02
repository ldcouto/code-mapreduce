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

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.rr.JFileRecordReader;

/**
 * The Class JFileInputFormat defines an  {@link InputFormat} to read non-class elements 
 * such as Interfaces and Enums.
 *
 * <br>
 * <br>
 * It also prevents Haddop from splitting the file during the set up phase.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JFileInputFormat extends FileInputFormat<FileID, Text>
{

    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    @Override
    public RecordReader<FileID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException
    {
        return new JFileRecordReader();
    }

}
