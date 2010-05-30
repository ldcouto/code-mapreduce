package pt.um.mrc.util.io.rr;

import pt.um.mrc.util.datatypes.FileID;
import pt.um.mrc.util.io.visitors.MiscGrabberFileVisitor;

public class JFileRecordReader extends JRecordReader<FileID, MiscGrabberFileVisitor>  {

	public JFileRecordReader(){
		super(new MiscGrabberFileVisitor());
	}


}
