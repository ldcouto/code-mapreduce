package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.GrabMcCabeClassVisitor;

public class JMcClassRecordReader extends JRecordReader<ClassID, GrabMcCabeClassVisitor>
{
    public JMcClassRecordReader()
    {
        super(new GrabMcCabeClassVisitor());
    }
}
