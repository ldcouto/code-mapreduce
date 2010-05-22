package pt.um.mrc.jobs.mccabe;


import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;

public class McCabeByMethodReducerTest
{
    private Reducer<MethodID, IntWritable, MethodID, IntWritable> reducer;
    private ReduceDriver<MethodID, IntWritable, MethodID, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new McCabeByMethodReducer();
        driver = new ReduceDriver<MethodID, IntWritable, MethodID, IntWritable>(reducer);
    }
    
    @Test
    public void testReduce()
    {
        ArrayList<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(10));
        values.add(new IntWritable(20));
        
        MethodID aux = new MethodID("foo", "bar", "foo.java", "foo.bar");
        
        driver.withInput(aux, values);
        driver.withOutput(aux, new IntWritable(30));
        driver.runTest();
    }

}
