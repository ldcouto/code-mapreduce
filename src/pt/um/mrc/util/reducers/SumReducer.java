package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer<KI, VI extends IntWritable, KO extends KI, VO extends IntWritable>
                                                                                extends
                                                                                Reducer<KI, IntWritable, KI, IntWritable>
{
    private IntWritable total = new IntWritable();
    
    @Override
    protected void reduce(KI key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException
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
