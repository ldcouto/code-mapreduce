package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

/**
 * This class is just a wrapper around the ArrayWritable class. It just
 * overrides the toString() method. The purpose is mostly to provide a simple
 * way to output final data.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */

public class StringAWP extends ArrayWritablePrintable {
	
	// Supposedly necessary according to Hadoop Docs.
	public StringAWP() {
		super(StringAWP.class);
	}

	public StringAWP(String[] strings) {
		super(strings);
		// TODO Auto-generated constructor stub
	}

	
	
}
