package pt.um.mrc.jobs.volume2;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

public class VolumeByFileTest
{
    private VolumeByFile driver;

    @Before
    public void setUp() throws Exception
    {
        driver = new VolumeByFile();
    }

    @Test
    public final void testGetArgCount()
    {
        int expected = 2;
        int actual = driver.getArgCount();

        assertEquals(expected, actual);
    }

    @Test
    public final void testGetInputFormatClass()
    {
        Class<?> expected = JavaFileInputFormat.class;
        Class<?> actual = driver.getInputFormatClass();

        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = VolumeByFileMapper.class;
        Class<?> actual = driver.getMapperClass();

        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyOutClass()
    {
        Class<?> expected = Text.class;
        Class<?> actual = driver.getMapperKeyOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperValueOutClass()
    {
        Class<?> expected = IntWritable.class;
        Class<?> actual = driver.getMapperValueOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = VolumeByFileReducer.class;
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: VolumeByFile <in> <out>";
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }
}
