package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.datatypes.CollectionWritable;

public class CollectionReducer<KI, VI, KO, VO extends CollectionWritable> extends Reducer<KI, VI, KO, CollectionWritable>
{
    @Override
    public void reduce(KI key, Iterable<VI> values, Context context) throws IOException,
            InterruptedException
    {
        
    }
}
