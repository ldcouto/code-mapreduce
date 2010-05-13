package pt.um.mrc.util.datatypes;

import java.io.Serializable;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

/**
 * This class is just a wrapper around the ArrayWritable class. It just overrides the toString() method. 
 * The purpose is mostly to provide a simple way to output final data.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */

public class ArrayWritablePrintable extends ArrayWritable implements Writable
{
	//Supposedly necessary according to Hadoop Docs.
	public ArrayWritablePrintable(){
		super(ArrayWritablePrintable.class);
	}
	
	public ArrayWritablePrintable(String[] strings) {
		super(strings);
	}
	
	public ArrayWritablePrintable(Class<? extends Writable> valueClass,
			Writable[] values) {
		super(valueClass, values);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayWritablePrintable(Class<? extends Writable> valueClass) {
		super(valueClass);
		// TODO Auto-generated constructor stub
	}



	/**
	 * Specialized
	 */
	
    @Override
    public String toString()
    {
    	String[] contents = this.toStrings();
    	
    	StringBuilder sb = new StringBuilder();
        sb.append("{");
        
    	for (String s : contents)
    	{
    	    sb.append(" ");
    	    sb.append(s);
        }
     
        sb.append("}");
        
        return sb.toString();
    }   
}


