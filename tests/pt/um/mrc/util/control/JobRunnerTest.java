package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.jobs.volume.VolumeByFile;
import pt.um.mrc.jobs.volume.VolumeByFileMapper;


public class JobRunnerTest {
	VolumeByFile volJob;
	String[] args;
	
	@Before
	public void setUp() throws Exception {
		volJob = new VolumeByFile();
		String command ="foo.bar.JobClass/inputs/ /outputs/";
		args = command.split("\\s");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetJob() throws Exception {
		
		//TODO this test is cheating
		int expected =0;
		int actual = JobRunner.setJob(args, volJob);
		
		assertEquals(expected, actual);
		
	}

	@Test
	public void testRunJob() {
		fail("Not yet implemented"); // TODO
	}

}
