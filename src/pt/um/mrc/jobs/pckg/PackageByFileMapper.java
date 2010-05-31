package pt.um.mrc.jobs.pckg;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;
import pt.um.mrc.util.datatypes.FileID;

/**
 * This class is the mapper for the {@link PackageByFile} job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PackageByFileMapper extends Mapper<FileID, Text, Text, Text>
{
    /** The filename. */
    private Text filename = new Text();

    /** The defined package. */
    private Text packge = new Text();

    /**
     * This overrides the default map method in order to compute the filname and
     * the defined package and write it to the context.
     */
    @Override
    protected void map(FileID key, Text value, Context context) throws IOException, InterruptedException
    {
        filename.set(key.getFileName());
        packge.set(PckgHelper.findPackage(value.toString()));

        context.write(filename, packge);
    }
}