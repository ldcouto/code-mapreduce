package pt.um.mrc.util.datatypes;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MethodIDTest
{
    private MethodID testID;

    @Before
    public void setUp() throws Exception
    {
        testID = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
    }

    @Test
    public final void testMethodID_NullConstructor()
    {
        MethodID id = new MethodID();

        assertNotNull(id);
        assertTrue(id instanceof MethodID);
    }

    @Test
    public final void testMethodID_ParameterizedConstructor()
    {
        String expectedMethodName = "map";
        String expectedClassName = "Mapper";
        String expectedFileName = "Mapper.java";
        String expectadPackageName = "mapreduce";

        MethodID id = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        String actualMethodName = id.getMethodName();
        String actualClassName = id.getClassName();
        String actualFileName = id.getFileName();
        String actualPackageName = id.getPackageName();

        assertEquals(expectedMethodName, actualMethodName);
        assertEquals(expectedClassName, actualClassName);
        assertEquals(expectedFileName, actualFileName);
        assertEquals(expectadPackageName, actualPackageName);
    }

    @Test
    public final void testGetMethodName()
    {
        String expectedMethodName = "map";
        String actualMethodName = testID.getMethodName();

        assertEquals(expectedMethodName, actualMethodName);
    }

    @Test
    public final void testSetMethodName()
    {
        String expectedMethodName = "reduce";
        testID.setMethodName("reduce");
        String actualMethodName = testID.getMethodName();

        assertEquals(expectedMethodName, actualMethodName);
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
        String expected = "mapreduce-Mapper.java-Mapper-map";
        String actual = testID.toString();

        assertEquals(expected, actual);
    }

    @Test
    public final void testReadFields()
    {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testWrite()
    {
        fail("Not yet implemented"); // TODO
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
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("reduce", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_02()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Reducer", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_03()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Reducer.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalID_04()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_DifferentID()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("reduce", "reducer", "Reducer.java", "mapred");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_EqualID()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_MethodNameNullID()
    {
        MethodID id1 = new MethodID(null, "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_ClassNameNullID()
    {
        MethodID id1 = new MethodID("map", null, "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_FileNameNullID()
    {
        MethodID id1 = new MethodID("map", "Mapper", null, "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_PackageNameNullID()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", null);
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        boolean result = id1.equals(id2);

        assertFalse(result);
    }

    @Test
    public final void testHashCode_NullID()
    {
        int expected = 923521;

        MethodID id = new MethodID(null, null, null, null);

        int actual = id.hashCode();

        assertEquals(expected, actual);
    }
    
    @Test
    public final void testHashCode_EqualIDs()
    {
        MethodID id1 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(hash2));
    }
    
    @Test
    public final void testHashCode_DifferentIDs()
    {
        MethodID id1 = new MethodID("reduce", "Reducer", "Reducer.java", "mapred");
        MethodID id2 = new MethodID("map", "Mapper", "Mapper.java", "mapreduce");

        int hash1 = id1.hashCode();
        int hash2 = id2.hashCode();

        assertThat(hash1, is(not(hash2)));
    }
}
