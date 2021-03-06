package pt.um.mrc.util.io.visitors;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.lib.RegexConstants;
import pt.um.mrc.util.datatypes.MethodID;

public class MethodGrabberClassVisitor extends GrabbingVisitor<MethodID>
{
    public void visit(ClassOrInterfaceDeclaration c, Object arg)
    {
        if (!c.isInterface())
        {
            for (BodyDeclaration td : c.getMembers())
            {
                MethodID aux = new MethodID("", c.getName(), fileName, packageName);
                if (td instanceof MethodDeclaration)
                {
                    buildEntry(aux, (MethodDeclaration) td);
                }
                if (td instanceof ConstructorDeclaration)
                {
                    buildEntry(aux, (ConstructorDeclaration) td);
                }
                if (td instanceof ClassOrInterfaceDeclaration)
                {
                    visit((ClassOrInterfaceDeclaration) td, arg);
                }
            }
        }
    }

    private void buildEntry(MethodID aux, MethodDeclaration md)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(md.getName());
        if (md.getParameters() != null)
            sb.append(md.getParameters().toString());
        else
            sb.append(RegexConstants.EMPTY_ARGUMENTS);
        aux.setMethodName(sb.toString());
        md.setAnnotations(null);
        md.setJavaDoc(null);
        elems.put(aux, new Text(md.toString()));
    }

    private void buildEntry(MethodID aux, ConstructorDeclaration cd)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(cd.getName());
        if (cd.getParameters() != null)
            sb.append(cd.getParameters().toString());
        else
            sb.append(RegexConstants.EMPTY_ARGUMENTS);
        aux.setMethodName(sb.toString());
        cd.setAnnotations(null);
        cd.setJavaDoc(null);
        elems.put(aux, new Text(cd.toString()));
    }
}
