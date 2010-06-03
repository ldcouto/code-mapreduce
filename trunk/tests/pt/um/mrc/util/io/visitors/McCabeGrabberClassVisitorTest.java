package pt.um.mrc.util.io.visitors;

import static org.junit.Assert.assertEquals;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.McCabeGrabberClassVisitor;

public class McCabeGrabberClassVisitorTest
{

    private McCabeGrabberClassVisitor visitor;
    private CompilationUnit cu;
    private String fileName = "TestMats/vtest.java";
    private Map<ClassID, Text> elems;

    @Before
    public void setUp() throws Exception
    {

        FileInputStream in = new FileInputStream(fileName);

        visitor = new McCabeGrabberClassVisitor();
        try
        {
            // parse the file
            cu = JavaParser.parse(in);
        } finally
        {
            in.close();
        }

        elems = new HashMap<ClassID, Text>();
        visitor.init(fileName, cu.getPackage().getName().toString(), elems);
    }

    @Test
    public void testVisit() throws Exception
    {
        visitor.visit(cu, null);

        HashMap<ClassID, Text> expected = new HashMap<ClassID, Text>();
        StringBuilder sb = new StringBuilder();
        sb.append("static {\n");
        sb.append("    if (true) i = 0;\n");
        sb.append("}\n");

        expected.put(new ClassID("FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));

        assertEquals(expected, elems);
    }

    @After
    public void tearDown() throws Exception
    {}

}
