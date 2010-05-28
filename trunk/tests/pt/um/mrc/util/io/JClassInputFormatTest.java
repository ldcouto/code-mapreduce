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
import org.junit.Test;

import pt.um.mrc.util.datatypes.ClassID;


public class JClassInputFormatTest
{

    private JClassInputFormat jcfi = new JClassInputFormat();
    private JobContext context = new JobContext(new Configuration(), new JobID());
    private Path file= new Path("some file");
    
    @Test
    public final void testIsSplitable()
    {
        boolean expected = false;
        
        boolean actual = jcfi.isSplitable(context, file);
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testCreateRecordReader() throws Exception
    {
        InputSplit split = null;
        TaskAttemptContext context = null;

        RecordReader<ClassID, Text> result = jcfi.createRecordReader(split, context);

        assertNotNull(result);
        assertTrue(result instanceof JClassRecordReader);
    }

}
