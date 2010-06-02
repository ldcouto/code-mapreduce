package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class VolumeByPackageMapperTest
{
    private Mapper<Text, Text, Text, IntWritable> mapper;
    private MapDriver<Text, Text, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new VolumeByPackageMapper();
        driver = new MapDriver<Text, Text, Text, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
    {
        Text keyIn = new Text("file1.java");
        Text keyOut = new Text("test");

        StringBuilder sb = new StringBuilder();
        sb.append("package test;");
        sb.append("public Class test{\n");
        sb.append("       \n");
        sb.append("   String A = \"Hello World\";\n");
        sb.append("}\n");

        Text inValue = new Text(sb.toString());

        IntWritable outValue = new IntWritable(3);

        driver.withInput(keyIn, inValue);
        driver.withOutput(keyOut, outValue);
        driver.runTest();
    }
}
