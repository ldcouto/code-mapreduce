package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;
import pt.um.mrc.util.reducers.CollectionReducer;

/**
 * This class is the reducer to the auxiliary job {@link PkgAndClassCache}.
 * It extends {@link CollectionReducer} which handles the reduce step.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class PkgAndClassReducer extends CollectionReducer<Text, Text, Text, CollectionWritablePrintable>
{}
