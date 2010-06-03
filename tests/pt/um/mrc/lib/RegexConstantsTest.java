package pt.um.mrc.lib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RegexConstantsTest
{
    @Test
    public final void testConstructor()
    {
        PckgHelper cls = new PckgHelper();
        
        assertNotNull(cls);
    }


    @Test
    public final void testFindPackage_ValidPackage()
    {
        String input = "package pt.um.mrc.lib;";
        
        String expected = "pt.um.mrc.lib";
        
        String result = PckgHelper.findPackage(input);
        
        assertEquals(expected, result);
    }

    @Test
    public final void testFindPackage_NoPackage()
    {
        String input = "import java.util.ArrayList;";
        
        String expected = "<default>";
        
        String result = PckgHelper.findPackage(input);
        
        assertEquals(expected, result);
    }
    
    @Test
	public void testExtractPkgNameStar() {
    	String s = "foo.bar.*";
    	
    	String expected = "foo.bar";
    	String actual = PckgHelper.extractPkgNameStar(s);
    	
    	assertEquals(expected, actual);
	}

    @Test
	public void testExtractPkgClassNames() {
    	String s = "foo.bar.Class";
    	
    	String[] expected = {"foo.bar", "foo.bar.Class"};
    	
    	String[] actual = PckgHelper.extractPkgClassNames(s);
    	
    	assertArrayEquals(expected, actual); 	
	}
    
    @Test
    public void testFindPackage(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("package foo.bar;\n");
    	sb.append("import f.b.*;\n)");
    	sb.append("public class Class1{}");
    	
    	String expected = "foo.bar";
    	String actual = PckgHelper.findPackage(sb.toString());
    	
    	assertEquals(expected, actual);
    	}
}
