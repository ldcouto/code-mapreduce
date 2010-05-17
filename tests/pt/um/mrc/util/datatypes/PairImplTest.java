package pt.um.mrc.util.datatypes;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PairImplTest
{
    @Test
    public final void testPairImpl_NullCtor()
    {
        Integer expectedInteger = 30;
        String expectedString = "Ola";

        Pair<Integer, String> p = new PairImpl<Integer, String>();
        p.setKey(30);
        p.setValue("Ola");

        Integer resultInteger = p.getKey();
        String resultString = p.getValue();

        assertEquals(expectedInteger, resultInteger);
        assertEquals(expectedString, resultString);
    }

    @Test
    public final void testPairImpl_ParameterizedCtor()
    {
        Integer expectedInteger = 30;
        String expectedString = "Ola";

        Pair<Integer, String> p = new PairImpl<Integer, String>(30, "Ola");

        Integer resultInteger = p.getKey();
        String resultString = p.getValue();

        assertEquals(expectedInteger, resultInteger);
        assertEquals(expectedString, resultString);
    }

    @Test
    public final void testGetKey()
    {
        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");

        Integer expected = 1;
        Integer result = p.getKey();

        assertEquals(expected, result);
    }

    @Test
    public final void testGetValue()
    {
        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");

        String expected = "Value";
        String result = p.getValue();

        assertEquals(expected, result);
    }

    @Test
    public final void testSetKey()
    {
        Integer expected = 2;

        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");
        p.setKey(2);

        Integer result = p.getKey();

        assertEquals(expected, result);
    }

    @Test
    public final void testSetValue()
    {
        String expected = "SomethingElse";

        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");
        p.setValue("SomethingElse");

        String result = p.getValue();

        assertEquals(expected, result);
    }

    @Test
    public final void testEquals_NullObject()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");

        boolean result = p.equals(null);

        assertFalse(result);
    }

    @Test
    public final void testEquals_SameObject()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");

        boolean result = p.equals(p);

        assertTrue(result);
    }

    @Test
    public final void testEquals_Object()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");
        Object obj = new Object();

        boolean result = p.equals(obj);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalPair01()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Test");

        boolean result = p1.equals(p2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalPair02()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Test 2");

        boolean result = p1.equals(p2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_DiferentPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Test 2");

        boolean result = p1.equals(p2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_EqualPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Test");

        boolean result = p1.equals(p2);

        assertTrue(result);
    }

    @Test
    public final void testEquals_PairNullKey()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(null, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        boolean result = p1.equals(p2);

        assertFalse(result);
    }

    @Test
    public final void testEquals_PairNullValue()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, null);
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        boolean result = p1.equals(p2);

        assertFalse(result);
    }

    @Test
    public final void testHashCode_NullPair()
    {
        int expected = 961;
        
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(null, null);

        int result = p1.hashCode();
        
        assertEquals(expected, result);
    }
    
    @Test
    public final void testHashCode_EqualPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        int hash1 = p1.hashCode();
        int hash2 = p2.hashCode();
        
        assertThat(hash1, is(hash2));
    }
    
    @Test
    public final void testHashCode_DifferentPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Text 2");

        int hash1 = p1.hashCode();
        int hash2 = p2.hashCode();
        
        assertThat(hash1, not(hash2));
    }
}
