package pt.um.mrc.jobs.allmetrics;

import org.apache.hadoop.mapreduce.OutputFormat;

import pt.um.mrc.util.control.JobInformable;


public interface JobInformableTextFormat extends JobInformable {
	
	  public abstract Class<? extends OutputFormat<?, ?>> getOutputFormatClass();

}
