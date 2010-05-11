package pt.um.mrc.jobs.imprt;

import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class FindPackagesReducerTest
{
    private Reducer<Text, Text, Text, Text> reducer;
    private ReduceDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new FindPackagesReducer();
        driver = new ReduceDriver<Text, Text, Text, Text>(reducer);
    }

    @Test
    public final void testReduce()
    {
        ArrayList<Text> values = new ArrayList<Text>();
        values.add(new Text(""));
        
        driver.withInput(new Text("pt.um.mrc.jobs.imprt"), values);
        driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text(""));
        driver.runTest();
    }
}
