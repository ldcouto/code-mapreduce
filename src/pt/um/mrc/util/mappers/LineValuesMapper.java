package pt.um.mrc.util.mappers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.datatypes.Pair;
import pt.um.mrc.util.datatypes.PairImpl;

/**
 * This mapper class takes a set file with specific formatting and processes it.
 * It is used to process the output of previous jobs in the context of Volume and McCabe 
 * Jobs.
 * <br><br>
 * 
 * Files fed to this mapper must be composed of lines with the results of a previous metric (such as
 * VolumeByMethod). Typically these files are produced by another Job. This Mapper is used in the second part
 * of a composite job and combines the previous results to produce the final output.
 * 
 * @param <KI>
 *            the generic type of the Input Key
 * @param <VI>
 *            the generic type of the Input Value
 * @param <KO>
 *            the generic type of the Output Key
 * @param <VO>
 *            the generic type of the Output Value
 *            
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class LineValuesMapper<KI, VI, KO, VO> extends Mapper<LongWritable, Text, Text, IntWritable>
{

    /** The out key. */
    private Text outKey = new Text();

    /** The out value. */
    private IntWritable outValue = new IntWritable();

    /** The line contents. */
    protected IDType lineContents;

    /**
     * This overriden map method processes the line given as value and
     * constructs a {@link Pair} with the information in that line and outputs
     * them.
     *
     * @param key the key
     * @param value the value
     * @param context the context
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        Pair<String, Integer> kv = this.processLine(value.toString());

        outKey.set(kv.getKey());
        outValue.set(kv.getValue());

        context.write(outKey, outValue);
    }

    /**
     * Process line.
     *
     * @param text the text
     * @return the pair
     */
    private Pair<String, Integer> processLine(String text)
    {
        Pair<String, Integer> p = new PairImpl<String, Integer>();

        String[] aux1 = text.split("\t");
        String[] aux2 = aux1[0].split("\\-");

        int auxLen = 0;

        switch (lineContents)
        {
        case PACKAGE:
            auxLen = 1;
            break;
        case FILE:
            auxLen = 2;
            break;
        case CLASS:
            auxLen = 3;
            break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(aux2[0]);

        for (int i = 1; i < auxLen; i++)
        {
            sb.append('-');
            sb.append(aux2[i]);
        }

        p.setKey(sb.toString());
        p.setValue(Integer.parseInt(aux1[1]));

        return p;
    }
}
