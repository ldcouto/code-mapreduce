package pt.um.mrc.util.io.iformats;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import pt.um.mrc.util.io.rr.JavaFileRecordReader;

/**
 * This class defines a InputFormat specifically for Java files. It prevents
 * files from being split. It an entire file at once.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */

public class JavaFileInputFormat extends FileInputFormat<Text, Text>
{

    /**
     * This method always returns false. It prevents a file from being split.
     */
    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    /**
     * This method creates a <code>RecordReader<Text, Text></code> that will
     * read an entire file all at once.
     */
    @Override
    public RecordReader<Text, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException
    {
        return new JavaFileRecordReader();
    }
}
