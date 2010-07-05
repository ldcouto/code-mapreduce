package pt.um.mrc.util.io.rr;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * A generic {@link RecordReader} that processes Java Files and feeds them to a Mapper.
 * Files are processed and fed in a record-oriented manner. The reader passes
 * information in the form of (Key, Value) pairs.
 * <br><br>
 * The Reader uses a parser to process a JavaFile and construct an internal HashMap of Keys and Values 
 * generically called elements.
 * The key must implement the WritableComparable interface and is one of the
 * parameters of the generic declaration. The Value is always Text.
 *<br><br> 
 *
 * The other parameter of this Reader is the Visitor class that will be used to construct the internal map. 
 * This Visitor must extend @Link{GrabbingVisitor}. 
 * <br><br>
 * This record reader can't be used directly. Rather, it should be extended for more specific purposes.
 *
 * @param <ID> the generic key type
 * @param <V> the Visitor type
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public abstract class JRecordReader<ID extends WritableComparable<ID>, V extends GrabbingVisitor<ID>> extends RecordReader<ID, Text>
{
	private Log LOG = LogFactory.getLog(JRecordReader.class);
	
    protected Map<ID, Text> elems = new HashMap<ID, Text>();
    
    protected String packageName;
    
    protected String fileName;
    
    protected CompilationUnit cu;
    
    protected FileSplit fSplit;
    
    protected FSDataInputStream fileIn;
    
    protected List<ID> keys;
    
    protected int curr = -1;
    
    protected V visitor;

    /**
     * Gets the elements.
     *
     * @return the methods
     */
    public Map<ID, Text> getElems()
    {
        return elems;
    }

    /**
     * Gets the package name.
     *
     * @return the package name
     */
    protected String getPackageName()
    {
        return packageName;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    protected String getFileName()
    {
        return fileName;
    }

    /**
     * Gets the compilation unit used by the Visitor
     *
     * @return the compilation unit
     */
    protected CompilationUnit getCu()
    {
        return cu;
    }

    /**
     * Gets the file split.
     *
     * @return the file split
     */
    protected FileSplit getfSplit()
    {
        return fSplit;
    }

    /**
     * Gets the file input stream.
     *
     * @return the file input stream
     */
    protected FSDataInputStream getFileIn()
    {
        return fileIn;
    }

    /**
     * Gets the map keys.
     *
     * @return the map keys
     */
    protected List<ID> getmKeys()
    {
        return keys;
    }

    /**
     * Gets the current key.
     *
     * @return the current key
     */
    protected int getCurrK()
    {
        return curr;
    }

    /**
     * Instantiates a new JRecordReader.
     *
     * @param v the visitor to be used by the reader
     */
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
     *
     * @param inSplit the InputSplit to be read
     * @param tac the task attempt context (internal Hadoop parameter)
     * @throws IOException signals that an I/O exception has occurred.
     * @throws InterruptedException signals that an interruption exception has occurred
     */

    @Override
    public void initialize(InputSplit inSplit, TaskAttemptContext tac) throws IOException, InterruptedException
    {
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
        	LOG.fatal("Could no parse " + fSplit.getPath().getName());
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
     * @return true, if successful
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException signals that an interruption exception has occurred
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
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException signals that an interruption exception has occurred
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
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException signals that an interruption exception has occurred
     */
    @Override
    public Text getCurrentValue() throws IOException, InterruptedException
    {
        return elems.get(keys.get(curr));
    }

    /**
     * Computes and returns the progress. Ie, how much of the
     * file has been read and passed, This method is typically used internally
     * by hadoop.
     *
     * @return a progress estimation, defined as (Current-Position /
     * Total-of-Positions)
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws InterruptedException signals that an interruption exception has occurred
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
     * control structures.
     *
     * @throws IOException Signals that an I/O exception has occurred.
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
