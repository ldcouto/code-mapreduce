package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


public class MethodID implements Writable {
	
	String methodName;
	String className;
	String fileName;
	String packageName;
	
	public MethodID(){
		methodName="";
		className="";
		fileName="";
		packageName="";
	}
	
	public MethodID(String methodName, String className, String fileName,
		String packageName) {
		super();
		this.methodName = methodName;
		this.className = className;
		this.fileName = fileName;
		this.packageName = packageName;
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

	@Override
	public String toString() {
		return "MethodID[className=" + className + ", fileName=" + fileName
			+ ", methodName=" + methodName + ", packageName=" + packageName
			+ "]";
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		className=in.readUTF();
		fileName=in.readUTF();
		methodName=in.readUTF();
		packageName=in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(className);
		out.writeUTF(fileName);
		out.writeUTF(methodName);
		out.writeUTF(packageName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result =
			prime * result + ((className == null) ? 0 : className.hashCode());
		result =
			prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result =
			prime * result + ((methodName == null) ? 0 : methodName.hashCode());
		result =
			prime * result
				+ ((packageName == null) ? 0 : packageName.hashCode());
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


	
	
}
