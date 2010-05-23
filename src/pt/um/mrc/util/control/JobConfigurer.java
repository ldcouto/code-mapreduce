package pt.um.mrc.util.control;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;

public class JobConfigurer {

	private Class<?> classJar;
	private Class<? extends InputFormat<?, ?>> intputFormat;
	private Path inputPath;
	private Path outputPath;

	public JobConfigurer(Class<?> classJar, Class<? extends InputFormat<?, ?>> intputFormat,
		Path inputPath, Path outputPath) {
		super();
		this.classJar = classJar;
		this.intputFormat = intputFormat;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	public Path getInputPath() {
		return inputPath;
	}

	public Path getOutputPath() {
		return outputPath;
	}

	public Class<? extends InputFormat<?, ?>> getIntputFormat() {
		return intputFormat;
	}

	public Class<?> getClassJar() {
		return classJar;
	}
}
