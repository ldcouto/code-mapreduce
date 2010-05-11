package pt.um.mrc.jobs.pckg;

import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class PackageByFileReducerTest
{
    private Reducer<Text, Text, Text, Text> reducer;
    private ReduceDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new PackageByFileReducer();
        driver = new ReduceDriver<Text, Text, Text, Text>(reducer);
    }

    @Test
    public final void testReduce()
    {
        ArrayList<Text> values = new ArrayList<Text>();
        values.add(new Text("pt.um.mrc.jobs.pckg"));

        driver.withInput(new Text("File1.java"), values);
        driver.withOutput(new Text("File1.java"), new Text("pt.um.mrc.jobs.pckg"));
        driver.runTest();
    }

}
