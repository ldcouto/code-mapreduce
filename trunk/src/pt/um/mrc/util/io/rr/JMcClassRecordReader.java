package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.GrabMcCabeClassVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class JMcClassRecordReader.
 */
public class JMcClassRecordReader extends JRecordReader<ClassID, GrabMcCabeClassVisitor>
{

    /**
     * Instantiates a new {@link JMcClassRecordReader}.
     */
    public JMcClassRecordReader()
    {
        super(new GrabMcCabeClassVisitor());
    }
}
