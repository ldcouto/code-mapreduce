package pt.um.mrc.util.datatypes;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class FileIDTest
{
    private FileID testID;

    @Before
    public void setUp() throws Exception
    {
        testID = new FileID("Mapper.java", "mapreduce");
    }

    @Test
    public final void testFileID_NullConstructor()
    {
        FileID id = new FileID();

        assertNotNull(id);
        assertTrue(id instanceof FileID);
    }

    @Test
    public final void testFileID_ParameterizedConstructor()
    {
        String expectedFileName = "Mapper.java";
        String expectadPackageName = "mapreduce";

        FileID id = new FileID("Mapper.java", "mapreduce");

        String actualFileName = id.getFileName();
        String actualPackageName = id.getPackageName();

        assertEquals(expectedFileName, actualFileName);
        assertEquals(expectadPackageName, actualPackageName);
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
        String expected = "mapreduce-Mapper.java";
        String actual = testID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testReadWrite() throws IOException
    {
        DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/FileID"));
        DataInput in = new DataInputStream(new FileInputStream("TestMats/FileID"));

        FileID expected = new FileID();

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
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Reducer.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_02()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_DifferentID()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Reducer.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_EqualID()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_FileNameNullID()
    {
        FileID id1 = new FileID(null, "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_FileNameBothNullID()
    {
        FileID id1 = new FileID(null, "mapreduce");
        FileID id2 = new FileID(null, "mapreduce");

        boolean result = id1.equals(id2);

        assertTrue(result);
    }
    
    @Test
    public final void testEquals_PackageNameNullID()
    {
        FileID id1 = new FileID("Mapper.java", null);
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }
    
    @Test
    public final void testEquals_PackageNameBothNullID()
    {
        FileID id1 = new FileID("Mapper.java", null);
        FileID id2 = new FileID("Mapper.java", null);

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testHashCode_NullID()
    {
        int expected = 961;

        FileID id = new FileID(null, null);

        int actual = id.hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public final void testHashCode_EqualIDs()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(hash2));
    }

    @Test
    public final void testHashCode_DifferentIDs()
    {
        FileID id1 = new FileID("Reducer.java", "mapred");
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(not(hash2)));
    }

    @Test
    public final void testCompareTo_Same()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapreduce");

        int expected = 0;
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffPackage()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper.java", "mapred");

        int expected = "mapreduce".compareTo("mapred");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffFile()
    {
        FileID id1 = new FileID("Mapper.java", "mapreduce");
        FileID id2 = new FileID("Mapper2.java", "mapreduce");

        int expected = "Mapper.java".compareTo("Mapper2.java");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }
}
