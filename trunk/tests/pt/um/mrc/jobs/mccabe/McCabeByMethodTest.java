package pt.um.mrc.jobs.mccabe;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.IntWritable;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.iformats.JMethodInputFormat;

public class McCabeByMethodTest
{
    private McCabeByMethod driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new McCabeByMethod();
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: McCabeByMethod <in> <out>";
        
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = McCabeByMethodMapper.class;
        
        Class<?> actual = driver.getMapperClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyClass()
    {
        Class<?> expected = MethodID.class;
        
        Class<?> actual = driver.getMapperKeyOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperValueClass()
    {
        Class<?> expected = IntWritable.class;
        
        Class<?> actual = driver.getMapperValueOutClass();
        
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
        Class<?> expected = McCabeByMethodReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
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
