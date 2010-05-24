/*
 * 
 */
package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

/**
 * The Class CollectionReducer. This class extends the Reducer Class in order to
 * output a list of values.
 * 
 * @param <KI>
 *            the generic type.
 * @param <VI>
 *            the generic type should extend the org.apache.hadoop.io.Text class.
 * @param <KO>
 *            the generic type should be the same type as <KI>.
 * @param <VO>
 *            the generic type should extend the CollectionWritablePrintable class.
 *            
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class CollectionReducer<KI, VI extends Text, KO extends KI, VO extends CollectionWritablePrintable>
                                                                                                           extends
                                                                                                           Reducer<KI, Text, KI, CollectionWritablePrintable>
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.mapreduce.Reducer#reduce(KEYIN,
     * java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
     */
    @Override
    public void reduce(KI key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException
    {
        CollectionWritablePrintable valuesCollection = new CollectionWritablePrintable(
                Writable.class, ReduceHelpers.toTextArray(values));

        context.write(key, valuesCollection);
    }
}
