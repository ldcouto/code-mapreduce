package pt.um.mrc.util.reducers;

import java.util.TreeSet;

import org.apache.hadoop.io.Text;

/**
 * The Class ReduceHelpers is a static class with helper methods used on various
 * reducers.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class ReduceHelpers
{

    /**
     * Protect constructor since it is a static only class
     */
    protected ReduceHelpers()
    {}

    /**
     * Converts an Iterable of texts into an array of Texts
     * 
     * @param values
     *            the values
     * @return the text[]
     */
    public static Text[] toTextArray(Iterable<? extends Text> values)
    {
        // Auxiliary ArrayList to serve as an intermediate structure in
        // converting the values into a String array
        TreeSet<Text> aux = new TreeSet<Text>();

        // Add all the values to the auxiliary ArrayList
        for (Text elem : values)
        {
        	//TODO investigate why this silly workaround is needed
            aux.add(new Text(elem.toString()));

        }

        // Create and initialize a string array
        int size = aux.size();
        Text[] array = new Text[size];
        array = aux.toArray(array);
        return array;
    }
}
