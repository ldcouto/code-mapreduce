package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.FileID;


public class JFile2RecordReader extends JRecordReader<FileID, Misc2ClassVisitor>  {

	public JFile2RecordReader(){
		super(new Misc2ClassVisitor());
	}

}
