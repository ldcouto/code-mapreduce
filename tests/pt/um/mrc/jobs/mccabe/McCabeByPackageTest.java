package pt.um.mrc.jobs.mccabe;

import static org.junit.Assert.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.junit.Before;
import org.junit.Test;

public class McCabeByPackageTest
{

    private McCabeByPackage driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new McCabeByPackage();
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: McCabeByPackage <in> <out>";
        
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = McCabeByPackageMapper.class;
    
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
        Class<?> expected = IntWritable.class;
        
        Class<?> actual = driver.getMapperValueOutClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetInputFormatClass()
    {
        Class<?> expected = TextInputFormat.class;
        
        Class<?> actual = driver.getInputFormatClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = McCabeByPackageReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }
}
