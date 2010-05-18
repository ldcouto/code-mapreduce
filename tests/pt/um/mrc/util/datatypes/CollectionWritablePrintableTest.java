package pt.um.mrc.util.datatypes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.junit.Before;
import org.junit.Test;

public class CollectionWritablePrintableTest
{

    CollectionWritablePrintable awp;
    Text[] strings;

    @Before
    public void setUp() throws Exception
    {
        strings = new Text[2];
        strings[0] = new Text("foo");
        strings[1] = new Text("bar");
        awp = new CollectionWritablePrintable(Text.class,strings);
    }

    @Test
    public void testArrayWritablePrintable()
    {
        Writable[] expected = strings;
        Writable[] actual = awp.get();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testToString()
    {
        String expected = "{ foo bar}";
        String actual = awp.toString();
        assertEquals(expected, actual);
    }

}
