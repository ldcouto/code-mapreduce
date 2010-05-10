package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * This Reducer class is used to compute a project's list of packages.
 * @author Lu’s Duarte Couto
 * @author Tiago Alves Veloso
 */

public class FindPackagesReducer extends Reducer<Text, Text, Text, Text>
{

}
