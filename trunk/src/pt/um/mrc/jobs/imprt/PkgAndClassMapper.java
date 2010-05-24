package pt.um.mrc.jobs.imprt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.ClassHelper;
import pt.um.mrc.lib.PckgHelper;

public class PkgAndClassMapper extends Mapper<Text, Text, Text, Text> {

	List<String> classes = new ArrayList<String>();
	private Text packge = new Text();

	@Override
	protected void map(Text key, Text value, Context context) throws IOException,
		InterruptedException {
		packge.set(PckgHelper.findPackage(value.toString()));
		classes = ClassHelper.findClasses(value.toString());

		for (String s : classes)
			context.write(packge, new Text(packge.toString()+'.'+s));
	}
}
