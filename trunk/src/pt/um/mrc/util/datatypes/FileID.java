package pt.um.mrc.util.datatypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

import pt.um.mrc.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class FileID represents an Identifier of a File. It's composed by the
 * file name and the package name.
 */
public class FileID implements WritableComparable<FileID>
{
    /** The file name. */
    String fileName;

    /** The package name. */
    String packageName;

    /**
     * Instantiates a new FileID.
     */
    public FileID()
    {
        fileName = "";
        packageName = "";
    }

    /**
     * Instantiates a new FileID.
     * 
     * @param fileName
     *            the file name
     * @param packageName
     *            the package name
     */
    public FileID(String fileName, String packageName)
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
     * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
     */
    @Override
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
    @Override
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(packageName);
        out.writeUTF(fileName);
    }

    @Override
    public int hashCode()
    {
        final int prime = Constants.HASH_CODE_PRIME;
        int result = 1;
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FileID other = (FileID) obj;
        if (fileName == null)
        {
            if (other.fileName != null)
                return false;
        }
        else if (!fileName.equals(other.fileName))
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

    @Override
    public int compareTo(FileID o)
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