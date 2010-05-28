package pt.um.mrc.util.io;

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

import pt.um.mrc.util.datatypes.FileID;

public class JFileInputFormatTest
{
    private JFileInputFormat jfif;
    private JobContext context;
    private Path filename;
    
    
    @Before
    public void setUp() throws Exception
    {
        jfif = new JFileInputFormat();
        context = new JobContext(new Configuration(), new JobID());
        filename = new Path("some/file");
    }

    @Test
    public final void testIsSplitable()
    {
        boolean expected = false;
        
        boolean actual = jfif.isSplitable(context, filename);
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testCreateRecordReader() throws Exception
    {
        InputSplit split = null;
        TaskAttemptContext context = null;
        
        RecordReader<FileID, Text> actual = jfif.createRecordReader(split, context);
        
        assertNotNull(actual);
        assertTrue(actual instanceof JFileRecordReader);
    }

}
