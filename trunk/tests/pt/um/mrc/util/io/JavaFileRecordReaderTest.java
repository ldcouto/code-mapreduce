package pt.um.mrc.util.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper.MockMapContext;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.hadoop.util.LineReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaFileRecordReaderTest {
	JavaFileRecordReader javaRRGoodFile;
	JavaFileRecordReader javaRREmptyFile;
	JavaFileRecordReader javaRREmptyFile1Char;
	FileSplit fileSplitGood;
	FileSplit fileSplitEmpty;
	FileSplit fileSplitEmpty1Char;
	MockMapContextWrapper<Text, Text, Text, Text> mockContext;
	MockMapContext mapMockContext;
	TaskAttemptContext tac;
	List<Pair<Text, Text>> inputs;
	Counters counters;

	@Before
	public void setUp() throws Exception {
		fileSplitGood = new FileSplit(new Path("somefile"), 0, 149, null);
		fileSplitEmpty1Char = new FileSplit(new Path("somefile_empty"), 0, 1,
				null);
		fileSplitEmpty = new FileSplit(new Path("somefile_empty"), 0, 0, null);
		Text foo = new Text("foo");
		Text bar = new Text("bar");
		Pair<Text, Text> p1 = new Pair<Text, Text>(foo, bar);
		inputs = new ArrayList<Pair<Text, Text>>();
		inputs.add(p1);

		counters = new Counters();
		mockContext = new MockMapContextWrapper<Text, Text, Text, Text>();

		mapMockContext = mockContext.getMockContext(inputs,
				counters);

		tac = new TaskAttemptContext(mapMockContext.getConfiguration(),
				mapMockContext.getTaskAttemptID());

		javaRRGoodFile = new JavaFileRecordReader();
		javaRRGoodFile.initialize(fileSplitGood, tac);

		javaRREmptyFile1Char = new JavaFileRecordReader();
		javaRREmptyFile1Char.initialize(fileSplitEmpty1Char, tac);

		javaRREmptyFile = new JavaFileRecordReader();
		javaRREmptyFile.initialize(fileSplitEmpty, tac);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInitialize_InputSplit_TaskAttemptContext()
			throws IOException, InterruptedException {
		JavaFileRecordReader jrrToTest = new JavaFileRecordReader();
		jrrToTest.initialize(fileSplitEmpty, tac);
		Text expectedKey = null;
		Text expectedValue = null;
		long expectedStart = 0;
		long expectedPos = 0;
		long expectedEnd = 0;
		FileSplit expectedSplit = fileSplitEmpty;
		
		Configuration auxJob=mapMockContext.getConfiguration();
		CompressionCodecFactory expectedFactory = new CompressionCodecFactory(auxJob);
		Path auxPath=new Path("somefile");
		
		FileSystem auxFS =  auxPath.getFileSystem(auxJob);
		LineReader expectedReader = new LineReader(auxFS.open(auxPath), auxJob);
		
		Text actualKey = jrrToTest.getKey();
		Text actualValue = jrrToTest.getValue();
		long actualStart = jrrToTest.getStart();
		long actualPos = jrrToTest.getPos();
		long actualEnd = jrrToTest.getEnd();
		FileSplit actualSplit = jrrToTest.getSplit();
		CompressionCodecFactory actualFactory = jrrToTest.getCompressionCodecs();
		LineReader actualReader = jrrToTest.getIn();
		
		// TODO: Codecs and line reader lue
		
		Assert.assertEquals(expectedKey, actualKey);
		Assert.assertEquals(expectedValue, actualValue);
		Assert.assertEquals(expectedStart, actualStart);
		Assert.assertEquals(expectedPos, actualPos);
		Assert.assertEquals(expectedEnd, actualEnd);
		Assert.assertEquals(expectedSplit, actualSplit);
		//Assert.assertSame(expectedFactory, actualFactory);
		//Assert.assertEquals(expectedReader, actualReader);
	}

	@Test
	public final void testNextKeyValue_NormalFile() throws IOException,
			InterruptedException {
		boolean expected = true;
		boolean actual = javaRRGoodFile.nextKeyValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testNextKeyValue_EmptyFile() throws IOException,
			InterruptedException {
		boolean expected = false;
		boolean actual = javaRREmptyFile1Char.nextKeyValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetCurrentKey() throws IOException,
			InterruptedException {
		javaRRGoodFile.nextKeyValue();
		Text expected = new Text("somefile");
		Text actual = javaRRGoodFile.getCurrentKey();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetCurrentValue() throws IOException,
			InterruptedException {
		javaRRGoodFile.nextKeyValue();
		Text expected = new Text(
				"package pt.um.mrc.util.reducers;import java.util.ArrayList;public class IdentityReducerTest {	private Reducer<Text,Text,Text,Text> reducer;}");
		Text actual = javaRRGoodFile.getCurrentValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetProgress_Start() throws IOException,
			InterruptedException {
		float expected = 0f;
		float actual = javaRREmptyFile.getProgress();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetProgress_Underway() throws IOException,
			InterruptedException {
		float expected = 1f;
		javaRRGoodFile.nextKeyValue();
		float actual = javaRRGoodFile.getProgress();
		Assert.assertEquals(expected, actual);

	}

	@Test(expected = NullPointerException.class)
	public final void testClose() throws InterruptedException, IOException {
		javaRRGoodFile.close();
		javaRRGoodFile.nextKeyValue();
	}
}
