package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.PkgGrabberClassVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class JClassAndPkgRecordReader.
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
