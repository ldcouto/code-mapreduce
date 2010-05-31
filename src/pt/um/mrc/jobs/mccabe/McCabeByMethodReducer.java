package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;

import pt.um.mrc.util.datatypes.MethodID;
import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the reducer for the {@link McCabeByMethod} job. It extends the
 * {@link SumReducer} class which handles the reduce step.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByMethodReducer extends SumReducer<MethodID, IntWritable, MethodID, IntWritable>
{}