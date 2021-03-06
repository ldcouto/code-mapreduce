package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.mappers.LineValuesMapper;

/**
 * This class is the mapper for {@link VolumeByClass} job. It extends the
 * {@link LineValuesMapper} class which handles the map step.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByClassMapper extends LineValuesMapper<LongWritable, Text, Text, IntWritable>
{
    /**
     * This overriden method simply flags the LineValuesMapper to process
     * information for classes.
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException
    {
        lineContents = IDType.CLASS;
        super.setup(context);
    }
}