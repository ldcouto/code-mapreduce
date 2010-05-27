package pt.um.mrc.util.io;

import pt.um.mrc.util.datatypes.GeneralID;


public class JEverythingRecordReader extends JRecordReader<GeneralID, GrabEverythingVisitor>{
	
	public JEverythingRecordReader() {
		super(new GrabEverythingVisitor());
	}
}
