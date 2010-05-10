package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

/**
 * This class is just a wrapper around the ArrayWritable class. It just overrides the toString() method. 
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class CollectionWritable extends ArrayWritable implements Writable
{

	public CollectionWritable(Class<? extends Writable> valueClass) {
		super(valueClass);
		// TODO Auto-generated constructor stub
	}
   
}
