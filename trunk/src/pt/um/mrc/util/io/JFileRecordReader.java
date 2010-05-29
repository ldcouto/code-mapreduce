package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.FileID;


public class JFileRecordReader extends JRecordReader<FileID, Misc2ClassVisitor>  {

	public JFileRecordReader(){
		super(new Misc2ClassVisitor());
	}

}
