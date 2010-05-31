package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.visitors.MethodGrabberClassVisitor;

// TODO: Auto-generated Javadoc
/**
 * A RecordReader that processes Java Files and feeds them to a Mapper. Files
 * are processed and fed in a record-oriented manner. The reader passes
 * information in the form of (Key, Value) pairs.
 * 
 * The key is composed of the package, file, class and method name (including
 * parameters) and is represented by a MethodID.
 * 
 * The value is composed of the entire method body (including declaration) and
 * is represented by a Text object.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */

public class JMethodRecordReader extends JRecordReader<MethodID, MethodGrabberClassVisitor>
{

    /**
     * Instantiates a new {@link JMethodRecordReader}.
     */
    public JMethodRecordReader()
    {
        super(new MethodGrabberClassVisitor());
    }
}
