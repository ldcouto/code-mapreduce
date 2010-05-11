package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class FindPackagesMapperTest
{
    private Mapper<Text, Text, Text, Text> mapper;
    private MapDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new FindPackagesMapper();
        driver = new MapDriver<Text, Text, Text, Text>(mapper);
    }

    @Test
    public final void testMapTextTextContext()
    {
        driver.withInput(new Text("File1.java"), new Text("package pt.um.mrc.jobs.imprt;"));
        driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text(""));
        driver.runTest();
    }
}
