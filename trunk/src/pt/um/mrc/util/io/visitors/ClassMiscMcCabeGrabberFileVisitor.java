package pt.um.mrc.util.io.visitors;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ClassID;

public class ClassMiscMcCabeGrabberFileVisitor extends GrabbingVisitor<ClassID>
{
    public void visit(ClassOrInterfaceDeclaration c, Object arg)
    {
        if (!c.isInterface())
        {
            ClassID aux = new ClassID(c.getName(), fileName, packageName);
            StringBuilder sb = new StringBuilder();

            for (BodyDeclaration td : c.getMembers())
            {
                if (!(td instanceof MethodDeclaration) && !(td instanceof ConstructorDeclaration))
                {
                    td.setAnnotations(null);
                    td.setJavaDoc(null);
                    sb.append(td.toString());
                    sb.append("\n");
                }
            }
            elems.put(aux, new Text(sb.toString()));
        }
    }
}
