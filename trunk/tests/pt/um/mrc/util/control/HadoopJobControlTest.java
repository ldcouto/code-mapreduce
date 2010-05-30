package pt.um.mrc.util.control;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.jobs.pckg.PackageByFileMapper;
import pt.um.mrc.jobs.pckg.PackageByFileReducer;
import pt.um.mrc.util.io.iformats.JavaFileInputFormat;

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
		String inputPath = "some/dir";
		String outputPath = "other/dir";
		String[] args = {inputPath, outputPath};

		JobConfigHolder jc = new JobConfigHolder(classJar, inputFormat, args);

		MapperConfigHolder mc =
			new MapperConfigHolder(PackageByFileMapper.class, Text.class, Text.class);

		HadoopJobControl.configureSimpleJob(job, jc, mc, PackageByFileReducer.class);

		assertEquals(PackageByFileMapper.class, job.getMapperClass());
		assertEquals(PackageByFileReducer.class, job.getReducerClass());
		assertEquals(Text.class, job.getMapOutputKeyClass());
		assertEquals(Text.class, job.getMapOutputValueClass());
		assertEquals(JavaFileInputFormat.class, job.getInputFormatClass());

		// Can't compare this. While setting the input path, the path given is resolved to a full path
		//assertEquals(inputPath, FileInputFormat.getInputPaths(job).toString());
		assertEquals(outputPath, FileOutputFormat.getOutputPath(job).toString());
	}

	@Test
	public final void testCheckArguments() throws Exception {
		String[] expectedArgs = { "<in>", "<out>" };
		CheckedJobInfo cji = new CheckedJobInfo("Some usage message", new Configuration(), 2);

		String[] actualArgs = HadoopJobControl.checkArguments(expectedArgs, cji);

		assertArrayEquals(expectedArgs, actualArgs);
	}

}
