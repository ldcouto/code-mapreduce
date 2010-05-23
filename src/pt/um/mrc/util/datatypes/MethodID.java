package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class MethodID implements WritableComparable<MethodID> {

	String methodName;
	String className;
	String fileName;
	String packageName;

	public MethodID() {
		methodName = "";
		className = "";
		fileName = "";
		packageName = "";
	}

	public MethodID(String methodName, String className, String fileName, String packageName) {
		this.methodName = methodName;
		this.className = className;
		this.fileName = fileName;
		this.packageName = packageName;
	}

	public MethodID(String aggre) {
		String[] aux = aggre.split("-");
		packageName = aux[0];
		fileName = aux[1];
		className = aux[2];
		methodName = aux[3];
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String prettyString() {
		return "MethodID[" + "packageName=" + packageName + ", fileName=" + fileName
			+ ", className=" + className + ", methodName=" + methodName + "]";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(packageName);
		sb.append('-');
		sb.append(fileName);
		sb.append('-');
		sb.append(className);
		sb.append('-');
		sb.append(methodName);
		return sb.toString();
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		packageName = in.readUTF();
		fileName = in.readUTF();
		className = in.readUTF();
		methodName = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(packageName);
		out.writeUTF(fileName);
		out.writeUTF(className);
		out.writeUTF(methodName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
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
		MethodID other = (MethodID) obj;
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
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		return true;
	}

	@Override
	public int compareTo(MethodID o) {
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
