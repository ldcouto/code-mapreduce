package pt.um.mrc.util.mappers;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineValuesMapperTest {

    private Mapper<LongWritable, Text, Text, IntWritable> mapper;
    private MapDriver<LongWritable, Text, Text, IntWritable> driver;

	
	@Before
	public void setUp() throws Exception {
		mapper = new LineValuesMapper<LongWritable,Text,Text,IntWritable>();
		driver = new MapDriver<LongWritable, Text, Text, IntWritable>(mapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapLongWritableTextContext() {
		LongWritable inKey = new LongWritable(10);

		Text inValues =
			new Text(
				"pt.um.mrc.jobs.imprt-FindPackagesMapper.java-FindPackagesMapper-map[Text key, Text value, Context context]\t5");

		Text outKey = new Text("pt.um.mrc.jobs.imprt-FindPackagesMapper.java-FindPackagesMapper");

		IntWritable outValue = new IntWritable(5);

		driver.withInput(inKey, inValues);
		driver.withOutput(outKey, outValue);
		driver.runTest();
	}
	

}
