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
        int expectedArgNum = 3;
        
        CheckedJobInfo cji = new CheckedJobInfo("Some usage message", new Configuration(), 3);
        
        assertEquals(expectedMessage, cji.getUsageMessage());
        assertEquals(expectedConf.toString(), cji.getConf().toString());
        assertEquals(expectedArgNum, cji.getArgNum());
    }
}
