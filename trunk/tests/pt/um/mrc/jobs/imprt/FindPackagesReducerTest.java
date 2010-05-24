package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

public class FindPackagesReducerTest
{
    private Reducer<Text, Text, Text, CollectionWritablePrintable> reducer;
    private ReduceDriver<Text, Text, Text, CollectionWritablePrintable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new PkgAndClassReducer();
        driver = new ReduceDriver<Text, Text, Text, CollectionWritablePrintable>(reducer);
    }

    @Test
    public final void testReduce() throws Exception
    {
        ArrayList<Text> values = new ArrayList<Text>();
        values.add(new Text("pt.um.mrc.jobs.imprt.ImportByFile"));
        
        Text[] aux = {new Text("pt.um.mrc.jobs.imprt.ImportByFile")};
        
        driver.withInput(new Text("pt.um.mrc.jobs.imprt"), values);
        List<Pair<Text,CollectionWritablePrintable>> actual = driver.run();
        
        assertArrayEquals(aux, actual.get(0).getSecond().get());
    }
}
