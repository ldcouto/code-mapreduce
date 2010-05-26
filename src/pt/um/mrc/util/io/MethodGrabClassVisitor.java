package pt.um.mrc.util.io;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.MethodID;

public class MethodGrabClassVisitor extends GrabClassVisitor<MethodID> {


	public void visit(ClassOrInterfaceDeclaration c, Object arg) {
		for (BodyDeclaration td : c.getMembers()) {
			MethodID aux = new MethodID("", c.getName(), fileName, packageName);
			if (td instanceof MethodDeclaration) {
				MethodDeclaration md = (MethodDeclaration) td;
				StringBuilder sb = new StringBuilder();
				sb.append(md.getName());
				if (md.getParameters() != null)
					sb.append(md.getParameters().toString());
				else
					sb.append("[ ]");
				aux.setMethodName(sb.toString());
				elems.put(aux, new Text(td.toString()));
			}
		}
	}
}
