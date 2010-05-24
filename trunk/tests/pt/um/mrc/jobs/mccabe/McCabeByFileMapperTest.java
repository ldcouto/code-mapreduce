package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class McCabeByFileMapperTest
{
    private Mapper<LongWritable, Text, Text, IntWritable> mapper;
    private MapDriver<LongWritable, Text, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new McCabeByFileMapper();
        driver = new MapDriver<LongWritable, Text, Text, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
    {
        LongWritable inKey = new LongWritable(10);

        Text inValues = new Text(
                "pt.um.mrc.jobs.imprt-PkgAndClassMapper.java-PkgAndClassMapper\t5");

        Text outKey = new Text("pt.um.mrc.jobs.imprt-PkgAndClassMapper.java");

        IntWritable outValue = new IntWritable(5);

        driver.withInput(inKey, inValues);
        driver.withOutput(outKey, outValue);
        driver.runTest();
    }
}
