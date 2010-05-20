package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

public class CollectionReducer<KI, VI extends Text, KO extends KI, VO extends CollectionWritablePrintable>
                                                                                             extends
                                                                                             Reducer<KI, Text, KI, CollectionWritablePrintable>
{

    @Override
    public void reduce(KI key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException
    {        
        CollectionWritablePrintable valuesCollection = new CollectionWritablePrintable(Writable.class,ReduceHelpers.toTextArray(values));
        
        context.write(key, valuesCollection);
    }
}
