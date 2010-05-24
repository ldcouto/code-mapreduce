package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;


public class FindPackagesMapReduceTest
{
    private Mapper<Text, Text, Text, Text> mapper;
    private Reducer<Text, Text, Text, Text> reducer;
    private MapReduceDriver<Text, Text, Text, Text, Text, Text> driver;
    
    @Before
    public void setUp() throws Exception
    {
        mapper = new PkgAndClassMapper();
        reducer = new PkgAndClassReducer();
        driver = new MapReduceDriver<Text, Text, Text, Text, Text, Text>(mapper,reducer);
    }
    
    @Test
    public final void testMapReduce()
    {
        driver.withInput(new Text("File1.java"), new Text("package pt.um.mrc.jobs.imprt;"));
        driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text(""));
        driver.runTest();
    }
}
