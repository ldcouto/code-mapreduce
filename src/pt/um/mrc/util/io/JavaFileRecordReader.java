package pt.um.mrc.util.io;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

import pt.um.mrc.util.Constants;

/**
 * The JavaFileRecordReader extends org.apache.hadoop.mapreduce.RecordReader.
 * 
 * Treats the key as filename and value as the file content.
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JavaFileRecordReader extends RecordReader<Text, Text> {
	
	/** The key. */
	private Text key = null;
	
	/** The value. */
	private Text value = null;
	
	/** The start. */
	private long start;
	
	/** The position in the file. */
	private long pos;
	
	/** The end. */
	private long end;
	
	/** The compression codecs. */
	private CompressionCodecFactory compressionCodecs = null;
	
	/** The Line Reader. */
	private LineReader in;
	
	/** The File Split. */
	private FileSplit split;

	
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	protected Text getKey() {
		return key;
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	protected Text getValue() {
		return value;
	}


	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	protected long getStart() {
		return start;
	}


	/**
	 * Gets the position in the file.
	 *
	 * @return the position in the file.
	 */
	protected long getPos() {
		return pos;
	}


	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	protected long getEnd() {
		return end;
	}


	/**
	 * Gets the compression codecs.
	 *
	 * @return the compression codecs
	 */
	protected CompressionCodecFactory getCompressionCodecs() {
		return compressionCodecs;
	}


	/**
	 * Gets the LineReader.
	 *
	 * @return the LineReader
	 */
	public LineReader getIn() {
		return in;
	}


	/**
	 * Gets the split.
	 *
	 * @return the split
	 */
	public FileSplit getSplit() {
		return split;
	}


	/**
	 * Called once at start up.
	 *
	 * @param genericSplit the generic split
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public void initialize(InputSplit genericSplit, TaskAttemptContext context)
			throws IOException, InterruptedException {
		split = (FileSplit) genericSplit;
		Configuration job = context.getConfiguration();

		start = split.getStart();
		end = start + split.getLength();
		final Path file = split.getPath();
		compressionCodecs = new CompressionCodecFactory(job);
		final CompressionCodec codec = compressionCodecs.getCodec(file);

		// Open the file
		FileSystem fs = file.getFileSystem(job);
		FSDataInputStream fileIn = fs.open(split.getPath());
		if (codec != null) {
			in = new LineReader(codec.createInputStream(fileIn), job);
		} else {
			in = new LineReader(fileIn, job);
		}
	}
	

	/**
	 * In this case it just stores the filename in <code>key</code> and reads
	 * the contents of the file to <code>value</code>.
	 *
	 * @return false if there is nothing more to read.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// Initialize the key
		if (key == null) {
			key = new Text();
		}

		// Set the key to the filename
		key.set(split.getPath().getName());

		// Initialize the value
		if (value == null) {
			value = new Text();
		}

		// Variable to store a read line
		Text line = new Text();

		// Variable to store the file contents
		StringBuilder sb = new StringBuilder();

		// Variable to store the read bytes
		long readBytes = 0;

		// Read the file line-by-line
		while (pos < end) {
			readBytes = in.readLine(line);

			if (readBytes == 0) {
				break;
			}

			pos += readBytes;
			sb.append(line.toString());
		}

		// Set the value to the content of the file
		value.set(sb.toString());

		// Destroy the pair if EOF reached
		if (readBytes == 0) {
			key = null;
			value = null;
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get the current key.
	 *
	 * @return The current key.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	/**
	 * Get the current value.
	 *
	 * @return The current value.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return value;
	}

	/**
	 * Get the progress within the split.
	 *
	 * @return the progress of the reading process.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public float getProgress() throws IOException, InterruptedException {
		if (start == end) {
			return 0.0f;
		} else {
			return Math.min(Constants.HUNDRED_PERCENT, (pos - start) / (float) (end - start));
		}
	}

	/**
	 * Close the input stream when done.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void close() throws IOException {
		if (in != null) {
			in.close();
		}

	}
}
