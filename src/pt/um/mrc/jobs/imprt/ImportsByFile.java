package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;
import pt.um.mrc.util.io.JavaFileInputFormat;

/**
 * This class contains the configuration for the job that relates files with the
 * packages they import.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFile
{

    static Configuration conf = new Configuration();
    static FileSystem fs;
    static String cache = "tmpCache/";


    
    public static void main(String[] args) throws Exception
    {
    	PIBFJob1 job1 = new ImportsByFile.PIBFJob1();
    	String[] j1Args = args;
    	j1Args[0]=cache;
    	
    	JobRunner.setJob(j1Args, job1);
        int status = JobRunner.runJob();
    	if (status != 0)
			System.exit(status);
    	
        // Prepare the Cache for the second Job
        JobRunner.configureDistCache(new Path(cache));
        
       	PIBFJob2 job2 = new ImportsByFile.PIBFJob2();
    	String[] j2Args = args;
    	j1Args[1]=cache;
    	
    	JobRunner.setJob(j2Args, job2);
    	status=JobRunner.runJob();
    	FileSystem.get(JobRunner.getConf()).delete(new Path(cache), true);

    	System.exit(status);
}
    
    protected static class PIBFJob1 implements JobInformable {

    	@Override
    	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
    		return JavaFileInputFormat.class;
    	}

    	@Override
    	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
    		return PkgAndClassMapper.class;
    	}

    	@Override
    	public Class<?> getMapperKeyClass() {
    		return Text.class;
    	}

    	@Override
    	public Class<?> getMapperValueClass() {
    		return Text.class;
    	}

    	@Override
    	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
    		return PkgAndClassReducer.class;
    	}

    	@Override
    	public String getUsage() {
    		return "Usage: ImportsByFile <in> <cache>";
    	}

		@Override
		public int getArgCount() {
			return 2;
		}

    }
    
    protected static class PIBFJob2 implements JobInformable {

    	@Override
    	public Class<? extends InputFormat<?, ?>> getInputFormatClass() {
    		return JavaFileInputFormat.class;
    	}

    	@Override
    	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
    		return ImportsByFileMapper.class;
    	}

    	@Override
    	public Class<?> getMapperKeyClass() {
    		return Text.class;
    	}

    	@Override
    	public Class<?> getMapperValueClass() {
    		return Text.class;
    	}

    	@Override
    	public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass() {
    		return ImportsByFileReducer.class;
    	}

    	@Override
    	public String getUsage() {
    		return "Usage: ImportsByFile <cache> <out>";
    	}

		@Override
		public int getArgCount() {
			return 2;
		}

    }
   
}