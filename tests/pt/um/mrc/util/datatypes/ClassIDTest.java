package pt.um.mrc.util.datatypes;

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

import org.junit.Before;
import org.junit.Test;

public class ClassIDTest
{
    private ClassID testID;

    @Before
    public void setUp() throws Exception
    {
        testID = new ClassID("Mapper", "Mapper.java", "mapreduce");
    }

    @Test
    public final void testClassID_NullConstructor()
    {
        ClassID id = new ClassID();

        assertNotNull(id);
        assertTrue(id instanceof ClassID);
    }

    @Test
    public final void testClassID_ParameterizedConstructor()
    {
        String expectedClassName = "Mapper";
        String expectedFileName = "Mapper.java";
        String expectadPackageName = "mapreduce";

        ClassID id = new ClassID("Mapper", "Mapper.java", "mapreduce");

        String actualClassName = id.getClassName();
        String actualFileName = id.getFileName();
        String actualPackageName = id.getPackageName();

        assertEquals(expectedClassName, actualClassName);
        assertEquals(expectedFileName, actualFileName);
        assertEquals(expectadPackageName, actualPackageName);
    }

    @Test
    public final void testGetClassName()
    {
        String expectedClassName = "Mapper";
        String actualClassName = testID.getClassName();

        assertEquals(expectedClassName, actualClassName);
    }

    @Test
    public final void testSetClassName()
    {
        String expectedClassName = "Reducer";
        testID.setClassName("Reducer");
        String actualClassName = testID.getClassName();

        assertEquals(expectedClassName, actualClassName);
    }

    @Test
    public final void testGetFileName()
    {
        String expectedFileName = "Mapper.java";
        String actualFileName = testID.getFileName();

        assertEquals(expectedFileName, actualFileName);
    }

    @Test
    public final void testSetFileName()
    {
        String expectedFileName = "Reducer.java";
        testID.setFileName("Reducer.java");
        String actualFileName = testID.getFileName();

        assertEquals(expectedFileName, actualFileName);
    }

    @Test
    public final void testGetPackageName()
    {
        String expectedPackageName = "mapreduce";
        String actualPackageName = testID.getPackageName();

        assertEquals(expectedPackageName, actualPackageName);
    }

    @Test
    public final void testSetPackageName()
    {
        String expectedPackageName = "mapred";
        testID.setPackageName("mapred");
        String actualPackageName = testID.getPackageName();

        assertEquals(expectedPackageName, actualPackageName);
    }

    @Test
    public final void testToString()
    {
        String expected = "mapreduce-Mapper.java-Mapper";
        String actual = testID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testReadWrite() throws IOException
    {
        DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/ClassID"));
        DataInput in = new DataInputStream(new FileInputStream("TestMats/ClassID"));

        ClassID expected = new ClassID();

        testID.write(out);
        expected.readFields(in);

        assertEquals(expected, testID);
    }

    @Test
    public final void testEquals_Null()
    {
        boolean result = testID.equals(null);

        assertFalse(result);
    }

    @Test
    public final void testEquals_SameObject()
    {
        boolean result = testID.equals(testID);

        assertTrue(result);
    }

    @Test
    public final void testEquals_SomeObject()
    {
        boolean result = testID.equals(new Object());

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_01()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Reducer", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_02()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Reducer.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_03()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_DifferentID()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("reducer", "Reducer.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_EqualID()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_ClassNameNullID()
    {
        ClassID id1 = new ClassID(null, "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_FileNameNullID()
    {
        ClassID id1 = new ClassID("Mapper", null, "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_PackageNameNullID()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", null);
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testHashCode_NullID()
    {
        int expected = 29791;

        ClassID id = new ClassID(null, null, null);

        int actual = id.hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public final void testHashCode_EqualIDs()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(hash2));
    }

    @Test
    public final void testHashCode_DifferentIDs()
    {
        ClassID id1 = new ClassID("Reducer", "Reducer.java", "mapred");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(not(hash2)));
    }

    @Test
    public final void testCompareTo_Same()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapreduce");

        int expected = 0;
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffPackage()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper.java", "mapred");

        int expected = "mapreduce".compareTo("mapred");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffFile()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper", "Mapper2.java", "mapreduce");

        int expected = "Mapper.java".compareTo("Mapper2.java");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffClass()
    {
        ClassID id1 = new ClassID("Mapper", "Mapper.java", "mapreduce");
        ClassID id2 = new ClassID("Mapper2", "Mapper.java", "mapreduce");

        int expected = "Mapper".compareTo("Mapper2");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }
}
