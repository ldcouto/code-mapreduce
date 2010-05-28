package pt.um.mrc.jobs.pckg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * package they define.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PackageByFile implements JobInformable
{
    
    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getUsage()
     */
    public String getUsage()
    {
        return "Usage: PackageByFile <in> <out>";
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getMapperClass()
     */
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return PackageByFileMapper.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getMapperKeyClass()
     */
    public Class<?> getMapperKeyClass()
    {
        return Text.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getMapperValueClass()
     */
    public Class<?> getMapperValueClass()
    {
        return Text.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getInputFormatClass()
     */
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return JFileInputFormat.class;
    }

    /* (non-Javadoc)
     * @see pt.um.mrc.util.control.JobInformable#getReducerClass()
     */
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return PackageByFileReducer.class;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args)
    {
        PackageByFile me = new PackageByFile();
        JobRunner.setJob(args, me);
        JobRunner.runJob();
    }

	@Override
	public int getArgCount() {
		return 2;
	}
}