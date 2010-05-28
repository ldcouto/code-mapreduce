package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.JFileInputFormat;

public class PkgAndClassChacheTest
{
    private PkgAndClassCache driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new PkgAndClassCache();
    }

    @Test
    public final void testGetInputFormatClass()
    {
        Class<?> expected = JFileInputFormat.class;
        
        Class<?> actual = driver.getInputFormatClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = PkgAndClassMapper.class;
        
        Class<?> actual = driver.getMapperClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyClass()
    {
        Class<?> expected = Text.class;
        
        Class<?> actual = driver.getMapperKeyOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperValueClass()
    {
        Class<?> expected = Text.class;
        
        Class<?> actual = driver.getMapperValueOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = PkgAndClassReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: PkgAndClassCache <in> <cache>";
        
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
