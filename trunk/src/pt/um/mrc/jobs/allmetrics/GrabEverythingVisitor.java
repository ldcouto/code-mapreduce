package pt.um.mrc.jobs.allmetrics;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
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
public class GrabEverythingVisitor extends GrabbingVisitor<ElemID>
{

    protected String fileName;
    protected String packageName;
    protected Map<ElemID, Text> elems = new TreeMap<ElemID, Text>();

    public GrabEverythingVisitor()
    {
        super();
    }

    public void init(String fName, String pkgName, Map<ElemID, Text> els)
    {
        fileName = fName;
        packageName = pkgName;
        elems = els;
    }

    public class ClassVisitor extends VoidVisitorAdapter<Object>
    {

        public void visit(ClassOrInterfaceDeclaration c, Object arg)
        {
            if (!c.isInterface())
            {
                ElemID aux = new ElemID();
                StringBuilder sb = new StringBuilder();

                aux.setFileName(fileName);
                aux.setPackageName(packageName);
                aux.setClassName(c.getName());
                aux.setIDType(IDType.CLASS);

                if (!c.getMembers().isEmpty())
                {
                    for (BodyDeclaration td : c.getMembers())
                    {
                        td.setAnnotations(null);
                        td.setJavaDoc(null);
                        if (!(td instanceof MethodDeclaration) && !(td instanceof ConstructorDeclaration))
                        {
                            sb.append(td.toString());
                            sb.append("\n");
                        }
                    }
                }
                elems.put(aux, new Text(sb.toString()));
            }
        }
    }

    public class MethodVisitor extends VoidVisitorAdapter<Object>
    {
        public void visit(ClassOrInterfaceDeclaration c, Object arg)
        {
            if (!c.getMembers().isEmpty())
            {
                for (BodyDeclaration td : c.getMembers())
                {
                    ElemID aux = new ElemID("", c.getName(), fileName, packageName, IDType.METHOD, MetricType.NONE);

                    if (td instanceof MethodDeclaration)
                    {
                        buildEntry(aux, (MethodDeclaration) td);
                    }
                    if (td instanceof ConstructorDeclaration)
                    {
                        buildEntry(aux, (ConstructorDeclaration) td);
                    }
                }
            }
        }

        private void buildEntry(ElemID aux, MethodDeclaration md)
        {
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

        private void buildEntry(ElemID aux, ConstructorDeclaration cd)
        {

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



    public void visit(CompilationUnit c, Object arg)
    {
        ElemID aux = new ElemID();

        aux.setIDType(IDType.FILE);
        aux.setFileName(fileName);
        aux.setPackageName(packageName);

        StringBuilder sb = new StringBuilder();

        if (c.getPackage() != null)
        {
            sb.append(c.getPackage().toString());
            //sb.append("\n");
        }
        else
        {
            sb.append("<default>");
            sb.append("\n");
        }

        if (c.getImports() != null)
        {
            for (ImportDeclaration id : c.getImports())
            {
                sb.append(id.toString());
                sb.append("\n");
            }
        }

        processTypes(c, sb);
    }
    
    private void processTypes(CompilationUnit c, StringBuilder sb)
    {
        if (c.getTypes() != null)
        {
            for (TypeDeclaration t : c.getTypes())
            {
                if (t instanceof EnumDeclaration)
                {
                    t.setAnnotations(null);
                    t.setJavaDoc(null);
                    sb.append(t.toString());
                    sb.append("\n");
                }

                if (t instanceof ClassOrInterfaceDeclaration)
                {
                    if (((ClassOrInterfaceDeclaration) t).isInterface())
                    {
                        for (BodyDeclaration b : t.getMembers())
                        {
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
        ElemID aux = new ElemID();
        
        aux.setFileName(fileName);
        aux.setPackageName(packageName);
        aux.setIDType(IDType.FILE);
        
        elems.put(aux, new Text(sb.toString()));
    }
}
