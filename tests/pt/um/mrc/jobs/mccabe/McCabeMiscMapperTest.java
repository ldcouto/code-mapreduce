package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ClassID;

public class McCabeMiscMapperTest
{
    private Mapper<WritableComparable<?>, Text, Text, IntWritable> mapper;
    private MapDriver<WritableComparable<?>, Text, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new McCabeMiscMapper();
        driver = new MapDriver<WritableComparable<?>, Text, Text, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
    {
        ClassID keyIn = new ClassID("Mapper","Mapper.java","mapreduce");
        
        Text keyOut = new Text(keyIn.toString());
        
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("    static{\n");
        sb.append("    if (a == b)");
        sb.append("    }\n");
        sb.append("}\n");
        
        Text valueIn = new Text(sb.toString());

        IntWritable valueOut = new IntWritable(2);
        
        driver.withInput(keyIn, valueIn);
        driver.withOutput(keyOut, valueOut);
        driver.runTest();
    }
}
