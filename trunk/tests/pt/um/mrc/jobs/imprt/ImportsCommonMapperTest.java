package pt.um.mrc.jobs.imprt;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;
import pt.um.mrc.util.mappers.PackageInfoMapper;


public class ImportsCommonMapperTest {
	ArrayList<String> packages;
	ImportsCommonMapper<Text, Text, Text, Text> mapper;
	MapDriver<Text, Text, Text,Text> driver;

	@Before
	public void setUp() throws Exception {
		packages = new ArrayList<String>();
		packages.add("foo.bar");
		packages.add("foo.bar2");
		mapper = new ImportsCommonMapper<Text, Text, Text, Text>();
		driver = new MapDriver<Text,Text,Text,Text>(mapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapTextTextContext() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("import foo.*; \n");
		sb.append("import java.util.*");
		driver.withInput(new Text("f1.java"), new Text(sb.toString()));
		mapper.setInternalPackages(packages);
		
		List<Pair<Text,Text>> actual = new ArrayList<Pair<Text,Text>>();
		List<Pair<Text,Text>> expected = new ArrayList<Pair<Text,Text>>();
		
		expected.add(new Pair<Text,Text>(new Text("f1.java"), new Text("foo.bar")));
		expected.add(new Pair<Text,Text>(new Text("f1.java"), new Text("foo.bar2")));
		
		actual = driver.run();
		
		Assert.assertEquals(expected, actual);
		
	}

}
