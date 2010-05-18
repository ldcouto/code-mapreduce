package pt.um.mrc.util.io;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import pt.um.mrc.util.datatypes.MethodID;

public class JMethodInputFormat extends FileInputFormat<MethodID, Text>
{
    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }

    @Override
    public RecordReader<MethodID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1)
            throws IOException, InterruptedException
    {
        return new JMethodRecordReader();
    }
}
