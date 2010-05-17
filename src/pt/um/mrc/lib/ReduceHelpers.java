package pt.um.mrc.lib;

import java.util.ArrayList;

public class ReduceHelpers
{
    protected ReduceHelpers()
    {}

    public static String[] toStringArray(Iterable<? extends java.lang.Object> values)
    {
        // Auxiliary ArrayList to serve as an intermidiate stucture in
        // converting the values into a String array
        ArrayList<String> aux = new ArrayList<String>();

        // Add all the values to the auxiliary ArrayList
        for (Object elem : values)
        {
            aux.add(elem.toString());
        }

        aux.trimToSize();

        // Create and initialize a string array
        int size = aux.size();
        String [] array;
        array = new String[size];
        array = aux.toArray(array);

        return array;
    }
}
