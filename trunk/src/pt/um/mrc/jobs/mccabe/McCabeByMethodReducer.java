package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the Reducer for the job that relates methods with their McCabe
 * number.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByMethodReducer extends SumReducer<MethodID, IntWritable, MethodID, IntWritable>
{}