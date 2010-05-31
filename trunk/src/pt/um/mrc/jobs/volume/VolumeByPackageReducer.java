package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the reducer {@link VolumeByPackage} job. It extends the
 * {@link SumReducer} class which handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeByPackageReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}