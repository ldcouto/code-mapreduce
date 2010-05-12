package pt.um.mrc.jobs.imprt;


import static org.junit.Assert.fail;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ArrayWritablePrintable;

public class ImportsByPackageReducerTest
{

    private Reducer<Text, Text, Text, ArrayWritablePrintable> reducer;
    private ReduceDriver<Text, Text, Text, ArrayWritablePrintable> driver;

    @Before
    public void setUp() throws Exception
    {
        reducer = new ImportsByPackageReducer();
        driver = new ReduceDriver<Text, Text, Text, ArrayWritablePrintable>(reducer);
    }
    
    @Test
    public final void testReduce()
    {
        fail("Not yet implemented"); // TODO Can't test CollectionReducer.
    }

}
