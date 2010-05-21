package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the Reducer for the job that relates methods with their lines
 * of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByMethodReducer extends SumReducer<MethodID, IntWritable, MethodID, IntWritable>
{}