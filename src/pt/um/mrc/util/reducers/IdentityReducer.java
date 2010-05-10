package pt.um.mrc.util.reducers;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class IdentityReducer<KI, VI, KO, VO> extends Reducer<KI, VI, KO, VO>
{
    @Override
    protected void reduce(KI key, Iterable<VI> values, Context context) throws IOException,
            InterruptedException
    {
        super.reduce(key, values, context);
    }
}
