package pt.um.mrc.util.io.iformats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.iformats.JMcClassInputFormat;
import pt.um.mrc.util.io.rr.JMcClassRecordReader;

public class JMcClassInputFormatTest
{
    private JMcClassInputFormat jmccif;
    private JobContext context;
    private Path filename;

    @Before
    public void setUp() throws Exception
    {
        jmccif = new JMcClassInputFormat();
        context = new JobContext(new Configuration(), new JobID());
        filename = new Path("some/file");
    }

    @Test
    public final void testIsSplitable()
    {
        boolean expected = false;

        boolean actual = jmccif.isSplitable(context, filename);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCreateRecordReader() throws Exception
    {
        InputSplit split = null;
        TaskAttemptContext context = null;

        RecordReader<ClassID, Text> actual = jmccif.createRecordReader(split, context);

        assertNotNull(actual);
        assertTrue(actual instanceof JMcClassRecordReader);
    }
}
