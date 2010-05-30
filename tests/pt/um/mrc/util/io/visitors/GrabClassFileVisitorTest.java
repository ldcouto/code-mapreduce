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

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.visitors.ClassGrabberFileVisitor;

public class GrabClassFileVisitorTest {

	ClassGrabberFileVisitor visitor;
	CompilationUnit cu;
	String fileName = "TestMats/vtest.java";
	Map<FileID, Text> elems;

	@Before
	public void setUp() throws Exception {
		FileInputStream in = new FileInputStream(fileName);

		visitor = new ClassGrabberFileVisitor();
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		elems = new HashMap<FileID, Text>();
		visitor.init(fileName, cu.getPackage().getName().toString(), elems);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVisitCompilationUnitObject() {

		visitor.visit(cu, null);

		StringBuilder sb = new StringBuilder();
		sb.append("package pt.um.mrc.jobs.imprt;\n");
		sb.append("\n\n");
		sb.append("import java.io.IOException;\n\n");
		sb.append("import org.apache.hadoop.io.Text;\n\n");
		sb.append("import org.apache.hadoop.mapreduce.Mapper;\n\n");
		sb.append("import pt.um.mrc.lib.PckgHelper;\n\n");
		sb.append("public interface jnter {\n\n");
		sb.append("    public void meth1t();\n\n");
		sb.append("    public void meth1();\n");
		sb.append("}");
		
		HashMap<FileID, Text> expected = new HashMap<FileID, Text>();
		expected.put(new FileID(fileName, "pt.um.mrc.jobs.imprt"), new Text(sb.toString()));
		
		assertEquals(expected, elems);
	}

}
