package pt.um.mrc.jobs.volume;

import static org.junit.Assert.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.JClassInputFormat;

public class VolumeByClassMiscTest
{
    private VolumeByClassMisc driver;

    @Before
    public void setUp() throws Exception
    {
        driver = new VolumeByClassMisc();
    }

    @Test
    public final void testGetInputFormatClass()
    {
        Class<?> expected = JClassInputFormat.class;
        
        Class<?> actual = driver.getInputFormatClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = VolumeMiscMapper.class;
        
        Class<?> actual = driver.getMapperClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyClass()
    {
        Class<?> expected = Text.class;
        
        Class<?> actual = driver.getMapperKeyClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperValueClass()
    {
        Class<?> expected = IntWritable.class;
        
        Class<?> actual = driver.getMapperValueClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = VolumeByClassReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: VolumeByClassMisc <in> <out>";
        
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetArgCount()
    {
        int expected = 2;
        
        int actual = driver.getArgCount();
        
        assertEquals(expected, actual);
    }

}
