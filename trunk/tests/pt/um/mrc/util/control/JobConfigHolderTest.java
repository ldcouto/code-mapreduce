package pt.um.mrc.util.control;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.um.mrc.jobs.allmetrics.CSTextOutputFormat;
import pt.um.mrc.util.io.iformats.JMethodInputFormat;

public class JobConfigHolderTest
{
    @Test
    public final void testJobConfigurer()
    {
        Class<JobConfigHolderTest> expectedClassJar = JobConfigHolderTest.class;
        Class<JMethodInputFormat> expectedInputFormat = JMethodInputFormat.class;
        Class<CSTextOutputFormat> expectedOutputFormat = CSTextOutputFormat.class;
        String[] expectedInputOutputPaths = { "Some/In/Path", "Some/Other/In/Path", "Some/Out/Path" };

        String[] paths = { "Some/In/Path", "Some/Other/In/Path", "Some/Out/Path" };
        JobConfigHolder jc = new JobConfigHolder(JobConfigHolderTest.class, JMethodInputFormat.class, CSTextOutputFormat.class,
                paths);

        assertEquals(expectedClassJar, jc.getClassJar());
        assertEquals(expectedInputFormat, jc.getIntputFormat());
        assertEquals(expectedOutputFormat, jc.getOutputFormat());
        assertArrayEquals(expectedInputOutputPaths, jc.getPaths());
    }
}
