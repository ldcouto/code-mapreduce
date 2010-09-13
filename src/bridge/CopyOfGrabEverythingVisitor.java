package bridge;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.io.visitors.GrabbingVisitor;

//FIXME Acho que FUNCIONO
public class CopyOfGrabEverythingVisitor extends GrabbingVisitor<GeneralID> {

	protected String fileName;
	protected String packageName;
	protected Map<GeneralID, Text> elems = new TreeMap<GeneralID, Text>();

	public CopyOfGrabEverythingVisitor() {
		super();
	}

	public void init(String fName, String pkgName, Map<GeneralID, Text> els) {
		fileName = fName;
		packageName = pkgName;
		elems = els;
	}


	public class ClassVisitor extends VoidVisitorAdapter<Object> {

		public void visit(ClassOrInterfaceDeclaration c, Object arg) {
			if (!c.isInterface()) {
				GeneralID aux = new GeneralID();
				StringBuilder sb = new StringBuilder();

				aux.setFileName(fileName);
				aux.setPackageName(packageName);
				aux.setClassName(c.getName());
				aux.setType(IDType.CLASS);

				for (BodyDeclaration td : c.getMembers()) {
					td.setAnnotations(null);
					td.setJavaDoc(null);
					if (!(td instanceof MethodDeclaration)
							&& !(td instanceof ConstructorDeclaration)) {
						sb.append(td.toString());
						sb.append("\n");
					}
				}
				elems.put(aux, new Text(sb.toString()));
			}
		}
	}


	public class MethodVisitor extends VoidVisitorAdapter<Object> {

		public void visit(ClassOrInterfaceDeclaration c, Object arg) {

			for (BodyDeclaration td : c.getMembers()) {
				GeneralID aux =
						new GeneralID("", c.getName(), fileName, packageName, IDType.METHOD);
				if (td instanceof MethodDeclaration) {
					buildEntry(aux, (MethodDeclaration) td);
				}
				if (td instanceof ConstructorDeclaration) {
					buildEntry(aux, (ConstructorDeclaration) td);
				}
			}
		}

		private void buildEntry(GeneralID aux, MethodDeclaration md) {
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

		private void buildEntry(GeneralID aux, ConstructorDeclaration cd) {
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

	public void visit(CompilationUnit c, Object arg) {
		GeneralID aux = new GeneralID();

		aux.setType(IDType.FILE);
		aux.setFileName(fileName);
		aux.setPackageName(packageName);

		StringBuilder sbFile = new StringBuilder();

		if (c.getImports() != null)
			sbFile.append(c.getImports().toString() + '\n');
		if (c.getPackage() != null)
			sbFile.append(c.getPackage().toString() + '\n');
		else
			sbFile.append("<default>\n");

		for (TypeDeclaration t : c.getTypes()) {
			t.setAnnotations(null);
			t.setJavaDoc(null);

			if (t instanceof EnumDeclaration) {
				for (BodyDeclaration b : t.getMembers()){
					b.setAnnotations(null);
					b.setJavaDoc(null);
				}
				sbFile.append(t.toString());
			}

			if (t instanceof ClassOrInterfaceDeclaration) {
				if (((ClassOrInterfaceDeclaration) t).isInterface()){
					for (BodyDeclaration b : t.getMembers()){
						b.setAnnotations(null);
						b.setJavaDoc(null);
					}
					sbFile.append(t.toString());	
				}
				

			}
			elems.put(aux, new Text(sbFile.toString()));
		}
	}

}
