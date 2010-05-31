package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.ClassMiscMcCabeGrabberFileVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class JClassRecordReader.
 */
public class JClassRecordReader extends JRecordReader<ClassID, ClassMiscMcCabeGrabberFileVisitor>
{
    
    /**
     * Instantiates a new {@link JClassRecordReader}.
     */
    public JClassRecordReader()
    {
        super(new ClassMiscMcCabeGrabberFileVisitor());
    }
}
