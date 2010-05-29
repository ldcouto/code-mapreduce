package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.FileID;


public class JFileRecordReader extends JRecordReader<FileID, GrabClassFileVisitor>  {

	public JFileRecordReader(){
		super(new GrabClassFileVisitor());
	}

}
