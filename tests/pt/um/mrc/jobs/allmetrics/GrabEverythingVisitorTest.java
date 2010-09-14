package pt.um.mrc.jobs.allmetrics;

import static org.junit.Assert.assertEquals;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.IDType;

public class GrabEverythingVisitorTest {

	private GrabEverythingVisitor visitor;
	private CompilationUnit cu;
	private String fileName = "TestMats/vtest.java";
	private Map<ElemID, Text> elems;

	@Before
	public void setUp() throws Exception {
		FileInputStream in = new FileInputStream(fileName);

		visitor = new GrabEverythingVisitor();
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		elems = new HashMap<ElemID, Text>();
		visitor.init(fileName, cu.getPackage().getName().toString(), elems);
	}

	@Test
	public void testVisit() {
		visitor.visit(cu, null);

		HashMap<ElemID, Text> expected = new HashMap<ElemID, Text>();
		StringBuilder sb = new StringBuilder();

		sb.append("package pt.um.mrc.jobs.imprt;");
		sb.append("import java.io.IOException;");
		sb.append("import org.apache.hadoop.io.Text;");
		sb.append("import org.apache.hadoop.mapreduce.Mapper;");
		sb.append("import pt.um.mrc.lib.PckgHelper;");
		sb.append("public interface jnter {");
		sb.append("    public void meth1t();");
		sb.append("    public void meth1();");
		sb.append("}");
		sb.append("public enum Day {");
		sb.append("    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY");
		sb.append("}");

		ElemID key = new ElemID();
		key.setFileName(fileName);
		key.setPackageName("pt.um.mrc.jobs.imprt");
		key.setIDType(IDType.FILE);
		
		expected.put(key, new Text(sb.toString()));

		Text txt = elems.get(key);
		
		String actual = txt.toString();
		actual = actual.replaceAll("[\\t\\n\\x0B\\f\\r]", "");
		
		assertEquals(expected.keySet(), elems.keySet());
		assertEquals(sb.toString().replaceAll("[\\t\\n\\x0B\\f\\r]",""), actual);
	}
}
