package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

// TODO: Auto-generated Javadoc
/**
 * ClassID represents an Identifier of a Class. It's composed by the class name,
 * the file name and the package name.
 */
public class ClassID extends AbsID implements WritableComparable<ClassID>
{
    /** The class name. */
    String className;

    /**
     * Instantiates a new ClassID.
     */
    public ClassID()
    {
        super();
        this.className = "";
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
    public ClassID(String className, String fileName, String packageName)
    {
        super(fileName,packageName);
        this.className = className;
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
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((className == null) ? 0 : className.hashCode());
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
        ClassID other = (ClassID) obj;
        if (className == null)
        {
            if (other.className != null)
                return false;
        }
        else if (!className.equals(other.className))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ClassID o)
    {
        int cmpClass = this.className.compareTo(o.getClassName());
        if (cmpClass != 0)
            return cmpClass;

        return super.compareTo(o);
    }

}
