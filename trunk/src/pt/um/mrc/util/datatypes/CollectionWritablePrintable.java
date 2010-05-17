package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * This class is just a wrapper around the ArrayWritable class. It just
 * overrides the toString() method. The purpose is mostly to provide a simple
 * way to output final data.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */

public class CollectionWritablePrintable extends ArrayWritable
{
    public CollectionWritablePrintable()
    {
        super(Text.class);
    }

    public CollectionWritablePrintable(Class<? extends Writable> valueClass, Writable[] values)
    {
        super(valueClass, values);
    }
    
    /**
     * Specialized
     */
    @Override
    public String toString()
    {
        Writable[] contents = this.get();

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Writable w : contents)
        {
            if (w != null)
            {
                sb.append(" ");
                sb.append(w.toString());
            }
        }

        sb.append("}");

        return sb.toString();
    }
}
