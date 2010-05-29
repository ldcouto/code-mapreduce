package pt.um.mrc.util.io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JClassRecordReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJClassRecordReader() {

		JClassRecordReader jrr = new JClassRecordReader();

		Class<?> expected = GrabClassMiscVisitor.class;

		Class<?> actual = jrr.visitor.getClass();

		assertEquals(expected, actual);
	}

}
