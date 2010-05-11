package pt.um.mrc.lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class PckgHelperTest
{

    @Test
    public final void testFindPackage_ValidPackage()
    {
        String input = "package pt.um.mrc.lib;";
        
        String expected = "pt.um.mrc.lib";
        
        String result = PckgHelper.findPackage(input);
        
        assertEquals(expected, result);
    }

    @Test
    public final void testFindPackage_NoPackage()
    {
        String input = "import java.util.ArrayList;";
        
        String expected = "default package";
        
        String result = PckgHelper.findPackage(input);
        
        assertEquals(expected, result);
    }
}
