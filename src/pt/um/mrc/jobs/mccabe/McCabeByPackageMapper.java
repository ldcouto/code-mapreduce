package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.mappers.LineValuesMapper;

/**
 * This class is the Mapper for the job that relates packages with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByPackageMapper extends LineValuesMapper<LongWritable, Text, Text, IntWritable>
{}