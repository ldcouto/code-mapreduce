package pt.um.mrc.jobs.volume2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.VolumeHelper;

public class VolumeCommonMapper<KI, VI, KO extends KI, VO> extends Mapper<KI, Text, KI, IntWritable>
{
    /** The number of lines of code in the body of a method. */
    private IntWritable lines = new IntWritable();

    /**
     * This overrides the default map method in order to compute the lines of
     * code in the given value
     */
    @Override
    protected void map(KI key, Text value, Context context) throws IOException, InterruptedException
    {
        lines.set(VolumeHelper.countLoCUnformatted(value.toString()));
        context.write(key, lines);
    }
}
