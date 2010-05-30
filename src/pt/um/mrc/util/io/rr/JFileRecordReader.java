package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.visitors.ClassGrabberFileVisitor;

public class JFileRecordReader extends JRecordReader<FileID, ClassGrabberFileVisitor>
{
    public JFileRecordReader()
    {
        super(new ClassGrabberFileVisitor());
    }

}
