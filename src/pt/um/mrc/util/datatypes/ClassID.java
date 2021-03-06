package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import pt.um.mrc.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * ClassID represents an Identifier of a Class. It's composed by the class name,
 * the file name and the package name. It extends {@link AbsID}.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
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
        super(fileName, packageName);
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

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.AbsID#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = Constants.HASH_CODE_PRIME;
        int result = super.hashCode();
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.um.mrc.util.datatypes.AbsID#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        ClassID other = (ClassID) obj;
        boolean areEquals = true;
        areEquals &= (className == null ? className == other.getClassName() : className.equals(other.getClassName()));
        return areEquals;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ClassID o)
    {
        int cmpSuper = super.compareTo(o);
        if (cmpSuper !=0)
            return cmpSuper;
        
        int cmpClass = this.className.compareTo(o.getClassName());
        if (cmpClass != 0)
            return cmpClass;

        return 0;
    }

}
