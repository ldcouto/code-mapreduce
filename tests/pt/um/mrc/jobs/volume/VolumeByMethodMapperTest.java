package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;

public class VolumeByMethodMapperTest
{
    private Mapper<MethodID, Text, MethodID, IntWritable> mapper;
    private MapDriver<MethodID, Text, MethodID, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new VolumeByMethodMapper();
        driver = new MapDriver<MethodID, Text, MethodID, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
    {
        MethodID key = new MethodID("helloWorld[]","HelloWorld","HelloWorld.java","test");
        
        StringBuilder sb = new StringBuilder();
        sb.append("public void helloWorld(){\n");
        sb.append("   System.out.println(\"Hello World\")\n");
        sb.append("}\n");
        
        Text inValue = new Text(sb.toString());
        
        IntWritable outValue = new IntWritable(3);
        
        driver.withInput(key, inValue);
        driver.withOutput(key, outValue);
        driver.runTest();
    }

}
