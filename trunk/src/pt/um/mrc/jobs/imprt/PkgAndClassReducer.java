package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.IdentityReducer;

public class PkgAndClassReducer extends IdentityReducer<Text, Text, Text, Text>
{
    //TODO This may need to do something
//    @Override
//    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
//            InterruptedException
//    {
//        context.write(key, new Text(""));
//    }
}
