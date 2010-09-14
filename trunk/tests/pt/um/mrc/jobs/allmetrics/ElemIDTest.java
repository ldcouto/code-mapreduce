package pt.um.mrc.jobs.allmetrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.IDType;

public class ElemIDTest {

	ElemID testEID;

	@Before
	public void setUp() {
		testEID = new ElemID("ElemMethod", "ElemClass", "ElemFile", "ElemPackage", IDType.METHOD,
						MetricType.VOLUME_BY_METHOD);
	}

	@Test
	public void testHashCode_Null() {
		int expected = 29791;

		MetricValue iot = new MetricValue(null, null, null);

		int actual = iot.hashCode();

		assertEquals(expected, actual);
	}

	@Test
	public void testElemID_Constructor_NoParams() {
		fail("Not yet implemented");
	}

	@Test
	public void testElemID_Constructor_Parameters() {
			String expectedMethd = "method";
			String expectedClass = "class";
			String expectedFile = "file";
			String expectedPackage = "package";
			IDType expectedIDT = IDType.METHOD;
			MetricType expectedMT = MetricType.NONE;
			
			ElemID eid = new ElemID("method","class","file","package",IDType.METHOD,MetricType.NONE);
			
			String actualMethd = eid.getMethodName();
			String actualClass = eid.getClassName();
			String actualFile = eid.getFileName();
			String actualPackage = eid.getPackageName();
			IDType actualIDT = eid.getIDType();
			MetricType actualMT = eid.getMetricType();
			
			assertEquals(expectedMethd, actualMethd);
			assertEquals(expectedClass, actualClass);
			assertEquals(expectedFile, actualFile);
			assertEquals(expectedPackage, actualPackage);
			assertEquals(expectedIDT, actualIDT);
			assertEquals(expectedMT, actualMT);
	}

	@Test
	public void testGetMetricName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMetricName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMethodName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMethodName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetClassName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFileName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFileName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPackageName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPackageName() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadFields() {
		fail("Not yet implemented");
	}

	@Test
	public void testWrite() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
