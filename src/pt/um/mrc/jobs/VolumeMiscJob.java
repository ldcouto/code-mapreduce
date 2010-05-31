	package pt.um.mrc.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.jobs.volume.VolumeMiscMapper;

public abstract class VolumeMiscJob {

	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return VolumeMiscMapper.class;
	}

	public Class<?> getMapperKeyOutClass() {
		return Text.class;
	}

	public Class<?> getMapperValueOutClass() {
		return IntWritable.class;
	}
}
