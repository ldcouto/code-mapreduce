package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.McCabeGrabberClassVisitor;

/**
 * An extension of {@link JRecordReader} to read non-method portions of a Class that are needed
 * for Cyclomatic Complexity (such as static blocks). <br>
 * <br>
 * The key of this reader is composed of the package, file and class names
 * and is represented by a ClassID. <br>
 * <br>
 * The value is composed of all statements in a single Text object, separated by \n.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */
public class JMcClassRecordReader extends JRecordReader<ClassID, McCabeGrabberClassVisitor>
{

    /**
     * Instantiates a new {@link JMcClassRecordReader} with the proper Visitor.
     */
    public JMcClassRecordReader()
    {
        super(new McCabeGrabberClassVisitor());
    }
}
