package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.ClassID;


public class JClassAndPkgRecordReader  extends JRecordReader<ClassID, GrabClassAndPkgVisitor> {

	public JClassAndPkgRecordReader() {
		super(new GrabClassAndPkgVisitor());
	}
}
