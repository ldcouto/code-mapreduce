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
		//visitor.init(fileName, cu.getPackage().getName().toString(), elems);
	}

	@Test
	public void testVisit() {
		visitor.init(fileName, cu.getPackage().getName().toString(), elems);

		visitor.visit(cu, null);

		HashMap<ElemID, Text> expected = new HashMap<ElemID, Text>();
		StringBuilder sb = new StringBuilder();

		sb.append("private Text packge;\n");
		sb.append("int i = 0;\n");
		sb.append("static {\n");
		sb.append("    if (true) i = 0;\n");
		sb.append("}\n");

		ElemID key = new ElemID("", "FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt",IDType.CLASS, null);
		
		expected.put(key, new Text(sb.toString()));
			
		ElemID key2 = new ElemID("", "A", fileName, "pt.um.mrc.jobs.imprt",IDType.CLASS, null);
		
		expected.put(key2, new Text(""));

		assertEquals(expected, elems);
	}
}
