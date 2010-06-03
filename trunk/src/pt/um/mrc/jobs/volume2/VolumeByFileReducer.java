package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * The Class VolumeByFileReducer.
 */
public class VolumeByFileReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}
