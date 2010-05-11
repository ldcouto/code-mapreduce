package pt.um.mrc.util.datatypes;

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

	public ArrayWritablePrintable(String[] strings) {
		super(strings);
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


