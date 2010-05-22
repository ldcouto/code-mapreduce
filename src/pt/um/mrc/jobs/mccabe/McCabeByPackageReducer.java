package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the Reducer for the job that relates packages with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByPackageReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}