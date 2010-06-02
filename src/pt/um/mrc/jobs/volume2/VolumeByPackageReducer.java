package pt.um.mrc.jobs.volume2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

public class VolumeByPackageReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{

}
