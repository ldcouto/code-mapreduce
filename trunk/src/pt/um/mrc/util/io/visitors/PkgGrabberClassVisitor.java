package pt.um.mrc.util.io.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ClassID;

public class PkgGrabberClassVisitor extends GrabbingVisitor<ClassID>
{
    public void visit(CompilationUnit cu, Object arg)
    {
        for (TypeDeclaration td : cu.getTypes())
        if (td instanceof ClassOrInterfaceDeclaration || td instanceof EnumDeclaration)
        {
            td.setAnnotations(null);
            td.setJavaDoc(null);
            ClassID aux = new ClassID(td.getName(), fileName, packageName);
            elems.put(aux, new Text(""));
        }
    }
}
