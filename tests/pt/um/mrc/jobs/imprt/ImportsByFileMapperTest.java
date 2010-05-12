package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class ImportsByFileMapperTest
{
    private Mapper<Text, Text, Text, Text> mapper;
    private MapDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new ImportsByFileMapper();
        driver = new MapDriver<Text, Text, Text, Text>(mapper);
    }
    
    @Test
    public final void testSetUp()
    {
        // TODO doesn't work without the distrivuted cache.
        fail("Not yet implemented");
    } 

    @Test
    public final void testMapTextTextContext()
    {
        // TODO Doesn't work without the distributed cache.
        fail("Not yet implemented");
    }

}
