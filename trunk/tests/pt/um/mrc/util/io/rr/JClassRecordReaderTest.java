package pt.um.mrc.util.io.rr;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.io.rr.JClassRecordReader;
import pt.um.mrc.util.io.visitors.ClassMiscMcCabeGrabberFileVisitor;

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

		Class<?> expected = ClassMiscMcCabeGrabberFileVisitor.class;

		Class<?> actual = jrr.visitor.getClass();

		assertEquals(expected, actual);
	}

}
