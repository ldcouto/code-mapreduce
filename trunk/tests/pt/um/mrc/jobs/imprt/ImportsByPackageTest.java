package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.JavaFileInputFormat;

public class ImportsByPackageTest
{
    private ImportsByPackage driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new ImportsByPackage();
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
        Class<?> expected = ImportsByPackageMapper.class;
        
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
        Class<?> expected = Text.class;
        
        Class<?> actual = driver.getMapperValueClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetReducerClass()
    {
        Class<?> expected = ImportsByPackageReducer.class;
        
        Class<?> actual = driver.getReducerClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetUsage()
    {
        String expected = "Usage: ImportsByPackage <cache> <out>";
        
        String actual = driver.getUsage();
        
        assertEquals(expected, actual);
    }

}
