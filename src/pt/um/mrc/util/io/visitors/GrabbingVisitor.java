package pt.um.mrc.util.io.visitors;

import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * The Class GrabbingVisitor defines a common way to develop Visitors for use with the
 * {@link JRecordReader}.
 *	<br><br>
 *	Grabbing visitors are initialized inside the RecordReader and then ran to construct a 
 *	map of elements to be passed to the Mapper. These visitors all have access to the same map
 *	as the RecordReader while also having knowledge of the package and name of the file they are visiting
 *	so as to properly construct a their keys.
 *
 * @param <ID> the generic type of the key.
 *  
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso 
 * 
 */
public abstract class GrabbingVisitor<ID extends WritableComparable<ID>> extends
		VoidVisitorAdapter<Object> {

	/** The file name. */
	String fileName;
	
	/** The package name. */
	String packageName;
	
	/** The elements map. */
	Map<ID, Text> elems = new HashMap<ID, Text>();

	/**
	 * Instantiates a new grabbing visitor.
	 */
	public GrabbingVisitor() {
		super();
	}

	/**
	 * Initializes the visitor with the name and package of the file being visited and the map being used by the
	 * RecordReader. This cannot be done at instantiation since that is done too early in 
	 * the RecordReader initialization process.
	 *
	 * @param fileName the file name
	 * @param packageName the package name
	 * @param elems the elements map
	 */
	public void init(String fileName, String packageName, Map<ID, Text> elems) {
		this.fileName = fileName;
		this.packageName = packageName;
		this.elems = elems;
	}

}
