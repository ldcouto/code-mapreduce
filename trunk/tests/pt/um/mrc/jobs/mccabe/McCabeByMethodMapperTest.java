package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;

public class McCabeByMethodMapperTest
{
    private Mapper<MethodID, Text, MethodID, IntWritable> mapper;
    private MapDriver<MethodID, Text, MethodID, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new McCabeByMethodMapper();
        driver = new MapDriver<MethodID, Text, MethodID, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
    {        
        StringBuilder sb = new StringBuilder();
        sb.append("public void manyControlStatements(){\n");
        sb.append("    if (a == b && c == d || a == c)\n");
        sb.append("    while (true)\n");
        sb.append("    do{ i++; }while(true)\n");
        sb.append("    case a :\n");
        sb.append("    catch (Exception e)\n");
        sb.append("    for (int i; i< 20; i++ )\n");
        sb.append("}\n");
        
        MethodID key = new MethodID("manyControlStatements", "SomeClass", "SomeClass.java", "test");
        
        driver.withInput(key, new Text(sb.toString()));
        driver.withOutput(key, new IntWritable(8));
        driver.runTest();
    }
}
