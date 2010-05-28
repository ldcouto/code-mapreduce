package pt.um.mrc.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConstantsTest
{
    private Constants consts;
    
    @Test
    public final void testNullConstructor()
    {
        consts = new Constants();
        
        assertNotNull(consts);
        assertTrue(consts instanceof Constants);
    }
}
