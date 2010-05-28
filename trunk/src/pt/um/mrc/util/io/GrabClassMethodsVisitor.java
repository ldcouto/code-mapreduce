package pt.um.mrc.util.io;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.MethodID;

public class GrabClassMethodsVisitor extends GrabbingVisitor<MethodID> {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		for (BodyDeclaration td : c.getMembers()) {
			MethodID aux = new MethodID("", c.getName(), fileName, packageName);
			if (td instanceof MethodDeclaration) {
				buildEntry(aux, (MethodDeclaration) td);
			}
			if (td instanceof ConstructorDeclaration) {
				buildEntry(aux, (ConstructorDeclaration) td);
			}
		}
	}

	private void buildEntry(MethodID aux, MethodDeclaration md) {
		StringBuilder sb = new StringBuilder();
		sb.append(md.getName());
		if (md.getParameters() != null)
			sb.append(md.getParameters().toString());
		else
			sb.append("[ ]");
		aux.setMethodName(sb.toString());
		md.setAnnotations(null);
		md.setJavaDoc(null);
		elems.put(aux, new Text(md.toString()));
	}

	private void buildEntry(MethodID aux, ConstructorDeclaration cd) {
		StringBuilder sb = new StringBuilder();
		sb.append(cd.getName());
		if (cd.getParameters() != null)
			sb.append(cd.getParameters().toString());
		else
			sb.append("[ ]");
		aux.setMethodName(sb.toString());
		cd.setAnnotations(null);
		cd.setJavaDoc(null);
		elems.put(aux, new Text(cd.toString()));
	}
}
