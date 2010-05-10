package pt.um.mrc.util.datatypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairImplTest
{
    @Test
    public final void testPairImpl()
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
        
        Pair<Integer, String> p = new PairImpl<Integer, String>(1,"Value");
        p.setKey(2);
        
        Integer result = p.getKey();

        assertEquals(expected, result);
    }

    @Test
    public final void testSetValue()
    {
        String expected = "SomethingElse";
        
        Pair<Integer, String> p = new PairImpl<Integer, String>(1,"Value");
        p.setValue("SomethingElse");
        
        String result = p.getValue();

        assertEquals(expected, result);
    }

}
