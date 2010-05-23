package pt.um.mrc.util.io;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

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
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import pt.um.mrc.util.datatypes.MethodID;

/**
 * A RecordReader that processes Java Files and feeds them to a Mapper. Files
 * are processed and fed in a record-oriented manner. The reader passes
 * information in the form of (Key, Value) pairs.
 * 
 * The key is composed of the package, file, class and method name (including
 * parameters) and is represented by a MethodID.
 * 
 * The value is composed of the entire method body (including declaration) and
 * is represented by a Text object.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 * 
 */

public class JMethodRecordReader extends RecordReader<MethodID, Text> {

	private Map<MethodID, Text> methods = new HashMap<MethodID, Text>();
	private String packageName;
	private String fileName;
	private CompilationUnit cu;
	private FileSplit fSplit;
	private FSDataInputStream fileIn;
	private List<MethodID> mKeys;
	private int currM = -1;

	public Map<MethodID, Text> getMethods() {
		return methods;
	}

	protected String getPackageName() {
		return packageName;
	}

	protected String getFileName() {
		return fileName;
	}

	protected CompilationUnit getCu() {
		return cu;
	}

	protected FileSplit getfSplit() {
		return fSplit;
	}

	protected FSDataInputStream getFileIn() {
		return fileIn;
	}

	protected List<MethodID> getmKeys() {
		return mKeys;
	}

	protected int getCurrM() {
		return currM;
	}

	/**
	 * Called at startup. Reads the file, applies the parser and loads the
	 * auxiliary control structures. The parameters are only used internally by
	 * Hadoop so there is no need to worry about them.
	 * 
	 * It should be noted that after initialization the reader does <b>not</b>
	 * have a Key or Value loader. The internal iterator must be moved forward
	 * with the {@link nextKeyValue()} method.
	 */

	@Override
	public void initialize(InputSplit inSplit, TaskAttemptContext tac) throws IOException,
		InterruptedException {

		// currMethodID = new MethodID();
		// currMethod = new Text();
		//		
		Configuration job = tac.getConfiguration();
		fSplit = (FileSplit) inSplit;

		final Path file = fSplit.getPath();

		// Open the file
		FileSystem fs = file.getFileSystem(job);
		fileIn = fs.open(fSplit.getPath());

		try {
			cu = JavaParser.parse(fileIn);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (cu.getBeginLine() > 0) {
			fileName = fSplit.getPath().getName();
			if (cu.getPackage() != null)
				packageName = cu.getPackage().getName().toString();
			else
				packageName = "<default>";
		}

		new ClassVisitor().visit(cu, null);

		if (methods.keySet().size() > 0)
			mKeys = new ArrayList<MethodID>(methods.keySet());
	}

	/**
	 * Checks if there is another pair available and, if so, moves the internal
	 * iterator so it points to that position.
	 * 
	 * @returns True if another pair is available. False, otherwise.
	 */

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if (mKeys != null) {
			if (currM < mKeys.size() - 1) {
				currM++;
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
	public MethodID getCurrentKey() throws IOException, InterruptedException {
		return mKeys.get(currM);
	}

	/**
	 * Returns the value currently being pointed to by the iterator. The reader
	 * does not check if the position is valid. That responsibility lies with
	 * whoever is calling the method.
	 * 
	 * @return The current value
	 */
	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return methods.get(mKeys.get(currM));
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
	public float getProgress() throws IOException, InterruptedException {
		if (currM == -1)
			return 0;
		float aux = (currM + 1) / (float) mKeys.size();
		float r = Math.min(1.0f, aux);
		return r;
	}

	/**
	 * Closes the reader by closing the FileInputStream and resetting auxiliary
	 * structures.
	 */
	@Override
	public void close() throws IOException {
		if (fileIn != null) {
			fileIn.close();
		}
		methods = null;
		mKeys = null;
	}


	private class ClassVisitor extends VoidVisitorAdapter<Object> {

		public void visit(ClassOrInterfaceDeclaration c, Object arg) {
			for (BodyDeclaration td : c.getMembers()) {
				MethodID aux = new MethodID("", c.getName(), fileName, packageName);
				if (td instanceof MethodDeclaration) {
					MethodDeclaration md = (MethodDeclaration) td;
					StringBuilder sb = new StringBuilder();
					sb.append(md.getName());
					if (md.getParameters() != null)
						sb.append(md.getParameters().toString());
					else
						sb.append("[ ]");
					aux.setMethodName(sb.toString());
					methods.put(aux, new Text(td.toString()));
				}
			}
		};
	}

}
