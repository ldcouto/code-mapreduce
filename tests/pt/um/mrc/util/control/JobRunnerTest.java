package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.hadoop.conf.Configuration;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.jobs.volume.VolumeByFile;

public class JobRunnerTest
{
    VolumeByFile volJob;
    String[] args;

    @Before
    public void setUp() throws Exception
    {
        volJob = new VolumeByFile();
        String command = "arg1 arg2 arg3";
        args = command.split("\\s");
    }

    @Test
    public final void testConstructor()
    {
        JobRunner jr = new JobRunner();

        assertNotNull(jr);
        assertTrue(jr instanceof JobRunner);
    }

    @Test
    public final void testSetJob() throws Exception
    {
        Class<?> expectedMapper = volJob.getMapperClass();
        Class<?> expectedMapperKey = volJob.getMapperKeyClass();
        Class<?> expectedMapperValue = volJob.getMapperValueClass();
        Class<?> expectedInputFormatClass = volJob.getInputFormatClass();
        Class<?> expectedReducer = volJob.getReducerClass();
        String expectedUsage = volJob.getUsage();
        Configuration expectedConfiguration = new Configuration();

        JobRunner.setJob(args, volJob);

        Class<?> actualMapper = JobRunner.job.getMapperClass();
        Class<?> actualMapperKey = JobRunner.job.getMapOutputKeyClass();
        Class<?> actualMapperValue = JobRunner.job.getMapOutputValueClass();
        Class<?> actualInputFormatClass = JobRunner.job.getInputFormatClass();
        Class<?> actualReducer = JobRunner.job.getReducerClass();
        String actualUsage = JobRunner.cji.getUsageMessage();
        Configuration actualConfiguration = JobRunner.getConf();

        assertEquals(expectedMapper, actualMapper);
        assertEquals(expectedMapperKey, actualMapperKey);
        assertEquals(expectedMapperValue, actualMapperValue);
        assertEquals(expectedInputFormatClass, actualInputFormatClass);
        assertEquals(expectedReducer, actualReducer);
        assertEquals(expectedUsage, actualUsage);
        assertEquals(expectedConfiguration.toString(), actualConfiguration.toString());
    }
}
