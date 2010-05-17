package pt.um.mrc.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class McCabeHelperTest
{

    @Before
    public void setUp() throws Exception
    {}

    @Test
    public final void testConstructor()
    {
        McCabeHelper cls = new McCabeHelper();

        assertNotNull(cls);
    }

    @Test
    public final void testFindControlStatements_ValidInput()
    {
        String input = "while (classMatcher.find())\n"
                + "{\n"
                + "        String classHeader = classMatcher.group().replaceAll(\"\\{\",\"\");\n"
                + "        String[] classTmp = classHeader.split(\"extends\");\n"
                + "        String[] classNameAux = classTmp[0].split(\"class\");\n"
                + "        String className = classNameAux[1].trim();\n"
                + "        if (classTmp.length > 1)\n"
                + "        {\n"
                + "                String[] superClassAux = classTmp[1].split(\"implements\");\n"
                + "                String superClass = superClassAux[0].trim();\n"
                + "                pairs.add(new PairImpl<String, String>(className, superClass));\n"
                + "        }\n" 
                + "}\n";

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("while");
        expected.add("if");

        ArrayList<String> result = McCabeHelper.findControlStatements(input);

        assertEquals(expected, result);
    }

    @Test
    public final void testFindControlStatements_InvalidInput()
    {
        String input = "";

        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> result = McCabeHelper.findControlStatements(input);

        assertEquals(expected, result);
    }
}
