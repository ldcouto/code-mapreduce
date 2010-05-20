package pt.um.mrc.util.control;

import org.apache.hadoop.mapreduce.Mapper;

public class MapperConfigurer {

	private Class<? extends Mapper<?, ?, ?, ?>> mapperClass;
	private Class<?> mapOutKey;
	private Class<?> mapOutValue;

	public MapperConfigurer(Class<? extends Mapper<?, ?, ?, ?>> mapper,
		Class<?> mapOutKey, Class<?> mapOutValue) {
		this.mapperClass = mapper;
		this.mapOutKey = mapOutKey;
		this.mapOutValue = mapOutValue;
	}

	
	public Class<? extends Mapper<?, ?, ?, ?>> getMapperClass() {
		return mapperClass;
	}

	
	public Class<?> getMapOutKey() {
		return mapOutKey;
	}

	
	public Class<?> getMapOutValue() {
		return mapOutValue;
	}
	
	

}
