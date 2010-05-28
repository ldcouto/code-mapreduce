package pt.um.mrc.util.control;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.um.mrc.util.io.JMethodInputFormat;

public class JobConfigurerTest
{
    @Test
    public final void testJobConfigurer()
    {
        Class<JobConfigurerTest> expectedClassJar = JobConfigurerTest.class;
        Class<JMethodInputFormat> expectedInputFormat = JMethodInputFormat.class;
        String[] expectedInputOutputPaths = { "Some/In/Path", "Some/Other/In/Path", "Some/Out/Path" };

        String[] paths = { "Some/In/Path", "Some/Other/In/Path", "Some/Out/Path" };
        JobConfigurer jc = new JobConfigurer(JobConfigurerTest.class, JMethodInputFormat.class,
                paths);

        assertEquals(expectedClassJar, jc.getClassJar());
        assertEquals(expectedInputFormat, jc.getIntputFormat());
        assertArrayEquals(expectedInputOutputPaths, jc.getPaths());
    }
}
