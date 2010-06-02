package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.ClassMiscGrabberFileVisitor;

/**
 * An extension of {@link JRecordReader} to read non-method portions of a Class. <br>
 * <br>
 * The key of this reader is composed of the package, file and class names
 * and is represented by a ClassID. <br>
 * <br>
 * The value is composed of all elements in a single Text object, separated by \n.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class JClassRecordReader extends JRecordReader<ClassID, ClassMiscGrabberFileVisitor>
{
    
    /**
     * Instantiates a new {@link JClassRecordReader}.
     */
    public JClassRecordReader()
    {
        super(new ClassMiscGrabberFileVisitor());
    }
}
