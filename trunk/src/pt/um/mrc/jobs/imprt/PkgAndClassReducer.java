package pt.um.mrc.jobs.imprt;

import org.apache.hadoop.io.Text;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;
import pt.um.mrc.util.reducers.CollectionReducer;

public class PkgAndClassReducer extends
	CollectionReducer<Text, Text, Text, CollectionWritablePrintable> {

}
