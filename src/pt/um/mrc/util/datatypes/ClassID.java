package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import pt.um.mrc.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * ClassID represents an Identifier of a Class. It's composed by the class name,
 * the file name and the package name.
 */
public class ClassID implements WritableComparable<ClassID> {

	/** The class name. */
	String className;

	/** The file name. */
	String fileName;

	/** The package name. */
	String packageName;

	/**
	 * Instantiates a new ClassID.
	 */
	public ClassID() {
		className = "";
		fileName = "";
		packageName = "";
	}

	/**
	 * Instantiates a new ClassID.
	 * 
	 * @param className
	 *            the class name
	 * @param fileName
	 *            the file name
	 * @param packageName
	 *            the package name
	 */
	public ClassID(String className, String fileName, String packageName) {
		this.className = className;
		this.fileName = fileName;
		this.packageName = packageName;
	}

	/**
	 * Instantiates a new ClassID from a single String name.
	 * 
	 * @param aggre
	 *            the aggregate string name (ex: "package-file-class")
	 */
	public ClassID(String aggre) {
		String[] aux = aggre.split("-");
		packageName = aux[0];
		fileName = aux[1];
		className = aux[2];
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

	/**
	 * Generates a pretty string for this MethodID.
	 * 
	 * @return a pretty string representation of this MethodID
	 */
	public String prettyString() {
		return "MethodID[" + "packageName=" + packageName + ", fileName=" + fileName
				+ ", className=" + className +"]";
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
		sb.append('-');
		sb.append(fileName);
		sb.append('-');
		sb.append(className);
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		packageName = in.readUTF();
		fileName = in.readUTF();
		className = in.readUTF();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(packageName);
		out.writeUTF(fileName);
		out.writeUTF(className);
	}

	@Override
	public int hashCode() {
		final int prime = Constants.HASH_CODE_PRIME;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
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
		ClassID other = (ClassID) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ClassID o) {
		int cmpPkg = this.packageName.compareTo(o.getPackageName());
		if (cmpPkg != 0)
			return cmpPkg;

		int cmpFile = this.fileName.compareTo(o.getFileName());
		if (cmpFile != 0)
			return cmpFile;

		int cmpClass = this.className.compareTo(o.getClassName());
		if (cmpClass != 0)
			return cmpClass;

		return 0;
	}

}
