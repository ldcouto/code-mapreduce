package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.McCabeHelper;
import pt.um.mrc.util.datatypes.MethodID;

/**
 * This class is the mapper for the {@link McCabeByMethod} job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class McCabeByMethodMapper extends Mapper<MethodID, Text, MethodID, IntWritable>
{
    /** The cyclomatic complexity. */
    private IntWritable mcCabeNumber = new IntWritable();

    /**
     * This overrides the default map method in order to compute the cyclomatic
     * complexity for the given <code>value</code>.
     */
    @Override
    protected void map(MethodID key, Text value, Context context) throws IOException, InterruptedException
    {
    	//System.err.println("McCabeByMethodMapper vai processar " + key.toString());
        mcCabeNumber.set(McCabeHelper.countMcCabe(value.toString()));
        context.write(key, mcCabeNumber);
    }
}