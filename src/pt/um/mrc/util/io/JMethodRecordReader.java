package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.MethodID;

/**
 * A RecordReader that processes Java Files and feeds them to a Mapper. Files
 * are processed and fed in a record-oriented manner. The reader passes
 * information in the form of (Key, Value) pairs.
 * 
 * The key is composed of the package, file, class and method name (including
 * parameters) and is represented by a MethodID.
 * 
 * The value is composed of the entire method body (including declaration) and
 * is represented by a Text object.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */

public class JMethodRecordReader extends JRecordReader<MethodID,MethodGrabClassVisitor> {

	public JMethodRecordReader(){
		super(new MethodGrabClassVisitor());
	}
}
