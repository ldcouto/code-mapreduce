package pt.um.mrc.jobs.allmetrics;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AllMetricsReducer extends Reducer<ElemID, MetricValue, ElemID, Text>
{
    @Override
    protected void reduce(ElemID key, Iterable<MetricValue> value, Context context) throws IOException, InterruptedException
    {
        Long aux = 0L;
        
        for (MetricValue metricValue : value)
        {
            if (metricValue.getIsText())
            {
                context.write(key, new Text("\""+metricValue.getText()+"\""));
            } else 
            {
                aux += metricValue.getIntw();
            }
        }
        
        context.write(key, new Text("\""+aux.toString()+"\""));
    }
}
