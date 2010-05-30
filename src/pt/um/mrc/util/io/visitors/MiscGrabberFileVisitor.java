package pt.um.mrc.util.io.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.FileID;

public class MiscGrabberFileVisitor extends GrabbingVisitor<FileID> {

	StringBuilder sb;

	public void visit(CompilationUnit c, Object arg) {
			sb = new StringBuilder();

			if (c.getPackage() != null) {
				sb.append(c.getPackage().toString());
				sb.append("\n");
			}

			if (c.getImports() != null) {
				for (ImportDeclaration id : c.getImports()) {
					sb.append(id.toString());
					sb.append("\n");
				}
			}

			processTypes(c);
		}

	private void processTypes(CompilationUnit c) {
		if (c.getTypes() != null) {
			for (TypeDeclaration t : c.getTypes()) {
				if (t instanceof EnumDeclaration) {
					t.setAnnotations(null);
					t.setJavaDoc(null);
					sb.append(t.toString());
					sb.append("\n");
				}
				
				if (t instanceof ClassOrInterfaceDeclaration) {
					if (((ClassOrInterfaceDeclaration) t).isInterface()) {
						for (BodyDeclaration b : t.getMembers()){
							b.setAnnotations(null);
							b.setJavaDoc(null);
						}
						t.setAnnotations(null);
						t.setJavaDoc(null);
						sb.append(t.toString());
					}
				}
			}
		}
		FileID aux = new FileID(fileName,packageName);
		elems.put(aux,new Text(sb.toString()));
	}
}
