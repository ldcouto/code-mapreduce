package bridge;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.reducers.IdentityReducer;

/**
 * This class is the Reducer for the {@link ImportsByFile} job. It extends
 * {@link CollectionReducer}, which handles the reduce step for this job.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */

public class ImportsByFileReducer extends IdentityReducer<Text,Text,Text,Text>
{}