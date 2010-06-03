package pt.um.mrc.util.io.rr;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.visitors.MiscGrabberFileVisitor;

public class JFileRecordReaderTest
{

    @Before
    public void setUp() throws Exception
    {}

    @Test
    public void testJClassRecordReader()
    {

        JFileRecordReader jrr = new JFileRecordReader();

        Class<?> expected = MiscGrabberFileVisitor.class;

        Class<?> actual = jrr.visitor.getClass();

        assertEquals(expected, actual);
    }

}
