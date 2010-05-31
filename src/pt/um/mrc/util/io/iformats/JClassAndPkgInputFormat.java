package pt.um.mrc.util.io.iformats;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.rr.JClassAndPkgRecordReader;

// TODO: Auto-generated Javadoc
/**
 * The Class JClassAndPkgInputFormat.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JClassAndPkgInputFormat extends FileInputFormat<ClassID, Text>
{

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.hadoop.mapreduce.lib.input.FileInputFormat#isSplitable(org
     * .apache.hadoop.mapreduce.JobContext, org.apache.hadoop.fs.Path)
     */
    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.hadoop.mapreduce.InputFormat#createRecordReader(org.apache
     * .hadoop.mapreduce.InputSplit,
     * org.apache.hadoop.mapreduce.TaskAttemptContext)
     */
    @Override
    public RecordReader<ClassID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException
    {
        return new JClassAndPkgRecordReader();
    }

}
