package pt.um.mrc.util.reducers;

import org.apache.hadoop.mapreduce.Reducer;

/**
 * The Class IdentityReducer.
 * 
 * This class simply outputs the Key and Value pairs as inputed by a Mapper
 * class. It completely ignores the Grouping stage.
 * 
 * @param <KI>
 *            the generic type
 * @param <VI>
 *            the generic type
 * @param <KO>
 *            the generic type
 * @param <VO>
 *            the generic type
 * 
 * @author Tiago Alves Veloso
 * @author Luis Duarte Couto
 */
public class IdentityReducer<KI, VI, KO, VO> extends Reducer<KI, VI, KO, VO>
{}
