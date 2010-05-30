package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import pt.um.mrc.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class MethodID represents an Identifier of a method. It's composed by the
 * method name, the class name, the file name and the package name.
 */
public class MethodID implements WritableComparable<MethodID>
{
    /** The method name. */
    String methodName;

    /** The class name. */
    String className;

    /** The file name. */
    String fileName;

    /** The package name. */
    String packageName;

    /**
     * Instantiates a new MethodID.
     */
    public MethodID()
    {
        methodName = "";
        className = "";
        fileName = "";
        packageName = "";
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
    public MethodID(String methodName, String className, String fileName, String packageName)
    {
        this.methodName = methodName;
        this.className = className;
        this.fileName = fileName;
        this.packageName = packageName;
    }

    /**
     * Gets the method name.
     * 
     * @return the method name
     */
    public String getMethodName()
    {
        return methodName;
    }

    /**
     * Sets the method name.
     * 
     * @param methodName
     *            the new method name
     */
    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    /**
     * Gets the class name.
     * 
     * @return the class name
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * Sets the class name.
     * 
     * @param className
     *            the new class name
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * Gets the file name.
     * 
     * @return the file name
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Sets the file name.
     * 
     * @param fileName
     *            the new file name
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Gets the package name.
     * 
     * @return the package name
     */
    public String getPackageName()
    {
        return packageName;
    }

    /**
     * Sets the package name.
     * 
     * @param packageName
     *            the new package name
     */
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
     */
    @Override
    public void readFields(DataInput in) throws IOException
    {
        packageName = in.readUTF();
        fileName = in.readUTF();
        className = in.readUTF();
        methodName = in.readUTF();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
     */
    @Override
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(packageName);
        out.writeUTF(fileName);
        out.writeUTF(className);
        out.writeUTF(methodName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = Constants.HASH_CODE_PRIME;
        int result = 1;
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodID other = (MethodID) obj;
        if (className == null)
        {
            if (other.className != null)
                return false;
        }
        else if (!className.equals(other.className))
            return false;
        if (fileName == null)
        {
            if (other.fileName != null)
                return false;
        }
        else if (!fileName.equals(other.fileName))
            return false;
        if (methodName == null)
        {
            if (other.methodName != null)
                return false;
        }
        else if (!methodName.equals(other.methodName))
            return false;
        if (packageName == null)
        {
            if (other.packageName != null)
                return false;
        }
        else if (!packageName.equals(other.packageName))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(MethodID o)
    {
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
