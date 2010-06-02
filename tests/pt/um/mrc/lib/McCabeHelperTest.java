package pt.um.mrc.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
                + "        \"if (A == B) then C\""
                + "        \"for (a very big number)\""
                + "        if (classTmp.length > 1)\n"
                + "        {\n"
                + "                String[] superClassAux = classTmp[1].split(\"implements\");\n"
                + "                String superClass = superClassAux[0].trim();\n"
                + "                pairs.add(new PairImpl<String, String>(className, superClass));\n"
                + "        }\n" 
                + "}\n";

        int expected = 3;

        int result = McCabeHelper.countMcCabe(input);

        assertEquals(expected, result);
    }

    @Test
    public final void testFindControlStatements_InvalidInput()
    {
        String input = "public void someMethod(){\n" +
        		"}\n";

        int expected = 1;
        int result = McCabeHelper.countMcCabe(input);

        assertEquals(expected, result);
    }
}
