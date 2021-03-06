package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import pt.um.mrc.util.Constants;

/**
 * An abstract ID. This class is inherited by {@link FileID}, {@link ClassID}
 * and {@link MethodID}. It's purpose is to hold common information for each
 * FileID, ClassID and MethodID.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public abstract class AbsID
{
    /** The file name. */
    String fileName;

    /** The package name. */
    String packageName;

    /**
     * Instantiates a new AbsID.
     */
    public AbsID()
    {
        this.fileName = "";
        this.packageName = "";
    }

    /**
     * Instantiates a new AbsID.
     * 
     * @param fileName
     *            the file name
     * @param packageName
     *            the package name
     */
    public AbsID(String fileName, String packageName)
    {
        this.fileName = fileName;
        this.packageName = packageName;
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
        return sb.toString();
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
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
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
        AbsID other = (AbsID) obj;
        boolean areEquals = true;
        areEquals &= (fileName == null ? fileName == other.getFileName() : fileName.equals(other.getFileName()));
        areEquals &= (packageName == null ? packageName == other.getPackageName() : packageName.equals(other.getPackageName()));
        return areEquals;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
     */
    /**
     * Read fields from a data input stram.
     * 
     * @param in
     *            the DataInput stream
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void readFields(DataInput in) throws IOException
    {
        packageName = in.readUTF();
        fileName = in.readUTF();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
     */
    /**
     * Writes fields to a data output stream.
     * 
     * @param out
     *            the DataOutput stream
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(packageName);
        out.writeUTF(fileName);
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * 
     * @param o
     *            the object to be compared.
     * @return the a negative integer, zero, or a positive integer as this
     *         object is less than, equal to, or greater than the specified
     *         object.
     */
    public int compareTo(AbsID o)
    {
        int cmpPkg = this.packageName.compareTo(o.getPackageName());
        if (cmpPkg != 0)
            return cmpPkg;

        int cmpFile = this.fileName.compareTo(o.getFileName());
        if (cmpFile != 0)
            return cmpFile;

        return 0;
    }
}
