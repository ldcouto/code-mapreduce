package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

public class ImportsByPackageReducerTest
{

    private Reducer<Text, Text, Text, CollectionWritablePrintable> reducer;
    private ReduceDriver<Text, Text, Text, CollectionWritablePrintable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new ImportsByPackageReducer();
        driver = new ReduceDriver<Text, Text, Text, CollectionWritablePrintable>(reducer);
    }

    @Test
    public final void testReduce() throws IOException
    {
        ArrayList<Text> valuesIn = new ArrayList<Text>();
        valuesIn.add(new Text("pt.um.mrc.util.datatypes.TextArrayWritablePrintable"));
        valuesIn.add(new Text("pt.um.mrc.util.reducers.TextCollectionReducer"));

        Text keyIn = new Text("package pt.um.mrc.jobs.imprt");

        Text[] expectedOutputValue = {
                new Text("pt.um.mrc.util.datatypes.TextArrayWritablePrintable"),
                new Text("pt.um.mrc.util.reducers.TextCollectionReducer") };

        driver.withInput(keyIn, valuesIn);
        Pair<Text, CollectionWritablePrintable> actualOutput = driver.run().get(0);

        assertEquals(keyIn, actualOutput.getFirst());
        assertArrayEquals(expectedOutputValue, actualOutput.getSecond().get());
    }

}
