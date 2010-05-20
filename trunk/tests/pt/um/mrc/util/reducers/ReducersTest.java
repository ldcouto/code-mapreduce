package pt.um.mrc.util.reducers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.junit.Test;

import pt.um.mrc.util.reducers.ReduceHelpers;

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
        ArrayList<Text> input = new ArrayList<Text>();
        input.add(new Text("Uma"));
        input.add(new Text("lista"));

        Text[] expected = { new Text("Uma"), new Text("lista") };

        Text[] result = (Text[]) ReduceHelpers.toTextArray(input);

        assertArrayEquals(expected, result);
    }
}
