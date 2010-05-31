package pt.um.mrc.util.datatypes;

import org.apache.hadoop.io.WritableComparable;

/**
 * The Class FileID represents an Identifier of a File. It's composed by the
 * file name and the package name.
 */
public class FileID extends AbsID implements WritableComparable<FileID>
{

    public FileID()
    {
        super();
    }

    public FileID(String fileName, String packageName)
    {
        super(fileName, packageName);
    }

    @Override
    public int compareTo(FileID o)
    {
        return super.compareTo(o);
    }
    
}
