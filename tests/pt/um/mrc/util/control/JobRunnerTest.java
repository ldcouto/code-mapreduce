package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;

import org.junit.After;
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
        String command = "foo.bar.JobClass/inputs/ /outputs/";
        args = command.split("\\s");
    }

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public void testSetJob() throws Exception
    {
        Class<?> expectedMapper = volJob.getMapperClass();
        Class<?> expectedMapperKey = volJob.getMapperKeyClass();
        Class<?> expectedMapperValue = volJob.getMapperValueClass();
        Class<?> expectedInputFormatClass = volJob.getInputFormatClass();
        Class<?> expectedReducer = volJob.getReducerClass();
        String expectedUsage = volJob.getUsage();

        JobRunner.setJob(args, volJob);

        Class<?> actualMapper = JobRunner.job.getMapperClass();
        Class<?> actualMapperKey = JobRunner.job.getMapOutputKeyClass();
        Class<?> actualMapperValue = JobRunner.job.getMapOutputValueClass();
        Class<?> actualInputFormatClass = JobRunner.job.getInputFormatClass();
        Class<?> actualReducer = JobRunner.job.getReducerClass();
        String actualUsage = JobRunner.cji.getUsageMessage();

        assertEquals(expectedMapper, actualMapper);
        assertEquals(expectedMapperKey, actualMapperKey);
        assertEquals(expectedMapperValue, actualMapperValue);
        assertEquals(expectedInputFormatClass, actualInputFormatClass);
        assertEquals(expectedReducer, actualReducer);
        assertEquals(expectedUsage, actualUsage);
    }
}
