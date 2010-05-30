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
                        "\n" +
        		"System.out.println(\"Hello World\")\n" +
        		"      \n" +
        		"\t\n" +
        		"\t\t \n" +
        		"}\n";
        
        int expected = 3;
        
        int actual = VolumeHelper.countLinesOfCode(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public final void testCountLinesOfCode02()
    {
        String input = "public void helloWorld(){\n" + 
                        "\n" +
                        "System.out.println(\"Hello World\")\n" +
                        "}";
        
        int expected = 3;
        
        int actual = VolumeHelper.countLinesOfCode(input);
        
        assertEquals(expected, actual);
    }
}
