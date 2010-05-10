package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.IdentityReducer;

/**
 * This class is the Reducer for the job that relates files with the package
 * they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class PackageByFileReducer extends IdentityReducer<Text, Text, Text, Text>
{
}
