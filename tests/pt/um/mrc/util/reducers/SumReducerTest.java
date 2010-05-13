package pt.um.mrc.util.reducers;

import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class SumReducerTest
{
    private Reducer<Text, IntWritable, Text, IntWritable> reducer;
    private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new SumReducer<Text, IntWritable, Text, IntWritable>();
        driver = new ReduceDriver<Text,IntWritable,Text,IntWritable>(reducer);
    }

    @Test
    public final void testReduceKIIterableOfIntWritableContext()
    {
        ArrayList<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));

        driver.withInput(new Text("File1.java"), values);
        driver.withOutput(new Text("File1.java"), new IntWritable(3));
        driver.runTest();
    }

}
