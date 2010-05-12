package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ArrayWritablePrintable;
import pt.um.mrc.util.reducers.CollectionReducer;

/**
 * This class is the Reducer for the job that relates packages with the packages
 * they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByPackageReducer extends
                                    CollectionReducer<Text, Text, Text, ArrayWritablePrintable>
{
    
}