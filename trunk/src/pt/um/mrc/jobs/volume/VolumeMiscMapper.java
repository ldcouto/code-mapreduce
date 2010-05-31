package pt.um.mrc.jobs.volume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;

/**
 * This class is the mapper for the {@link VolumeByClass} and
 * {@link VolumeByFile} jobs.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class VolumeMiscMapper extends Mapper<WritableComparable<?>, Text, Text, IntWritable>
{

    /** The key out. */
    private Text keyOut = new Text();

    /** The value out. */
    private IntWritable valueOut = new IntWritable();

    /**
     * This overrides the default map method. This method converts the key into
     * a Text value and computes the volume of the value given.
     */
    @Override
    protected void map(WritableComparable<?> key, Text value, Context context) throws IOException, InterruptedException
    {
        keyOut.set(key.toString());
        valueOut.set(VolumeHelper.countLinesOfCode(value.toString()));

        context.write(keyOut, valueOut);
    }
}
