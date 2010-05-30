package pt.um.mrc.util.io.rr;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import pt.um.mrc.util.Constants;
import pt.um.mrc.util.io.visitors.GrabbingVisitor;

/**
 * A generic RecordReader that processes Java Files and feeds them to a Mapper.
 * Files are processed and fed in a record-oriented manner. The reader passes
 * information in the form of (Key, Value) pairs.
 * 
 * The key must implement the WritableComparable interface and is one of the
 * parameters of the generic declaration. The other parameter is the parser
 * (specifically Visitor) that will be used to generate the values.
 * 
 * This record reader can't be used directly. Rather, it should be extended by
 * other, more specific ones.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */

public abstract class JRecordReader<ID extends WritableComparable<ID>, V extends GrabbingVisitor<ID>> extends RecordReader<ID, Text>
{
    protected Map<ID, Text> elems = new HashMap<ID, Text>();
    protected String packageName;
    protected String fileName;
    protected CompilationUnit cu;
    protected FileSplit fSplit;
    protected FSDataInputStream fileIn;
    protected List<ID> keys;
    protected int curr = -1;
    protected V visitor;

    public Map<ID, Text> getMethods()
    {
        return elems;
    }

    protected String getPackageName()
    {
        return packageName;
    }

    protected String getFileName()
    {
        return fileName;
    }

    protected CompilationUnit getCu()
    {
        return cu;
    }

    protected FileSplit getfSplit()
    {
        return fSplit;
    }

    protected FSDataInputStream getFileIn()
    {
        return fileIn;
    }

    protected List<ID> getmKeys()
    {
        return keys;
    }

    protected int getCurrM()
    {
        return curr;
    }

    public JRecordReader(V v)
    {
        visitor = v;
    }

    /**
     * Called at startup. Reads the file, applies the parser and loads the
     * auxiliary control structures. The parameters are only used internally by
     * Hadoop so there is no need to worry about them.
     * 
     * It should be noted that after initialization the reader does <b>not</b>
     * have a Key or Value loaded. The internal iterator must be moved forward
     * with the {@link nextKeyValue()} method.
     */

    @Override
    public void initialize(InputSplit inSplit, TaskAttemptContext tac) throws IOException, InterruptedException
    {

        // currMethodID = new MethodID();
        // currMethod = new Text();
        //		
        Configuration job = tac.getConfiguration();
        fSplit = (FileSplit) inSplit;

        final Path file = fSplit.getPath();

        // Open the file
        FileSystem fs = file.getFileSystem(job);
        fileIn = fs.open(fSplit.getPath());

        try
        {
            cu = JavaParser.parse(fileIn);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        if (cu.getBeginLine() > 0)
        {
            fileName = fSplit.getPath().getName();
            if (cu.getPackage() != null)
                packageName = cu.getPackage().getName().toString();
            else
                packageName = "<default>";
        }

        visitor.init(fileName, packageName, elems);
        visitor.visit(cu, null);

        if (elems.keySet().size() > 0)
            keys = new ArrayList<ID>(elems.keySet());
    }

    /**
     * Checks if there is another pair available and, if so, moves the internal
     * iterator so it points to that position.
     * 
     * @returns True if another pair is available. False, otherwise.
     */

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException
    {
        if (keys != null)
        {
            if (curr < keys.size() - 1)
            {
                curr++;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the key currently being pointed to by the iterator. The reader
     * does not check if the position is valid. That responsibility lies with
     * whoever is calling the method.
     * 
     * @return The current key.
     */
    @Override
    public ID getCurrentKey() throws IOException, InterruptedException
    {
        return keys.get(curr);
    }

    /**
     * Returns the value currently being pointed to by the iterator. The reader
     * does not check if the position is valid. That responsibility lies with
     * whoever is calling the method.
     * 
     * @return The current value
     */
    @Override
    public Text getCurrentValue() throws IOException, InterruptedException
    {
        return elems.get(keys.get(curr));
    }

    /**
     * Computes and returns an estimate of the progress. Ie, how much of the
     * file has been read and passed, This method is typically used internally
     * by hadoop.
     * 
     * @return a progress estimation, defined as (Current-Position /
     *         Total-of-Positions)
     */
    @Override
    public float getProgress() throws IOException, InterruptedException
    {
        if (curr == -1)
            return 0;
        float aux = (curr + 1) / (float) keys.size();
        float r = Math.min(Constants.HUNDRED_PERCENT, aux);
        return r;
    }

    /**
     * Closes the reader by closing the FileInputStream and resetting auxiliary
     * structures.
     */
    @Override
    public void close() throws IOException
    {
        if (fileIn != null)
        {
            fileIn.close();
        }
        elems = null;
        keys = null;
    }

}
