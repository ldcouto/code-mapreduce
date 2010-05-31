package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the reducer for {@link VolumeByMethod} job. It extends the
 * {@link SumReducer} which handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeByMethodReducer extends SumReducer<MethodID, IntWritable, MethodID, IntWritable>
{}