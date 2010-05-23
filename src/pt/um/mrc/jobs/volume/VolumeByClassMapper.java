package pt.um.mrc.jobs.volume;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.mappers.LineValuesMapper;

/**
 * This class is the Mapper for the job that relates classes with their lines of
 * code volume.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByClassMapper extends LineValuesMapper<LongWritable, Text, Text, IntWritable>
{}