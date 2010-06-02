package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.PkgGrabberClassVisitor;
/**
 * An extension of {@link JRecordReader} to read the package name from a class. <br>
 * <br>
 * The key of this reader is composed of the package, file and class names
 * and is represented by a ClassID. <br>
 * <br>
 * The value is an empty Text object since all relevant information is already in the Key.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class JClassAndPkgRecordReader extends JRecordReader<ClassID, PkgGrabberClassVisitor>
{
    
    /**
     * Instantiates a new {@link JClassAndPkgRecordReader}.
     */
    public JClassAndPkgRecordReader()
    {
        super(new PkgGrabberClassVisitor());
    }
}
