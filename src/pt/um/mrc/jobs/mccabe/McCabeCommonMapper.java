package pt.um.mrc.jobs.mccabe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;
import pt.um.mrc.util.datatypes.Pair;

public class McCabeCommonMapper<KI, VI, KO, VO> extends
                                                Mapper<LongWritable, Text, Text, IntWritable>
{
    private Text outKey = new Text();
    private IntWritable outValue = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException
    {
        // TODO: Move processKeyValue to another common lib.
        Pair<String, Integer> keyValuePair = VolumeHelper.processKeyValue(value.toString());

        outKey.set(keyValuePair.getKey());
        outValue.set(keyValuePair.getValue());

        context.write(outKey, outValue);
    }
}
