package pt.um.mrc.util.io;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JClassAndPkgRecordReaderTest {


	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJClassAndPkgRecordReader() {
		JClassAndPkgRecordReader jrr = new JClassAndPkgRecordReader();
		
		// We only need to check if the visitor is properly typed.
		
		Class<?>expected = GrabClassAndPkgVisitor.class;
		
		Class<?> actual = jrr.visitor.getClass();
		
		assertEquals(expected, actual);}

}
