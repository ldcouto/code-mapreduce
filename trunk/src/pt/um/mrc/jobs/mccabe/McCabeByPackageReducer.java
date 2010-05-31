package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.SumReducer;

/**
 * This class is the reducer for {@link McCabeByPackage} job. It extends the
 * {@link SumReducer} class wich handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class McCabeByPackageReducer extends SumReducer<Text, IntWritable, Text, IntWritable>
{}