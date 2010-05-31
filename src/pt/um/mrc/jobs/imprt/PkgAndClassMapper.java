package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.util.datatypes.ClassID;

/**
 * This class is the mapper for the auxiliary {@link PkgAndClassCache} job.
 * 
 * This job simply finds the necessary information and passes it on to the
 * reducer.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PkgAndClassMapper extends Mapper<ClassID, Text, Text, Text>
{

    /** Information about the class. */
    private Text cls = new Text();

    /** Information about the package. */
    private Text pkg = new Text();

    /**
     * This map function simply takes all the information it needs from the key
     * and outputs it to the next step.
     */
    @Override
    protected void map(ClassID key, Text value, Context context) throws IOException, InterruptedException
    {
        cls.set(key.getPackageName() + "." + key.getClassName());
        pkg.set(key.getPackageName());

        context.write(pkg, cls);
    }
}
