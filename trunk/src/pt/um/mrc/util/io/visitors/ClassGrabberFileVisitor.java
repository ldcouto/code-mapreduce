package pt.um.mrc.util.io.visitors;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.FileID;

public class ClassGrabberFileVisitor extends GrabbingVisitor<FileID>
{
    public void visit(CompilationUnit c, Object arg)
    {
        StringBuilder sb = new StringBuilder();

        if (c.getPackage() != null)
        {
            sb.append(c.getPackage().toString());
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

        if (c.getTypes() != null)
        {
            for (TypeDeclaration t : c.getTypes())
            {
                if (t instanceof EnumDeclaration)
                {
                    sb.append(t.toString());
                    sb.append("\n");
                }
                if (t instanceof ClassOrInterfaceDeclaration)
                {
                    if (((ClassOrInterfaceDeclaration) t).isInterface())
                    {
                        sb.append(t.toString());
                    }
                }
            }
        }
        FileID aux = new FileID(fileName, packageName);
        elems.put(aux, new Text(sb.toString()));
        // everything = sb.toString();
    }

}
