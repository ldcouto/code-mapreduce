package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the Reducer for the job that relates packages with their lines
 * of code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByPackageReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}