package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

/**
 * This class contains common information used by several jobs, namely the jobs
 * that use {@link TextInputFormat} as the {@link InputFormat}, Text as the key output of the
 * mapper and finally {@link IntWritable} as the output value of mapper.
 */
public abstract class Text2IntJob
{

    /**
     * Gets the class of the InputFormat used in this job
     * 
     * @return the InputFormat class used in this job
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return TextInputFormat.class;
    }

    /**
     * Gets the class of the output key given by the defined mapper.
     * 
     * @return the class of the output key given by the defined mapper
     */
    public Class<?> getMapperKeyOutClass()
    {
        return Text.class;
    }

    /**
     * Gets the class of the output value given by the defined mapper.
     * 
     * @return the class of the output value given by the defined mapper
     */
    public Class<?> getMapperValueOutClass()
    {
        return IntWritable.class;
    }

}
