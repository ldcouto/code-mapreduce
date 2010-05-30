package pt.um.mrc.jobs.pckg;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;
import pt.um.mrc.util.datatypes.FileID;

/**
 * This class is the Mapper for the job that relates files with the package they
 * define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PackageByFileMapper extends Mapper<FileID, Text, Text, Text>
{

    /** The packge. */
    private Text packge = new Text();

    /** The filename. */
    private Text filename = new Text();

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN,
     * org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(FileID key, Text value, Context context) throws IOException, InterruptedException
    {
        filename.set(key.getFileName());
        packge.set(PckgHelper.findPackage(value.toString()));

        context.write(filename, packge);
    }
}