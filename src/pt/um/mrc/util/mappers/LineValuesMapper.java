package pt.um.mrc.util.mappers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.util.datatypes.IDType;
import pt.um.mrc.util.datatypes.Pair;
import pt.um.mrc.util.datatypes.PairImpl;

public class LineValuesMapper<KI, VI, KO, VO> extends Mapper<LongWritable, Text, Text, IntWritable>
{

    private Text outKey = new Text();
    private IntWritable outValue = new IntWritable();
    protected IDType lineContents;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException
    {
        Pair<String, Integer> kv = this.processLine(value.toString());

        outKey.set(kv.getKey());
        outValue.set(kv.getValue());

        context.write(outKey, outValue);
    }

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
