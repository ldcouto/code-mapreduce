package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.fs.Path;
import org.junit.Test;

import pt.um.mrc.util.io.JMethodInputFormat;

public class JobConfigurerTest
{
    @Test
    public final void testJobConfigurer()
    {
        Class<JobConfigurerTest> expectedClassJar = JobConfigurerTest.class;
        Class<JMethodInputFormat> expectedInputFormat = JMethodInputFormat.class;
        Path expectedInputPath = new Path("some/dir");
        Path expectedOutputPath = new Path("other/dir");
        
        JobConfigurer jc = new JobConfigurer(JobConfigurerTest.class, JMethodInputFormat.class, new Path("some/dir"), new Path("other/dir"));
        
        assertEquals(expectedClassJar, jc.getClassJar());
        assertEquals(expectedInputFormat, jc.getIntputFormat());
        assertEquals(expectedInputPath, jc.getInputPath());
        assertEquals(expectedOutputPath, jc.getOutputPath());
    }
}
