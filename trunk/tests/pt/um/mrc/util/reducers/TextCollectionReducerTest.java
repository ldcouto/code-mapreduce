package pt.um.mrc.util.reducers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

public class TextCollectionReducerTest
{
    private Reducer<Text, Text, Text, CollectionWritablePrintable> reducer;
    private ReduceDriver<Text, Text, Text, CollectionWritablePrintable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new CollectionReducer<Text, Text, Text, CollectionWritablePrintable>();
        driver = new ReduceDriver<Text, Text, Text, CollectionWritablePrintable>(reducer);
    }

    @Test
    public final void testReduce() throws IOException
    {
        List<Text> values = new ArrayList<Text>();
        values.add(new Text("one"));
        values.add(new Text("two"));
        
        Text[] strings = new Text[2];
        strings[0] = new Text("one");
        strings[1] = new Text("two");

        CollectionWritablePrintable expectedAWP = new CollectionWritablePrintable(Text.class,strings);

        Pair<Text, CollectionWritablePrintable> expected = new Pair<Text, CollectionWritablePrintable>(new Text("numbers"),
                expectedAWP);
        
        driver.withInput(new Text("numbers"), values);
        
        Pair<Text, CollectionWritablePrintable> actualOutput = driver.run().get(0);
                
        assertEquals(expected.getFirst(), actualOutput.getFirst());
        assertArrayEquals(expected.getSecond().get(), actualOutput.getSecond().get());
    }
}
