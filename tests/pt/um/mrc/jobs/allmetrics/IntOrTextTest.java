package pt.um.mrc.jobs.allmetrics;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntOrTextTest {

	private IntOrText textIOT;
	private IntOrText numIOT;
	private IntOrText testIOT;

	@Before
	public void setUp() throws Exception {
		textIOT = new IntOrText("text", null, true);
		numIOT = new IntOrText(null, new Integer(1), false);
		testIOT = new IntOrText("text", 1, true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testMethodID_ParameterizedConstructor() {
		String expectedText = "map";
		Integer expectedNum = 1;
		Boolean expectedFlag = true;

		IntOrText iot = new IntOrText("map", 1, true);

		String actualText = iot.getText();
		Integer actualNum = iot.getIntw();
		Boolean actualFlag = iot.getIsText();

		assertEquals(expectedText, actualText);
		assertEquals(expectedNum, actualNum);
		assertEquals(expectedFlag, actualFlag);
	}

	@Test
	public final void testHashCode_Null() {
		int expected = 29791;

		IntOrText iot = new IntOrText(null, null, null);

		int actual = iot.hashCode();

		assertEquals(expected, actual);
	}

	@Test
	public final void testHashCode_EqualTexts() {
		IntOrText iot1 = new IntOrText("text", null, true);

		int hash1 = iot1.hashCode();
		int hash2 = textIOT.hashCode();

		assertThat(hash1, is(hash2));
	}

	@Test
	public final void testHashCode_EqualNums() {
		IntOrText iot1 = new IntOrText(null, 1, false);

		int hash1 = iot1.hashCode();
		int hash2 = numIOT.hashCode();

		assertThat(hash1, is(hash2));
	}

	@Test
	public final void testHashCode_Different() {

		int hash1 = textIOT.hashCode();
		int hash2 = numIOT.hashCode();

		assertThat(hash1, is(not(hash2)));
	}

	@Test
	public final void testIntorText_NullConstructor() {
		IntOrText iot = new IntOrText();

		assertNotNull(iot);
		assertTrue(iot instanceof IntOrText);
	}

	@Test
	public void testGetIsText() {
		Boolean expected = true;
		Boolean actual = testIOT.getIsText();

		assertEquals(expected, actual);
	}

	@Test
	public void testSetIsText() {
		Boolean expected = false;
		testIOT.setIsText(false);
		Boolean actual = testIOT.getIsText();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetText() {
		String expected = "text";
		String actual = testIOT.getText();

		assertEquals(expected, actual);
	}

	@Test
	public void testSetText() {
		String expected = "other text";
		testIOT.setText("other text");
		String actual = testIOT.getText();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetIntw() {
		Integer expected = new Integer(1);
		Integer actual = testIOT.getIntw();

		assertEquals(expected, actual);
	}

	@Test
	public void testSetIntw() {
		Integer expected = new Integer(3);
		testIOT.setIntw(3);
		Integer actual = testIOT.getIntw();

		assertEquals(expected, actual);
	}

	@Test
	public void testToString_Text() {
		String expected = "text";
		String actual = textIOT.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void testToString_Num() {
		String expected = "1";
		String actual = numIOT.toString();

		assertEquals(expected, actual);
	}

	@Test
	public final void testReadWrite() throws IOException {
		DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/iot"));
		DataInput in = new DataInputStream(new FileInputStream("TestMats/iot"));

		IntOrText expected = new IntOrText();

		testIOT.write(out);
		expected.readFields(in);

		assertEquals(expected, testIOT);
	}

	@Test
	public void testEquals_Identical_Flag() {
		IntOrText iot1 = new IntOrText("text", 1, true);
		IntOrText iot2 = new IntOrText("text", 1, false);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_Identical_Num() {
		IntOrText iot1 = new IntOrText("text", 1, true);
		IntOrText iot2 = new IntOrText("text", 2, false);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_Identical_Text() {
		IntOrText iot1 = new IntOrText("text1", 1, true);
		IntOrText iot2 = new IntOrText("text2", 1, false);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_DifferentNums() {
		IntOrText iot1 = new IntOrText(null, 1, true);
		IntOrText iot2 = new IntOrText(null, 2, true);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_DifferentTexts() {
		IntOrText iot1 = new IntOrText("text1", null, true);
		IntOrText iot2 = new IntOrText("text2", null, true);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_Equal() {
		IntOrText iot1 = new IntOrText("text", 1, true);
		IntOrText iot2 = new IntOrText("text", 1, true);

		boolean result = iot1.equals(iot2);
		assertTrue(result);
	}

	@Test
	public void testEquals_NullFlag() {
		IntOrText iot1 = new IntOrText("text", 1, null);
		IntOrText iot2 = new IntOrText("text", 1, true);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_NullBothFlags() {
		IntOrText iot1 = new IntOrText("text", 1, null);
		IntOrText iot2 = new IntOrText("text", 1, null);

		boolean result = iot1.equals(iot2);
		assertTrue(result);
	}

	@Test
	public void testEquals_NullText() {
		IntOrText iot1 = new IntOrText(null, 1, true);
		IntOrText iot2 = new IntOrText("text", 1, true);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_NullBothTexts() {
		IntOrText iot1 = new IntOrText(null, 1, true);
		IntOrText iot2 = new IntOrText(null, 1, true);

		boolean result = iot1.equals(iot2);
		assertTrue(result);
	}

	@Test
	public void testEquals_NullNum() {
		IntOrText iot1 = new IntOrText("text", null, true);
		IntOrText iot2 = new IntOrText("text", 1, true);

		boolean result = iot1.equals(iot2);
		assertFalse(result);
	}

	@Test
	public void testEquals_NullBothNums() {
		IntOrText iot1 = new IntOrText("text", null, true);
		IntOrText iot2 = new IntOrText("text", null, true);

		boolean result = iot1.equals(iot2);
		assertTrue(result);
	}

	@Test
	public void testEquals_NullIOT() {
		IntOrText iot1 = null;

		boolean result = textIOT.equals(iot1);

		assertFalse(result);
	}

	@Test
	public void testEquals_SomeObj() {
		Object obj = (Object) new Object();
		boolean result = testIOT.equals(obj);

		assertFalse(result);
	}

	@Test
	public void testCompareTo_SameText() {
		IntOrText iot1 = new IntOrText("test", null, true);
		IntOrText iot2 = new IntOrText("test", null, true);

		int expected = 0;
		int actual = iot1.compareTo(iot2);

		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_SameNum() {
		IntOrText iot1 = new IntOrText(null, 3, false);
		IntOrText iot2 = new IntOrText(null, 3, false);

		int expected = 0;
		int actual = iot1.compareTo(iot2);

		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_DiffText() {
		IntOrText iot1 = new IntOrText("text1", null, true);
		IntOrText iot2 = new IntOrText("text2", null, true);

		int expected = "text1".compareTo("text2");
		int actual = iot1.compareTo(iot2);

		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo_DiffNum() {
		IntOrText iot1 = new IntOrText(null, 3, false);
		IntOrText iot2 = new IntOrText(null, 5, false);

		int expected = new Integer(3).compareTo(new Integer(5));
		int actual = iot1.compareTo(iot2);

		assertEquals(expected, actual);
	}

}
