package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

public class VolumeByClassReducer2 extends SumReducer<Text, IntWritable, Text, IntWritable>
{}
