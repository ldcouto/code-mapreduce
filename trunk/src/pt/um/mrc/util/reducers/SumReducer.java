package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * The Class SumReducer, extends the Reducer class in order to process the Sum
 * of the values associated with a given key.
 * 
 * @param <KI>
 *            the generic type.
 * @param <VI>
 *            the generic type should extend IntWritable.
 * @param <KO>
 *            the generic type.
 * @param <VO>
 *            the generic type should extend IntWritable.
 */
public class SumReducer<KI, VI extends IntWritable, KO extends KI, VO extends IntWritable> extends Reducer<KI, IntWritable, KI, IntWritable>
{

    /** The total result of the sum. */
    private IntWritable total = new IntWritable();

    /**
     * This overriden reduce method computes the Sum of all the IntWritable
     * values associated with a key.
     */
    @Override
    protected void reduce(KI key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {
        int sum = 0;

        for (IntWritable elem : values)
        {
            sum += elem.get();
        }

        total.set(sum);
        context.write(key, total);
    }

}
