package pt.um.mrc.jobs.allmetrics;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;


public class JEverythingInputFormat extends FileInputFormat<ElemID, Text>
{

    @Override
    protected boolean isSplitable(JobContext context, Path filename)
    {
        return false;
    }
    
    @Override
    public RecordReader<ElemID, Text> createRecordReader(InputSplit arg0, TaskAttemptContext arg1)
            throws IOException, InterruptedException
    {
        return new JEverythingRecordReader();
    }

}
