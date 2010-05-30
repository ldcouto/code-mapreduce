package pt.um.mrc.util.io.visitors;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ClassID;


public class PkgGrabberClassVisitor extends GrabbingVisitor<ClassID> {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		if (!c.isInterface()){
		ClassID aux = new ClassID(c.getName(), fileName, packageName);
		elems.put(aux, new Text(""));
		}
	}
}
