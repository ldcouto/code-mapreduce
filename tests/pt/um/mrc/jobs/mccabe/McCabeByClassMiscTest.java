package pt.um.mrc.jobs.mccabe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.JClassInputFormat;
import pt.um.mrc.util.io.JMcClassInputFormat;

public class McCabeByClassMiscTest
{
    private McCabeByClassMisc driver;
    
    @Before
    public void setUp() throws Exception
    {
        driver = new McCabeByClassMisc();
    }

    @Test
    public final void testGetInputFormatClass()
    {
        Class<?> expected = JMcClassInputFormat.class;
        
        Class<?> actual = driver.getInputFormatClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperClass()
    {
        Class<?> expected = McCabeMiscMapper.class;
        
        Class<?> actual = driver.getMapperClass();
        
        assertEquals(expected, actual);
    }

    @Test
    public final void testGetMapperKeyClass()
    {
    }

    @Test
    public final void testGetMapperValueClass()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetReducerClass()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetUsage()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetArgCount()
    {
        fail("Not yet implemented"); // TODO
    }

}
