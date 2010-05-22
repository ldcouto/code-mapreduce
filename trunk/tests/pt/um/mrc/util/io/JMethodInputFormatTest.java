package pt.um.mrc.util.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;

public class JMethodInputFormatTest
{
    private JMethodInputFormat javamethod;

    @Before
    public void setUp() throws Exception
    {
        javamethod = new JMethodInputFormat();
    }

    @Test
    public final void testIsSplitable()
    {
        JobContext context = new JobContext(new Configuration(), new JobID());
        Path filename = new Path("Some File");

        boolean result = javamethod.isSplitable(context, filename);

        assertFalse(result);
    }

    @Test
    public final void testCreateRecordReader() throws IOException, InterruptedException
    {
        InputSplit split = null;
        TaskAttemptContext context = null;

        RecordReader<MethodID, Text> result;

        result = javamethod.createRecordReader(split, context);

        assertNotNull(result);
        assertTrue(result instanceof JMethodRecordReader);
    }
}
