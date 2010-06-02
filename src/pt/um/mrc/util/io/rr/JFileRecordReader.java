package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.visitors.MiscGrabberFileVisitor;

/**
 * An extension of {@link JRecordReader} to read non-class portions of a File (such as Enums). <br>
 * <br>
 * The key of this reader is composed of the package and file names
 * and is represented by a FileID. <br>
 * <br>
 * The value is composed of all elements in a single Text object, separated by \n.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */
public class JFileRecordReader extends JRecordReader<FileID, MiscGrabberFileVisitor>  {

	/**
	 * Instantiates a new {@link JFileRecordReader} with the proper Visitor.
	 */
	public JFileRecordReader(){
		super(new MiscGrabberFileVisitor());
	}


}
