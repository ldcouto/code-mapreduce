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
public class MethodID extends AbsID implements WritableComparable<MethodID>
{
    /** The method name. */
    String methodName;

    /** The class name. */
    String className;

    /**
     * Instantiates a new MethodID.
     */
    public MethodID()
    {
        super();
        methodName = "";
        className = "";
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
        super(fileName, packageName);
        this.methodName = methodName;
        this.className = className;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
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
        super.readFields(in);
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
        super.write(out);
        out.writeUTF(className);
        out.writeUTF(methodName);
    }

    @Override
    public int hashCode()
    {
        final int prime = Constants.HASH_CODE_PRIME;
        int result = super.hashCode();
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
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
        if (methodName == null)
        {
            if (other.methodName != null)
                return false;
        }
        else if (!methodName.equals(other.methodName))
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
        int cmpClass = this.className.compareTo(o.getClassName());
        if (cmpClass != 0)
            return cmpClass;

        int cmpMetd = this.methodName.compareTo(o.getMethodName());
        if (cmpMetd != 0)
            return cmpMetd;

        return super.compareTo(o);
    }
}
