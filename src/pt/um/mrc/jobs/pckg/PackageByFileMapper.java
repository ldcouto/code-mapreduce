package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This class is the Mapper for the job that relates files with the package
 * they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class PackageByFileMapper extends Mapper<Text, Text, Text, Text>
{
 
}