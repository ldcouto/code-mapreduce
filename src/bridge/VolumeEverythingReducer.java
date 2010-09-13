package bridge;

import org.apache.hadoop.io.IntWritable;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the Reducer for the job that relates methods with their lines
 * of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeEverythingReducer extends SumReducer<GeneralID, IntWritable, GeneralID, IntWritable>
{}