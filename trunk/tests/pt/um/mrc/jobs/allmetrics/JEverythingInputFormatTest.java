package pt.um.mrc.jobs.allmetrics;

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

public class JEverythingInputFormatTest
{
    JEverythingInputFormat iformat;

    @Before
    public void setUp() throws Exception
    {
        iformat = new JEverythingInputFormat();
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void testIsSplitable()
    {
        JobContext context = new JobContext(new Configuration(), new JobID());
        Path filename = new Path("Some File");

        boolean result = iformat.isSplitable(context, filename);

        assertFalse(result);
    }

    @Test
    public void testCreateRecordReaderInputSplitTaskAttemptContext() throws IOException, InterruptedException
    {
        InputSplit split = null;
        TaskAttemptContext context = null;

        RecordReader<ElemID,Text> result;

        result = iformat.createRecordReader(split, context);

        assertNotNull(result);
        assertTrue(result instanceof JEverythingRecordReader);
    }
}
