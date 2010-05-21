package pt.um.mrc.util.control;

import static org.junit.Assert.*;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

public class CheckedJobInfoTest
{

    @Test
    public final void testCheckedJobInfo()
    {
        String expectedMessage = "Some usage message";
        Configuration expectedConf = new Configuration();
        
        CheckedJobInfo cji = new CheckedJobInfo(new Configuration(), "Some usage message");
        
        assertEquals(expectedMessage, cji.getUsageMessage());
        assertEquals(expectedConf.toString(), cji.getConf().toString());
    }
}
