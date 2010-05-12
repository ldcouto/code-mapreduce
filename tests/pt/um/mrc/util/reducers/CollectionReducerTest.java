package pt.um.mrc.util.reducers;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ArrayWritablePrintable;

public class CollectionReducerTest
{
    private Reducer<Text, Text, Text, ArrayWritablePrintable> reducer;
    private ReduceDriver<Text, Text, Text, ArrayWritablePrintable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new CollectionReducer<Text, Text, Text, ArrayWritablePrintable>();
        driver = new ReduceDriver<Text, Text, Text, ArrayWritablePrintable>(reducer);
    }

    @Test
    // FIXME I really don't know what's going on here.
    public final void testReduce()
    {
        String[] strings = { "one", "two", "three" };

        List<Text> inValues = new ArrayList<Text>();
        inValues.add(new Text("one"));
        inValues.add(new Text("two"));
        inValues.add(new Text("three"));

        ArrayWritablePrintable expected = new ArrayWritablePrintable(strings);

        driver.withInput(new Text("numbers"), inValues);
        driver.withOutput(new Text("numbers"), expected);
        driver.runTest();
    }

}
