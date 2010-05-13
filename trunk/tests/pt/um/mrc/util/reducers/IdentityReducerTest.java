package pt.um.mrc.util.reducers;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;

public class IdentityReducerTest {

	private Reducer<Text,Text,Text,Text> reducer;
	private ReduceDriver<Text,Text,Text,Text> driver;
	private List<Text> vis = new ArrayList<Text>();
	private List<Text> vos = new ArrayList<Text>();
	
	
	@Before
	public void setUp() throws Exception {
		reducer = new Reducer<Text,Text,Text,Text>();
		driver  = new ReduceDriver<Text,Text,Text,Text>(reducer);
		vis.add(new Text("bar"));
		vos.add(new Text("bar"));
	}


	@Test
	public final void testReduceKIIterableOfVIContext() {
		driver.withInput(new Text("foo"), vis).withOutput(new Text("foo"), new Text("bar")).runTest();

	}

}
