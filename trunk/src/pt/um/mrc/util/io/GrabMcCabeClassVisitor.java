package pt.um.mrc.util.io;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.InitializerDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ClassID;

public class GrabMcCabeClassVisitor extends GrabbingVisitor<ClassID> {

	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		ClassID aux = new ClassID(c.getName(), fileName, packageName);
		StringBuilder sb = new StringBuilder();
		
		for (BodyDeclaration td : c.getMembers()) {
			if (td instanceof InitializerDeclaration) {
				sb.append(td.toString());
				sb.append("\n");
			}
		}
		if (sb.toString().length()>0)
			elems.put(aux, new Text(sb.toString()));
	}
}
