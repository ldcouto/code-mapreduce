package pt.um.mrc.util.datatypes;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

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

        Assert.assertEquals(expected, result);
    }

    @Test
    public final void testGetValue()
    {
        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");

        String expected = "Value";
        String result = p.getValue();

        Assert.assertEquals(expected, result);
    }

    @Test
    public final void testSetKey()
    {
        Integer expected = 2;

        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");
        p.setKey(2);

        Integer result = p.getKey();

        Assert.assertEquals(expected, result);
    }

    @Test
    public final void testSetValue()
    {
        String expected = "SomethingElse";

        Pair<Integer, String> p = new PairImpl<Integer, String>(1, "Value");
        p.setValue("SomethingElse");

        String result = p.getValue();

        Assert.assertEquals(expected, result);
    }

    @Test
    public final void testEquals_NullObject()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");

        boolean result = p.equals(null);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_SameObject()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");

        boolean result = p.equals(p);

        Assert.assertTrue(result);
    }

    @Test
    public final void testEquals_Object()
    {
        PairImpl<Integer, String> p = new PairImpl<Integer, String>(1, "Test");
        Object obj = new Object();

        boolean result = p.equals(obj);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalPair01()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Test");

        boolean result = p1.equals(p2);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_IdenticalPair02()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Test 2");

        boolean result = p1.equals(p2);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_DiferentPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Test 2");

        boolean result = p1.equals(p2);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_EqualPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Test");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Test");

        boolean result = p1.equals(p2);

        Assert.assertTrue(result);
    }

    @Test
    public final void testEquals_PairNullKey()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(null, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        boolean result = p1.equals(p2);

        Assert.assertFalse(result);
    }

    @Test
    public final void testEquals_PairNullValue()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, null);
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        boolean result = p1.equals(p2);

        Assert.assertFalse(result);
    }

    @Test
    public final void testHashCode_NullPair()
    {
        int expected = 961;
        
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(null, null);

        int result = p1.hashCode();
        
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public final void testHashCode_EqualPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(1, "Text");

        int hash1 = p1.hashCode();
        int hash2 = p2.hashCode();
        
        Assert.assertTrue(hash1 == hash2);
    }
    
    @Test
    public final void testHashCode_DifferentPair()
    {
        PairImpl<Integer, String> p1 = new PairImpl<Integer, String>(1, "Text");
        PairImpl<Integer, String> p2 = new PairImpl<Integer, String>(2, "Text 2");

        int hash1 = p1.hashCode();
        int hash2 = p2.hashCode();
        
        Assert.assertFalse(hash1 == hash2);
    }
}
