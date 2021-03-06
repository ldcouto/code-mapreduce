package pt.um.mrc.jobs.allmetrics;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * An adaptation of {@link TestOutputFormat}. This one uses ", " as the field
 * separator
 */
public class CSTextOutputFormat extends TextOutputFormat<LongWritable, Text> {

	@Override
	public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException,
			InterruptedException {
		Configuration conf = job.getConfiguration();
		boolean isCompressed = getCompressOutput(job);
		String keyValueSeparator = conf.get("mapred.textoutputformat.separator", ",");
		CompressionCodec codec = null;
		String extension = "";
		if (isCompressed) {
			Class<? extends CompressionCodec> codecClass =
					getOutputCompressorClass(job, GzipCodec.class);
			codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
			extension = codec.getDefaultExtension();
		}
		Path file = getDefaultWorkFile(job, extension);
		FileSystem fs = file.getFileSystem(conf);
		if (!isCompressed) {
			FSDataOutputStream fileOut = fs.create(file, false);
			return new LineRecordWriter<LongWritable, Text>(fileOut, keyValueSeparator);
		} else {
			FSDataOutputStream fileOut = fs.create(file, false);
			return new LineRecordWriter<LongWritable, Text>(new DataOutputStream(codec
					.createOutputStream(fileOut)), keyValueSeparator);
		}
	}

}
