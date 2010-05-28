package pt.um.mrc.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import org.junit.Test;

import pt.um.mrc.util.datatypes.Pair;
import pt.um.mrc.util.datatypes.PairImpl;

public class ClassHelperTest
{
    @Test
    public final void testConstructor()
    {
        ClassHelper clasS = new ClassHelper();
        
        assertNotNull(clasS);
    }
    
    @Test
    public final void testFindClassAndSuperClass_ValidInput01()
    {
         String input = "public class Test extends SuperTest{";
         
         ArrayList<Pair<String, String>> expected = new ArrayList<Pair<String,String>>();
         expected.add(new PairImpl<String, String>("Test","SuperTest"));
         
         ArrayList<Pair<String, String>> result = ClassHelper.findClassAndSuperClass(input);
         
         assertEquals(expected, result);
    }

    @Test
    public final void testFindClassAndSuperClass_ValidInput02()
    {
         String input = "public class Test extends SuperTest implements FaceTest{";
         
         ArrayList<Pair<String, String>> expected = new ArrayList<Pair<String,String>>();
         expected.add(new PairImpl<String, String>("Test","SuperTest"));
         
         ArrayList<Pair<String, String>> result = ClassHelper.findClassAndSuperClass(input);
         
         assertEquals(expected, result);
    }
    
    @Test
    public final void testFindClassAndSuperClass_InvalidInput()
    {
         String input = "public class Test{";
         
         ArrayList<Pair<String, String>> expected = new ArrayList<Pair<String,String>>();
         expected.add(new PairImpl<String, String>("Test","null"));
         
         ArrayList<Pair<String, String>> result = ClassHelper.findClassAndSuperClass(input);
         
         assertNotSame(expected, result);
    }
    
    @Test
    public final void testFindClasses_ValidInput()
    {
         String input = "public class Test{\n" +
         		"   public class Sapo{\n" +
         		"   }\n" +
         		"}\n";
         
         ArrayList<String> expected = new ArrayList<String>();
         expected.add(new String("Test"));
         expected.add(new String("Sapo"));

         ArrayList<String> result = ClassHelper.findClasses(input);
         
         assertEquals(expected, result);
    }
}
