package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class PackageByFileMapperTest
{

    private Mapper<Text, Text, Text, Text> mapper;
    private MapDriver<Text, Text, Text, Text> driver;
    
    @Before
    public void setUp() throws Exception
    {
        mapper = new PackageByFileMapper();
        driver = new MapDriver<Text, Text, Text, Text>(mapper);
    }

    @Test
    public final void testMap()
    {
        driver.withInput(new Text("File1.java"), new Text("package pt.um.mrc.jobs.pckg;"));
        driver.withOutput(new Text("File1.java"), new Text("pt.um.mrc.jobs.pckg"));
        driver.runTest();
    }

}
