package pt.um.mrc.lib;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ReducersTest
{
    @Test
    public final void testConstructor()
    {
        ReduceHelpers cls = new ReduceHelpers();

        assertNotNull(cls);
    }

    @Test
    public final void testToStringArray()
    {
        ArrayList<String> input = new ArrayList<String>();
        input.add("Uma");
        input.add("lista");

        String[] expected = { "Uma", "lista" };

        String[] result = ReduceHelpers.toStringArray(input);

        assertArrayEquals(expected, result);
    }
}
