package pt.um.mrc.util.control;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;

/**
 * The Class JobConfigurer is used as an auxiliary class to configure a
 * Hadoop job.
 *
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class JobConfigurer {

	/** The class jar. */
	private Class<?> classJar;
	
	/** The intput format. */
	private Class<? extends InputFormat<?, ?>> intputFormat;
	
	/** The input path. */
	private Path inputPath;
	
	/** The output path. */
	private Path outputPath;

	/**
	 * Instantiates a new job configurer.
	 *
	 * @param classJar the class jar
	 * @param intputFormat the intput format
	 * @param inputPath the input path
	 * @param outputPath the output path
	 */
	public JobConfigurer(Class<?> classJar, Class<? extends InputFormat<?, ?>> intputFormat,
		Path inputPath, Path outputPath) {
		this.classJar = classJar;
		this.intputFormat = intputFormat;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	/**
	 * Gets the input path.
	 *
	 * @return the input path
	 */
	public Path getInputPath() {
		return inputPath;
	}

	/**
	 * Gets the output path.
	 *
	 * @return the output path
	 */
	public Path getOutputPath() {
		return outputPath;
	}

	/**
	 * Gets the intput format.
	 *
	 * @return the intput format
	 */
	public Class<? extends InputFormat<?, ?>> getIntputFormat() {
		return intputFormat;
	}

	/**
	 * Gets the class jar.
	 *
	 * @return the class jar
	 */
	public Class<?> getClassJar() {
		return classJar;
	}
}
