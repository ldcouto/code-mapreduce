package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.ClassID;

public class JClassRecordReader extends JRecordReader<ClassID, GrabClassMiscVisitor> {

	public JClassRecordReader() {
		super(new GrabClassMiscVisitor());
	}
}
