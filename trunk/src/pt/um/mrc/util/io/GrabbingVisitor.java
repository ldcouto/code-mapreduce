package pt.um.mrc.util.io;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public abstract class GrabbingVisitor<ID extends WritableComparable<ID>> extends VoidVisitorAdapter<Object> {

	String fileName;
	String packageName;
	Map<ID, Text> elems= new HashMap<ID,Text>();

	public GrabbingVisitor() {
		super();
	}

	public void init(String fileName, String packageName, Map<ID, Text> elems) {
		this.fileName = fileName;
		this.packageName = packageName;
		this.elems = elems;
	}
	
	public void visit(ClassOrInterfaceDeclaration c, Object arg) {}
		
}
