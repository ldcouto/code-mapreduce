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

import pt.um.mrc.util.io.rr.JavaFileRecordReader;

// TODO: Auto-generated Javadoc
/**
 * This class defines a {@link InputFormat} specifically for Java files. It
 * prevents files from being split.<br>
 * 
 * The outputted keys are the filename and the outputted values are the file
 * contents <br>
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class JavaFileInputFormat extends FileInputFormat<Text, Text>
{

    /* (non-Javadoc)
     * @see org.apache.hadoop.mapreduce.lib.input.FileInputFormat#isSplitable(org.apache.hadoop.mapreduce.JobContext, org.apache.hadoop.fs.Path)
     */
    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.apache.hadoop.mapreduce.InputFormat#createRecordReader(org.apache.hadoop.mapreduce.InputSplit, org.apache.hadoop.mapreduce.TaskAttemptContext)
     */
    @Override
    public RecordReader<Text, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException
    {
        return new JavaFileRecordReader();
    }
}
