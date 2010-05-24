package pt.um.mrc.jobs.volume;

import static org.junit.Assert.*;

import org.apache.hadoop.io.IntWritable;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.JMethodInputFormat;

public class VolumeByMethodTest
{
    private VolumeByMethod driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new VolumeByMethod();
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: VolumeByMethod <in> <out>";
        
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = VolumeByMethodMapper.class;
        
        Class<?> actual = driver.getMapperClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyClass()
    {
        Class<?> expected = MethodID.class;
        
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
    public final void testGetInputFormatClass()
    {
        Class<?> expected = JMethodInputFormat.class;
        
        Class<?> actual = driver.getInputFormatClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = VolumeByMethodReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }
}
