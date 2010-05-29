package pt.um.mrc.jobs.imprt;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import pt.um.mrc.lib.PckgHelper;

public interface jnter{
	public void meth1t();
	public void meth1();
}



public class FindPackagesMapper extends Mapper<Text, Text, Text, Text> {

	public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	    THURSDAY, FRIDAY, SATURDAY 
	}

	
	public FindPackagesMapper(){}
	
	private Text packge;
	
	int i=0;
	
	static {
		if (true)
			i=0;
	}
	
	//here is some commentary


	@Override
	protected void map(Text key, Text value, Context context) throws IOException,
			InterruptedException {
		packge.set(PckgHelper.findPackage(value.toString()));

		context.write(packge, new Text(""));
	}


	/**
	 * Here's Javadoc!
	 * @param n
	 * @param arg
	 */
	public void visit(MethodDeclaration n, Object arg) {
		System.out.println(n.toString());
		System.out.println(n.haha());

	}
}
