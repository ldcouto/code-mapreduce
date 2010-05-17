package pt.um.mrc.util.reducers;

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
import pt.um.mrc.util.datatypes.StringAWP;

public class CollectionReducerTest {
	private Reducer<Text, Text, Text, StringAWP> reducer;
	private ReduceDriver<Text, Text, Text, StringAWP> driver;

	@Before
	public void setUp() throws Exception {
		reducer = new Reducer<Text, Text, Text, StringAWP>();
		driver = new ReduceDriver<Text, Text, Text, StringAWP>(reducer);
			}

	@Test
	public void testReduce() throws IOException{
		Assert.fail("Not yet implemented!");
	}
	
	@Test
	// FIXME this is a "native" problem with hadoop and MRUNIT. It relates to
	// collect() and the Heap Space. There also seems to be some a problem with
	// EOFs when deserealising datainputs.
	public final void testReduceBad() throws IOException {
		// fail("Not yet implemented.");

		String[] strings;
		strings = new String[2];
		strings[0] = "one";
		strings[1]= "two";


		StringAWP expectedAWP =
			new StringAWP(strings);

		Pair<Text, StringAWP> expected =
			new Pair<Text, StringAWP>(new Text("numbers"),
				expectedAWP);

		driver.setInputKey(new Text("numbers"));
		driver.addInputValue(new Text ("one"));
		driver.addInputValue(new Text("two"));
		
		List<Pair<Text, StringAWP>> actual = driver.run();
		Assert.assertEquals(expected, actual);
		
	}
}
