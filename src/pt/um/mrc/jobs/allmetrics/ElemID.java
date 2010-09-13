package pt.um.mrc.jobs.allmetrics;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import pt.um.mrc.util.Constants;
import pt.um.mrc.util.datatypes.IDType;

// TODO: Auto-generated Javadoc
/**
 * The Class MethodID represents an Identifier of a method. It's composed by the
 * method name, the class name, the file name and the package name.
 */

public class ElemID implements WritableComparable<ElemID> {

	/** The method name. */
	String methodName;

	/** The class name. */
	String className;

	/** The file name. */
	String fileName;

	/** The package name. */
	String packageName;

	MetricType metricType;

	IDType idType;

	/**
	 * Instantiates a new MethodID.
	 */
	public ElemID() {
		methodName = "";
		className = "";
		fileName = "";
		packageName = "";
		metricType = null;
	}

	/**
	 * Instantiates a new MethodID.
	 * 
	 * @param methodName
	 *            the method name
	 * @param className
	 *            the class name
	 * @param fileName
	 *            the file name
	 * @param packageName
	 *            the package name
	 */
	public ElemID(String methodName, String className, String fileName, String packageName,
			IDType idt, MetricType metricType) {
		this.methodName = methodName;
		this.className = className;
		this.fileName = fileName;
		this.packageName = packageName;
		this.idType = idt;
		this.metricType = metricType;
	}

	public MetricType getMetricType() {
		return metricType;
	}

	public void setMetricType(MetricType metricType) {
		this.metricType = metricType;
	}

	public IDType getIDType() {
		return idType;
	}
	
	public void setIDType(IDType idType) {
		this.idType = idType;
	}

	


	/**
	 * Gets the method name.
	 * 
	 * @return the method name
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Sets the method name.
	 * 
	 * @param methodName
	 *            the new method name
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the class name.
	 * 
	 * @param className
	 *            the new class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the package name.
	 * 
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 * 
	 * @param packageName
	 *            the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(packageName);
		switch (idType) {
		case FILE:
			sb.append(fileToString());
			break;
		case CLASS:
			sb.append(classToString());
			break;
		case METHOD:
			sb.append(methodToString());
			break;
		}

		return sb.toString();
	}

	private String fileToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-");
		sb.append(fileName);

		return sb.toString();
	}

	private String classToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(fileToString());
		sb.append("-");
		sb.append(className);

		return sb.toString();
	}

	private String methodToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(classToString());
		sb.append("-");
		sb.append(methodName);

		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		metricType = MetricType.valueOf(in.readUTF());
		idType = IDType.valueOf(in.readUTF());
		packageName = in.readUTF();
		if (idType.equals(IDType.FILE)) {
			fileName = in.readUTF();
		} else if (idType.equals(IDType.CLASS)) {
			fileName = in.readUTF();
			className = in.readUTF();
		} else if (idType.equals(IDType.METHOD)) {
			fileName = in.readUTF();
			className = in.readUTF();
			methodName = in.readUTF();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(metricType.toString());
		out.writeUTF(idType.toString());
		out.writeUTF(packageName);
		if (idType.equals(IDType.FILE)) {
			out.writeUTF(fileName);
		} else if (idType.equals(IDType.CLASS)) {
			out.writeUTF(fileName);
			out.writeUTF(className);
		} else if (idType.equals(IDType.METHOD)) {
			out.writeUTF(fileName);
			out.writeUTF(className);
			out.writeUTF(methodName);
		}
	}

	@Override
	public int hashCode() {
		final int prime = Constants.HASH_CODE_PRIME;
		int result = 1;
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((metricType == null) ? 0 : metricType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElemID other = (ElemID) obj;
		if (idType == null) {
			if (other.idType != null)
				return false;
		} else if (!idType.equals(other.idType))
			return false;

		if (!metricType.equals(other.metricType))
			return false;
		
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;

		if (idType == IDType.FILE) {
			return fileEquals(other);
		}

		if (idType == IDType.CLASS) {
			return classEquals(other);
		}

		if (idType == IDType.METHOD) {
			return methodEquals(other);
		}
		
		

		return true;
	}

	private boolean fileEquals(ElemID other) {
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName)) {
			return false;
		}
		return true;
	}

	private boolean classEquals(ElemID other) {
		if (fileEquals(other)) {
			if (className == null) {
				if (other.className != null)
					return false;
			} else if (!className.equals(other.className))
				return false;

			return true;
		} else {
			return false;
		}
	}

	private boolean methodEquals(ElemID other) {
		if (classEquals(other)) {
			if (methodName == null) {
				if (other.methodName != null)
					return false;
			} else if (!methodName.equals(other.methodName))
				return false;

			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ElemID o) {
		int cmpTyp = this.idType.compareTo(o.getIDType());
		if (cmpTyp != 0)
			return cmpTyp;

		int cmpMet = this.metricType.compareTo(o.getMetricType());
		if (cmpMet != 0)
			return cmpMet;
		
		int cmpPkg = this.packageName.compareTo(o.getPackageName());
		if (cmpPkg != 0)
			return cmpPkg;

		int cmpFile = this.fileName.compareTo(o.getFileName());
		if (cmpFile != 0)
			return cmpFile;

		int cmpClass = this.className.compareTo(o.getClassName());
		if (cmpClass != 0)
			return cmpClass;

		int cmpMetd = this.methodName.compareTo(o.getMethodName());
		if (cmpMetd != 0)
			return cmpMetd;
		return 0;
	}

}
