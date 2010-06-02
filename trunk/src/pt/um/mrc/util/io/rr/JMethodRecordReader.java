package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.io.visitors.MethodGrabberClassVisitor;

/**
 * An extension of {@link JRecordReader} to read Methods. <br>
 * <br>
 * The key of this reader is composed of the package, file, class and method names
 * (including parameters) and is represented by a MethodID. <br>
 * <br>
 * The value is composed of the entire method body (including declaration).
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */

public class JMethodRecordReader extends JRecordReader<MethodID, MethodGrabberClassVisitor> {

	/**
	 * Instantiates a new {@link JMethodRecordReader} with the proper Visitor.
	 */
	public JMethodRecordReader() {
		super(new MethodGrabberClassVisitor());
	}
}
