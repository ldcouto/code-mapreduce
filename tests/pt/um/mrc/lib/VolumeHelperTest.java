package pt.um.mrc.lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class VolumeHelperTest
{

    @Test
    public final void testVolumeHelper()
    {
        VolumeHelper cls = new VolumeHelper();
        
        assertNotNull(cls);
    }

    @Test
    public final void testCountLinesOfCode()
    {
        String input = "public void helloWorld(){\n" +
        		"System.out.println(\"Hello World\")\n" +
        		"}\n";
        
        int expected = 3;
        
        int actual = VolumeHelper.countLinesOfCode(input);
        
        assertEquals(expected, actual);
    }

}
