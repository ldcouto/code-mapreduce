package pt.um.mrc.util.io;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.GeneralID;
import pt.um.mrc.util.datatypes.GeneralID.IDType;

public class GrabEverythingVisitor extends GrabbingVisitor<GeneralID> {

	String fileName;
	String packageName;
	Map<GeneralID, Text> elems = new HashMap<GeneralID, Text>();

	public GrabEverythingVisitor() {
		super();
	}

	public void init(String fileName, String packageName, Map<GeneralID, Text> elems) {
		this.fileName = fileName;
		this.packageName = packageName;
		this.elems = elems;
	}

	public void visit(CompilationUnit c, Object arg) {
		GeneralID aux = new GeneralID();
		aux.setFileName(fileName);
		aux.setPackageName(packageName);
		aux.setType(IDType.FILE);

		elems.put(aux, new Text(c.getImports().toString()));
		elems.put(aux, new Text(c.getPackage().toString()));

		for (TypeDeclaration t : c.getTypes()) {
			if (t instanceof EnumDeclaration)
				elems.put(aux, new Text(t.toString()));
			if (t instanceof ClassOrInterfaceDeclaration) {
				ClassOrInterfaceDeclaration coi = (ClassOrInterfaceDeclaration) t;
				if (!coi.isInterface()) {
					for (BodyDeclaration b : coi.getMembers()) {
						if (!(b instanceof MethodDeclaration)) {
							aux.setType(IDType.CLASS);
							aux.setClassName(coi.getName());
							elems.put(aux, new Text(b.toString()));
						} else if (b instanceof MethodDeclaration) {
							aux.setType(IDType.METHOD);
							MethodDeclaration md = (MethodDeclaration) b;
							StringBuilder sb = new StringBuilder();
							sb.append(md.getName());
							if (md.getParameters() != null)
								sb.append(md.getParameters().toString());
							else
								sb.append("[ ]");
							aux.setMethodName(sb.toString());
							// b.setJavaDoc(null);
							// b.setAnnotations(null);
							elems.put(aux, new Text(md.toString()));
						}
					}
				} else {
					aux.setType(IDType.FILE);
					elems.put(aux, new Text(coi.toString()));
				}
			}
		}

	}

}
