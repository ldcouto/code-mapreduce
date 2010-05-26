package pt.um.mrc.util.io;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ClassID;

public class BodyGrabClassVisitor extends GrabClassVisitor<ClassID> {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		ClassID aux = new ClassID(c.getName(), fileName, packageName);
		StringBuilder sb = new StringBuilder();
		
		for (BodyDeclaration td : c.getMembers()) {
			if (!(td instanceof MethodDeclaration)) {
				sb.append(td.toString());
				sb.append("\n");
			}
		}

		elems.put(aux, new Text(sb.toString()));
	}
}
