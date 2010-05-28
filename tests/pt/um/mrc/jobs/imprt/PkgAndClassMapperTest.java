package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.FileID;

public class PkgAndClassMapperTest
{
    private Mapper<FileID, Text, Text, Text> mapper;
    private MapDriver<FileID, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new PkgAndClassMapper();
        driver = new MapDriver<FileID, Text, Text, Text>(mapper);
    }

    @Test
    public final void testMap()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("package pt.um.mrc.jobs.imprt;\n");
    	sb.append("public class Foo_Bar{}\n");
    	sb.append("public class Foo_Bar2{}");
        driver.withInput(new FileID("File1.java", "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));
        
        driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text("pt.um.mrc.jobs.imprt.Foo_Bar"));
        driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text("pt.um.mrc.jobs.imprt.Foo_Bar2"));
        driver.runTest();
    }
}
