package pt.um.mrc.util.datatypes;

import org.junit.Test;

public class IDTypeTest
{
    @Test
    public final void testIDType()
    {
        IDType.valueOf(IDType.METHOD.toString());
        IDType.valueOf(IDType.CLASS.toString());
        IDType.valueOf(IDType.FILE.toString());
        IDType.valueOf(IDType.PACKAGE.toString());
    }
}
