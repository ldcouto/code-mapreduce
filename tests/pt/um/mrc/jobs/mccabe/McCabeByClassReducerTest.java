package pt.um.mrc.jobs.mccabe;


import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class McCabeByClassReducerTest
{

    private Reducer<Text, IntWritable, Text, IntWritable> reducer;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new McCabeByClassReducer();
        driver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
    }
    
    @Test
    public void testReduce()
    {
        ArrayList<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(10));
        values.add(new IntWritable(20));
        
        driver.withInput(new Text("some class"), values);
        driver.withOutput(new Text("some class"), new IntWritable(30));
        driver.runTest();
    }
}
