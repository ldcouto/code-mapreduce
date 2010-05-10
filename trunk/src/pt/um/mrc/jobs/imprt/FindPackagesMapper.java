package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This Mapper class is used to compute a project's list of packages.
 * @author Lu’s Duarte Couto
 * @author Tiago Alves Veloso
 */

public class FindPackagesMapper extends Mapper<Text, Text, Text, Text>
{

}
