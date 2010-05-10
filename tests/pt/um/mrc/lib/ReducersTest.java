package pt.um.mrc.lib;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ReducersTest
{
    @Test
    public final void testToStringArray()
    {
        ArrayList<String> input = new ArrayList<String>();
        input.add("Uma");
        input.add("lista");

        String[] expected = { "Uma", "lista" };

        String[] result = Reducers.toStringArray(input);

        assertArrayEquals(expected, result);
    }
}
