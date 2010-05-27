package pt.um.mrc.jobs.volume;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import pt.um.mrc.util.control.JobInformable;
import pt.um.mrc.util.control.JobRunner;

public class VolumeByClass3 implements JobInformable
{

    @Override
    public Class<? extends InputFormat<?, ?>> getInputFormatClass()
    {
        return TextInputFormat.class;
    }

    @Override
    public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass()
    {
        return VolumeByClassMapper.class;
    }

    @Override
    public Class<?> getMapperKeyClass()
    {
        return Text.class;
    }

    @Override
    public Class<?> getMapperValueClass()
    {
        return IntWritable.class;
    }

    @Override
    public Class<? extends Reducer<?, ?, ?, ?>> getReducerClass()
    {
        return VolumeByClassReducer.class;
    }

    @Override
    public String getUsage()
    {
        return "Usage: VolumeByClass3 <methodloc> <in> <out>";
    }

    public static void main(String[] args) throws Exception
    {        
        VolumeByClass2 me2 = new VolumeByClass2();
        String[] argsJob1 = {args[1] , "tmp2/"}; 
        JobRunner.setJob(argsJob1, me2);
        
        int jobStatus1 = JobRunner.runJob();
        if (jobStatus1 != 0)
            System.exit(jobStatus1);
        
        // Making sure the job is being correctly executed
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path tmp = new Path("tmp");
        
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2)
        {
            System.err.println("Usage: VolumeByClass3 <methodloc> <in> <out>");
            System.exit(2);
        }
        
        // Configuring the job
        Job job = new Job(conf);
        job.setJarByClass(VolumeByClass3.class);
        job.setMapperClass(VolumeByClassMapper.class);
        job.setReducerClass(VolumeByClassReducer.class);

        // the map output is Text, Text
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.addInputPath(job, tmp);
        
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
        
        boolean jobStatus2 = job.waitForCompletion(true);
       
        fs.delete(tmp,true);
        
        System.exit(jobStatus2 ? 0 : 1);
    }
}
