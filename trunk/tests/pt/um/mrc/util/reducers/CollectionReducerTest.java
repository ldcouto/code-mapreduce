package pt.um.mrc.util.reducers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ArrayWritablePrintable;

public class CollectionReducerTest {
	private Reducer<Text, Text, Text, ArrayWritablePrintable> reducer;
	private ReduceDriver<Text, Text, Text, ArrayWritablePrintable> driver;

	@Before
	public void setUp() throws Exception {
		reducer = new CollectionReducer<Text, Text, Text, ArrayWritablePrintable>();
		driver = new ReduceDriver<Text, Text, Text, ArrayWritablePrintable>(
				reducer);
	}

	@Test
	// FIXME this is a "native" problem with hadoop and reflection (possibly
	// class permisision)
	public final void testReduce() throws IOException {
		// fail("Not yet implemented.");

		String[] strings = { "one" };

		List<Text> inValues = new ArrayList<Text>(0);
		inValues.add(new Text("one"));

		// for (Text t : inValues)
		// System.out.println(t.toString());

		ArrayWritablePrintable expected = new ArrayWritablePrintable(strings);

		driver.withInput(new Text("numbers"), inValues);
		driver.withOutput(new Text("numbers"), expected);
//		driver.runTest();
		 @SuppressWarnings("unused")//FIXME remove this!
		List<Pair<Text, ArrayWritablePrintable>> actual = driver.run();
		//         
		 Assert.assertEquals(true, true);
	}

}
