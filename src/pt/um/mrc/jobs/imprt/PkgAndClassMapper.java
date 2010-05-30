package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.util.datatypes.ClassID;

public class PkgAndClassMapper extends Mapper<ClassID, Text, Text, Text>
{
    private Text cls = new Text();
    private Text pkg = new Text();

    @Override
    protected void map(ClassID key, Text value, Context context) throws IOException, InterruptedException
    {
        cls.set(key.getPackageName() + "." + key.getClassName());
        pkg.set(key.getPackageName());

        context.write(pkg, cls);
    }
}
