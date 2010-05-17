package pt.um.mrc.lib;

import java.util.ArrayList;

import org.apache.hadoop.io.Text;

public class ReduceHelpers
{
    protected ReduceHelpers()
    {}

    public static Text[] toTextArray(Iterable<? extends Text> values)
    {
        // Auxiliary ArrayList to serve as an intermidiate stucture in
        // converting the values into a String array
        ArrayList<Text> aux = new ArrayList<Text>();

        // Add all the values to the auxiliary ArrayList
        for (Text elem : values)
        {
            aux.add(elem);
        }

        aux.trimToSize();

        // Create and initialize a string array
        int size = aux.size();
        Text[] array = new Text[size];
        array = aux.toArray(array);

        return array;
    }
}
