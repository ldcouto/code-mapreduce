package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.ArrayWritablePrintable;
import pt.um.mrc.util.reducers.CollectionReducer;

/**
 * This class is the Reducer for the job that relates files with the packages
 * they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFileReducer extends CollectionReducer<Text, Text, Text, ArrayWritablePrintable>
{

}