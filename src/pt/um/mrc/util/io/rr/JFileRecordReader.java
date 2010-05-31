package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.visitors.MiscGrabberFileVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class JFileRecordReader.
 */
public class JFileRecordReader extends JRecordReader<FileID, MiscGrabberFileVisitor>  {

	/**
	 * Instantiates a new {@link JFileRecordReader}.
	 */
	public JFileRecordReader(){
		super(new MiscGrabberFileVisitor());
	}


}
