package pt.um.mrc.util.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaFileInputFormatTest
{
    private JavaFileInputFormat javafile;

    @Before
    public void setUp() throws Exception
    {
        javafile = new JavaFileInputFormat();
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void testIsSplitable_JobContext_Path()
    {
        JobContext context = new JobContext(new Configuration(), new JobID());
        Path filename = new Path("Some File");

        boolean result = javafile.isSplitable(context, filename);

        assertFalse(result);
    }

    @Test
    public final void testCreateRecordReader_InputSplit_TaskAttemptContext() throws IOException, InterruptedException
    {
        InputSplit split = null;
        TaskAttemptContext context = null;

        RecordReader<Text, Text> result;

        result = javafile.createRecordReader(split, context);

        assertNotNull(result);
        assertTrue(result instanceof JavaFileRecordReader);
    }

}
