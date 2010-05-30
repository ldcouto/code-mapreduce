package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.ClassMiscMcCabeGrabberFileVisitor;

public class JClassRecordReader extends JRecordReader<ClassID, ClassMiscMcCabeGrabberFileVisitor> {

	public JClassRecordReader() {
		super(new ClassMiscMcCabeGrabberFileVisitor());
	}
}
