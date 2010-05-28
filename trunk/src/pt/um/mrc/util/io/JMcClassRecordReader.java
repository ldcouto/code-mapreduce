package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.ClassID;

public class JMcClassRecordReader extends JRecordReader<ClassID, GrabMcCabeClassVisitor> {

	public JMcClassRecordReader() {
		super(new GrabMcCabeClassVisitor());
	}
}
