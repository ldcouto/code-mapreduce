package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.FileID;

public class PackageByFileMapperTest
{

    private Mapper<FileID, Text, Text, Text> mapper;
    private MapDriver<FileID, Text, Text, Text> driver;
    
    @Before
    public void setUp() throws Exception
    {
        mapper = new PackageByFileMapper();
        driver = new MapDriver<FileID, Text, Text, Text>(mapper);
    }

    @Test
    public final void testMap()
    {
        driver.withInput(new FileID("File1.java", "package pt.um.mrc.jobs.pckg;"), new Text("package pt.um.mrc.jobs.pckg;"));
        driver.withOutput(new Text("File1.java"), new Text("pt.um.mrc.jobs.pckg"));
        driver.runTest();
    }
}
