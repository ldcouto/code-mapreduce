package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.WritableComparable;

/**
 * The Class FileID represents an Identifier of a File. It's composed by the
 * file name and the package name.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class FileID extends AbsID implements WritableComparable<FileID>
{

    /**
     * Instantiates a new file id.
     */
    public FileID()
    {
        super();
    }

    /**
     * Instantiates a new file id.
     * 
     * @param fileName
     *            the file name
     * @param packageName
     *            the package name
     */
    public FileID(String fileName, String packageName)
    {
        super(fileName, packageName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(FileID o)
    {
        return super.compareTo(o);
    }
}
