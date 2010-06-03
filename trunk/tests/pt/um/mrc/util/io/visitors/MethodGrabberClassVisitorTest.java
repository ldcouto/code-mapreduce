package pt.um.mrc.util.io.visitors;

import static org.junit.Assert.assertEquals;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.MethodID;

public class MethodGrabberClassVisitorTest
{
    
    private MethodGrabberClassVisitor visitor;
    private CompilationUnit cu;
    private String fileName = "TestMats/vtest.java";
    private Map<MethodID, Text> elems;

    @Before
    public void setUp() throws Exception
    {

        FileInputStream in = new FileInputStream(fileName);

        visitor = new MethodGrabberClassVisitor();
        try
        {
            // parse the file
            cu = JavaParser.parse(in);
        } finally
        {
            in.close();
        }

        elems = new HashMap<MethodID, Text>();
        visitor.init(fileName, cu.getPackage().getName().toString(), elems);
    }

    @Test
    public void testVisit() throws Exception
    {
        visitor.visit(cu, null);

        HashMap<MethodID, Text> expected = new HashMap<MethodID, Text>();
        StringBuilder sb = new StringBuilder();
        sb.append("public void visit(MethodDeclaration n, Object arg) {\n");
        sb.append("    System.out.println(n.toString());\n");
        sb.append("    System.out.println(n.haha());\n");
        sb.append("}");
        
        expected.put(new MethodID("visit[MethodDeclaration n, Object arg]","FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));

        sb.delete(0, sb.length());
        sb.append("protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {\n");
        sb.append("    packge.set(PckgHelper.findPackage(value.toString()));\n");
        sb.append("    context.write(packge, new Text(\"\"));\n");
        sb.append("}");
        
        expected.put(new MethodID("map[Text key, Text value, Context context]","FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));

        sb.delete(0, sb.length());
        sb.append("public FindPackagesMapper() {\n");
        sb.append("}");
        
        expected.put(new MethodID("FindPackagesMapper[ ]","FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));
        
        assertEquals(expected, elems);
    }

}
