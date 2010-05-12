package pt.um.mrc.lib;

import java.util.ArrayList;

public class ReduceHelpers
{    
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

        // Create and initialize a string array
        String[] array = aux.toArray(new String[aux.size()]);

        return array;
    }
}
