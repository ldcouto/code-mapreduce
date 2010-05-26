package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.ClassID;

public class JClassRecordReader extends JRecordReader<ClassID, BodyGrabClassVisitor> {

	public JClassRecordReader() {
		super(new BodyGrabClassVisitor());
	}
}
