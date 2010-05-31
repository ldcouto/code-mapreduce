package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the reducer for the {@link McCabeByClass} job. It extends
 * {@link SumReducer} class which handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByClassReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}