package pt.um.mrc.util.io;

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


public class GrabClassMiscVisitorTest {
	GrabClassMiscVisitor visitor;
	CompilationUnit cu;
	String fileName = "TestMats/vtest.java";
	Map<ClassID, Text> elems;

	@Before
	public void setUp() throws Exception {
		FileInputStream in = new FileInputStream(fileName);
		
		visitor = new GrabClassMiscVisitor();
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		
		elems = new HashMap<ClassID, Text>();
		visitor.init(fileName, cu.getPackage().getName().toString(), elems);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testVisitClassOrInterfaceDeclarationObject() {
		visitor.visit(cu, null);
		
		HashMap<ClassID,Text> expected = new HashMap<ClassID,Text>();
		StringBuilder sb = new StringBuilder();
	
		sb.append("public enum Day {\n\n");
		sb.append("    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY\n");
		sb.append("}\n");
		sb.append("private Text packge;\n");
		sb.append("int i = 0;\n");
		sb.append("static {\n");
		sb.append("    if (true) i = 0;\n");
		sb.append("}\n");
		
		expected.put(new ClassID("FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));
		
		assertEquals(expected, elems);
	}

}
