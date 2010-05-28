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

import pt.um.mrc.util.datatypes.IDType;

public class GeneralIDTest
{
    private GeneralID genID;

    @Before
    public void setUp() throws Exception
    {
        genID = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
    }

    @Test
    public final void testGeneralID_NullConstructor()
    {
        GeneralID id = new GeneralID();

        assertNotNull(id);
        assertTrue(id instanceof GeneralID);
    }

    @Test
    public final void testGeneralID_ParameterizedConstructor()
    {
        String expectedMethodName = "map";
        String expectedClassName = "Mapper";
        String expectedFileName = "Mapper.java";
        String expectadPackageName = "mapreduce";
        IDType expectedIDType = IDType.METHOD;

        GeneralID id = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        String actualMethodName = id.getMethodName();
        String actualClassName = id.getClassName();
        String actualFileName = id.getFileName();
        String actualPackageName = id.getPackageName();
        IDType actualIDType = id.getType();

        assertEquals(expectedMethodName, actualMethodName);
        assertEquals(expectedClassName, actualClassName);
        assertEquals(expectedFileName, actualFileName);
        assertEquals(expectadPackageName, actualPackageName);
        assertEquals(expectedIDType, actualIDType);
    }

    @Test
    public final void testGetType()
    {
        IDType expectedType = IDType.METHOD;
        IDType actualType = genID.getType();

        assertEquals(expectedType, actualType);
    }

    @Test
    public final void testSetType()
    {
        IDType expectedType = IDType.CLASS;
        genID.setType(IDType.CLASS);
        IDType actualType = genID.getType();

        assertEquals(expectedType, actualType);
    }

    @Test
    public final void testGetMethodName()
    {
        String expectedMethodName = "map";
        String actualMethodName = genID.getMethodName();

        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public final void testSetMethodName()
    {
        String expectedMethodName = "reduce";
        genID.setMethodName("reduce");
        String actualMethodName = genID.getMethodName();

        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public final void testGetClassName()
    {
        String expectedClassName = "Mapper";
        String actualClassName = genID.getClassName();

        assertEquals(expectedClassName, actualClassName);
    }

    @Test
    public final void testSetClassName()
    {
        String expectedClassName = "Reducer";
        genID.setClassName("Reducer");
        String actualClassName = genID.getClassName();

        assertEquals(expectedClassName, actualClassName);
    }

    @Test
    public final void testGetFileName()
    {
        String expectedFileName = "Mapper.java";
        String actualFileName = genID.getFileName();

        assertEquals(expectedFileName, actualFileName);
    }

    @Test
    public final void testSetFileName()
    {
        String expectedFileName = "Reducer.java";
        genID.setFileName("Reducer.java");
        String actualFileName = genID.getFileName();

        assertEquals(expectedFileName, actualFileName);
    }

    @Test
    public final void testGetPackageName()
    {
        String expectedPackageName = "mapreduce";
        String actualPackageName = genID.getPackageName();

        assertEquals(expectedPackageName, actualPackageName);
    }

    @Test
    public final void testSetPackageName()
    {
        String expectedPackageName = "mapred";
        genID.setPackageName("mapred");
        String actualPackageName = genID.getPackageName();

        assertEquals(expectedPackageName, actualPackageName);
    }

    @Test
    public final void testToString_METHOD()
    {
        String expected = "mapreduce-Mapper.java-Mapper-map";
        String actual = genID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testToString_CLASS()
    {
        genID.setType(IDType.CLASS);
        String expected = "mapreduce-Mapper.java-Mapper";
        String actual = genID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testToString_FILE()
    {
        genID.setType(IDType.FILE);
        String expected = "mapreduce-Mapper.java";
        String actual = genID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testReadWrite_METHOD() throws IOException
    {
        DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/GeneralID"));
        DataInput in = new DataInputStream(new FileInputStream("TestMats/GeneralID"));

        GeneralID expected = new GeneralID();

        genID.write(out);
        expected.readFields(in);

        assertEquals(expected, genID);
    }

    @Test
    public final void testReadWrite_CLASS() throws IOException
    {
        DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/GeneralID"));
        DataInput in = new DataInputStream(new FileInputStream("TestMats/GeneralID"));

        GeneralID expected = new GeneralID();

        genID.setType(IDType.CLASS);
        genID.write(out);
        expected.readFields(in);

        assertEquals(expected, genID);
    }

    @Test
    public final void testReadWrite_FILE() throws IOException
    {
        DataOutput out = new DataOutputStream(new FileOutputStream("TestMats/GeneralID"));
        DataInput in = new DataInputStream(new FileInputStream("TestMats/GeneralID"));

        GeneralID expected = new GeneralID();

        genID.setType(IDType.FILE);
        genID.write(out);
        expected.readFields(in);

        assertEquals(expected, genID);
    }

    @Test
    public final void testEquals_Null()
    {
        boolean result = genID.equals(null);

        assertFalse(result);
    }

    @Test
    public final void testEquals_SameObject()
    {
        boolean result = genID.equals(genID);

        assertTrue(result);
    }

    @Test
    public final void testEquals_SomeObject()
    {
        boolean result = genID.equals(new Object());

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_01()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("reduce", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_02()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Reducer", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_03()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Reducer.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_04()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapred", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_DifferentID()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("reduce", "reducer", "Reducer.java", "mapred", IDType.CLASS);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_EqualID()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_EqualPackageID()
    {
        GeneralID id1 = new GeneralID(null, null, null, "mapreduce", IDType.PACKAGE);
        GeneralID id2 = new GeneralID(null, null, null, "mapreduce", IDType.PACKAGE);

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_MethodNameNullID()
    {
        GeneralID id1 = new GeneralID(null, "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_ClassNameNullID()
    {
        GeneralID id1 = new GeneralID("map", null, "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_FileNameNullID()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", null, "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_PackageNameNullID()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", null, IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_TypeNullID()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", null);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testHashCode_NullID()
    {
        int expected = 28629151;

        GeneralID id = new GeneralID(null, null, null, null, null);

        int actual = id.hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public final void testHashCode_EqualIDs()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(hash2));
    }

    @Test
    public final void testHashCode_DifferentIDs()
    {
        GeneralID id1 = new GeneralID("reduce", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(not(hash2)));
    }

    @Test
    public final void testCompareTo_Same()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        int expected = 0;
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffType()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapred", IDType.CLASS);

        int expected = IDType.METHOD.compareTo(IDType.CLASS);
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffPackage()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper.java", "mapred", IDType.METHOD);

        int expected = "mapreduce".compareTo("mapred");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffFile()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper", "Mapper2.java", "mapreduce", IDType.METHOD);

        int expected = "Mapper.java".compareTo("Mapper2.java");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffClass()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("map", "Mapper2", "Mapper.java", "mapreduce", IDType.METHOD);

        int expected = "Mapper".compareTo("Mapper2");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }

    @Test
    public final void testCompareTo_DiffMetd()
    {
        GeneralID id1 = new GeneralID("map", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);
        GeneralID id2 = new GeneralID("setup", "Mapper", "Mapper.java", "mapreduce", IDType.METHOD);

        int expected = "map".compareTo("setup");
        int actual = id1.compareTo(id2);

        assertEquals(expected, actual);
    }
}
