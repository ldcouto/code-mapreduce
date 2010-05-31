package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * This class is just a wrapper around the ArrayWritable class. It just
 * overrides the toString() method. The purpose is mostly to provide a simple
 * way to output final data.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class CollectionWritablePrintable extends ArrayWritable
{
    public CollectionWritablePrintable()
    {
        super(Text.class);
    }

    /**
     * Instantiates a new collection writable printable.
     * 
     * @param valueClass
     *            the value class
     * @param values
     *            the values
     */
    public CollectionWritablePrintable(Class<? extends Writable> valueClass, Writable[] values)
    {
        super(valueClass, values);
    }

    /**
     * Specialized toString method to compose an array representation of this
     * collection.
     * 
     * @return the string
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
