package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;

/**
 * This class is the mapper for the {@link McCabeByClass} job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeMiscMapper extends Mapper<WritableComparable<?>, Text, Text, IntWritable>
{

    /** The key out. */
    private Text keyOut = new Text();

    /** The cyclomatic complexity. */
    private IntWritable mcCabeNumber = new IntWritable();

    /**
     * This overrides the default map method. This method converts the key into
     * a Text value and computes the cyclomatic complexity of the value given.
     */
    @Override
    protected void map(WritableComparable<?> key, Text value, Context context) throws IOException, InterruptedException
    {
        keyOut.set(key.toString());
        mcCabeNumber.set(McCabeHelper.countMcCabe(value.toString()));

        context.write(keyOut, mcCabeNumber);
    }
}
