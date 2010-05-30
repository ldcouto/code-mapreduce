package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.ClassID;
import pt.um.mrc.util.io.visitors.PkgGrabberClassVisitor;


public class JClassAndPkgRecordReader  extends JRecordReader<ClassID, PkgGrabberClassVisitor> {

	public JClassAndPkgRecordReader() {
		super(new PkgGrabberClassVisitor());
	}
}
