package pt.um.mrc.util.io.visitors;

import static org.junit.Assert.*;
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
import pt.um.mrc.util.io.visitors.PkgGrabberClassVisitor;


public class GrabClassAndPkgVisitorTest {

	PkgGrabberClassVisitor visitor;
	CompilationUnit cu;
	String fileName = "TestMats/vtest.java";
	Map<ClassID, Text> elems;
	
	@Before
	public void setUp() throws Exception {
		
		FileInputStream in = new FileInputStream(fileName);
		
		visitor = new PkgGrabberClassVisitor();
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
		expected.put(new ClassID("FindPackagesMapper", fileName, "pt.um.mrc.jobs.imprt"), new Text(""));
		
		assertEquals(expected, elems);
		
	}

}
