package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.mappers.LineValuesMapper;

/**
 * This class is the mapper for the {@link VolumeByFile} job. It extends the
 * {@link LineValuesMapper} class which handles the map step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class VolumeByFileMapper extends LineValuesMapper<LongWritable, Text, Text, IntWritable>
{

    /**
     * This overriden method simply flags the LineValuesMapper to process
     * information for files.
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException
    {
        lineContents = IDType.FILE;
        super.setup(context);
    }
}