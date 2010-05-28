package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ClassID;

public class PkgAndClassMapperTest {

	private Mapper<ClassID, Text, Text, Text> mapper;
	private MapDriver<ClassID, Text, Text, Text> driver;

	@Before
	public void setUp() throws Exception {
		mapper = new PkgAndClassMapper();
		driver = new MapDriver<ClassID, Text, Text, Text>(mapper);
	}

	@Test
	public final void testMap() {
		
		ClassID c1 = new ClassID("Foo_Bar","File1.java","pt.um.mrc.jobs.imprt");
		driver.withInput(c1, new Text(""));
		driver.withOutput(new Text("pt.um.mrc.jobs.imprt"),
				new Text("pt.um.mrc.jobs.imprt.Foo_Bar"));
		driver.runTest();
	}
}
