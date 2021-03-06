package pt.um.mrc.util.io.rr;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.rr.JMcClassRecordReader;

public class JMcClassRecordReaderTest
{
    @Before
    public void setUp() throws Exception
    {}

    @Test
    public final void testJMcClassRecordReader()
    {
        JMcClassRecordReader reader = new JMcClassRecordReader();
        
        assertNotNull(reader);
        assertTrue(reader instanceof JMcClassRecordReader);
    }
}
