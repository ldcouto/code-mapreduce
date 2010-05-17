package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.lib.ReduceHelpers;
import pt.um.mrc.util.datatypes.ArrayWritablePrintable;

public class CollectionReducer<KI, VI, KO extends KI, VO extends ArrayWritablePrintable>
	extends Reducer<KI, VI, KI, ArrayWritablePrintable> {

	@Override
	public void reduce(KI key, Iterable<VI> values, Context context)
		throws IOException, InterruptedException {
		ArrayWritablePrintable valuesCollection =
			new ArrayWritablePrintable(ReduceHelpers.toStringArray(values));

		context.write(key, valuesCollection);
	}
}
