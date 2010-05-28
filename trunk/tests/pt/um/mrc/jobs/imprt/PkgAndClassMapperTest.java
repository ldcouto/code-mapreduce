package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class PkgAndClassMapperTest {

	private Mapper<Text, Text, Text, Text> mapper;
	private MapDriver<Text, Text, Text, Text> driver;

	@Before
	public void setUp() throws Exception {
		mapper = new PkgAndClassMapper();
		driver = new MapDriver<Text, Text, Text, Text>(mapper);
	}

	@Test
	public final void testMap() {
		StringBuilder sb = new StringBuilder();
		sb.append("package pt.um.mrc.jobs.imprt;\n");
		sb.append("public class Foo_Bar{}\n");
		sb.append("public class Foo_Bar2{}");
		driver.withInput(new Text("pt.um.mrc.jobs.imprt-File1.java"), new Text(sb.toString()));

		driver.withOutput(new Text("pt.um.mrc.jobs.imprt"),
				new Text("pt.um.mrc.jobs.imprt.Foo_Bar"));
		driver.withOutput(new Text("pt.um.mrc.jobs.imprt"), new Text(
				"pt.um.mrc.jobs.imprt.Foo_Bar2"));
		driver.runTest();
	}
}
