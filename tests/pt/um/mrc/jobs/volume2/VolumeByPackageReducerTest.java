package pt.um.mrc.jobs.volume2;

import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class VolumeByPackageReducerTest
{

    private Reducer<Text, IntWritable, Text, IntWritable> reducer;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new VolumeByPackageReducer();
        driver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
    }

    @Test
    public void testReduce()
    {
        ArrayList<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(10));
        values.add(new IntWritable(20));

        Text aux = new Text("foo");

        driver.withInput(aux, values);
        driver.withOutput(aux, new IntWritable(30));
        driver.runTest();
    }
}
