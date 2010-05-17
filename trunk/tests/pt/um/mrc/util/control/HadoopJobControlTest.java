package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.jobs.pckg.PackageByFileMapper;
import pt.um.mrc.jobs.pckg.PackageByFileReducer;
import pt.um.mrc.util.io.JavaFileInputFormat;

public class HadoopJobControlTest
{
    private Job job;

    @Before
    public void setUp() throws Exception
    {
        job = new Job(new Configuration(), "Some job's name.");
    }

    @Test
    public final void testConstructor()
    {
        HadoopJobControl cls = new HadoopJobControl();
        
        assertNotNull(cls);
    }
    
    @Test
    public final void testConfigureSimpleJob() throws Exception
    {
        HadoopJobControl.configureSimpleJob(job, PackageByFileMapper.class,
                PackageByFileMapper.class, Text.class, Text.class, PackageByFileReducer.class,
                JavaFileInputFormat.class);
        
        assertEquals(PackageByFileMapper.class, job.getMapperClass());
        assertEquals(PackageByFileReducer.class, job.getReducerClass());
        assertEquals(Text.class, job.getMapOutputKeyClass());
        assertEquals(Text.class, job.getMapOutputValueClass());
        assertEquals(JavaFileInputFormat.class, job.getInputFormatClass());
    }
}
