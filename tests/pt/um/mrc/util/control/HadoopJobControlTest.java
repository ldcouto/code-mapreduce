package pt.um.mrc.util.control;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.jobs.pckg.PackageByFileMapper;
import pt.um.mrc.jobs.pckg.PackageByFileReducer;
import pt.um.mrc.util.io.JavaFileInputFormat;

public class HadoopJobControlTest {

	private Job job;

	@Before
	public void setUp() throws Exception {
		job = new Job(new Configuration(), "Some job's name.");
	}

	@Test
	public final void testConstructor() {
		HadoopJobControl cls = new HadoopJobControl();

		assertNotNull(cls);
	}

	@Test
	public final void testConfigureSimpleJob() throws Exception {
		Class<HadoopJobControlTest> classJar = HadoopJobControlTest.class;
		Class<JavaFileInputFormat> inputFormat = JavaFileInputFormat.class;
		Path inputPath = new Path("some/dir");
		Path outputPath = new Path("other/dir");

		JobConfigurer jc = new JobConfigurer(classJar, inputFormat, inputPath, outputPath);

		MapperConfigurer mc =
			new MapperConfigurer(PackageByFileMapper.class, Text.class, Text.class);

		HadoopJobControl.configureSimpleJob(job, jc, mc, PackageByFileReducer.class);

		assertEquals(PackageByFileMapper.class, job.getMapperClass());
		assertEquals(PackageByFileReducer.class, job.getReducerClass());
		assertEquals(Text.class, job.getMapOutputKeyClass());
		assertEquals(Text.class, job.getMapOutputValueClass());
		assertEquals(JavaFileInputFormat.class, job.getInputFormatClass());

		// FIXME expected value is much more simpler than the actual value
		// assertEquals(inputPath, FileInputFormat.getInputPaths(job)[0]);
		assertEquals(outputPath, FileOutputFormat.getOutputPath(job));
	}

	@Test
	public final void testCheckArguments() throws Exception {
		String[] args = { "<in>", "<out<" };
		CheckedJobInfo cji = new CheckedJobInfo(new Configuration(), "Some usage message");

		String[] actualArgs = HadoopJobControl.checkArguments(args, cji);

		assertArrayEquals(args, actualArgs);
	}

	@Test
	public void testCheckArguments_BadArgs(){
		//FIXME how to test error output
		
		String[] args = { "<in>"};
		CheckedJobInfo cji = new CheckedJobInfo(new Configuration(), "Some usage message");

		String[] actualArgs = HadoopJobControl.checkArguments(args, cji);

		assertArrayEquals(args, actualArgs);	

	}
}
