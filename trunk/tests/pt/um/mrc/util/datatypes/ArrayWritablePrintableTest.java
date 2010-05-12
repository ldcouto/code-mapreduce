package pt.um.mrc.util.datatypes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayWritablePrintableTest {
	
	ArrayWritablePrintable awp;
	String[] strings;

	@Before
	public void setUp() throws Exception {	
		strings = new String[2];
		strings[0]="foo";
		strings[1]="bar";
		awp = new ArrayWritablePrintable(strings);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testArrayWritablePrintable() {
		ArrayWritablePrintable awpToTest = new ArrayWritablePrintable(strings);
		ArrayWritable aux = new ArrayWritable(strings);
		
		Writable[] expected = aux.get();
		Writable[] actual = awpToTest.get();
		
		assertArrayEquals(expected, actual);
		}

	@Test
	public void testToString() {
		String expected = "{ foo bar}";
		String actual = awp.toString();
		assertEquals(expected, actual);
	}

}
