package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class McCabeByFileMapReduceTest
{

    private Mapper<Text, Text, Text, IntWritable> mapper;
    private Reducer<Text, IntWritable, Text, IntWritable> reducer;
    private MapReduceDriver<Text, Text, Text, IntWritable, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new McCabeByFileMapper();
        reducer = new McCabeByFileReducer();
        driver = new MapReduceDriver<Text, Text, Text, IntWritable, Text, IntWritable>(mapper,
                reducer);
    }

    @Test
    public final void testMapReduce()
    {
        String input = "while (classMatcher.find())\n"
            + "{\n"
            + "        String classHeader = classMatcher.group().replaceAll(\"\\{\",\"\");\n"
            + "        String[] classTmp = classHeader.split(\"extends\");\n"
            + "        String[] classNameAux = classTmp[0].split(\"class\");\n"
            + "        String className = classNameAux[1].trim();\n"
            + "        if (classTmp.length > 1)\n"
            + "        {\n"
            + "                String[] superClassAux = classTmp[1].split(\"implements\");\n"
            + "                String superClass = superClassAux[0].trim();\n"
            + "                pairs.add(new PairImpl<String, String>(className, superClass));\n"
            + "        }\n" 
            + "}\n";
        
        driver.withInput(new Text("file1.java"), new Text(input));
        driver.withOutput(new Text("file1.java"), new IntWritable(2));
        driver.runTest();
    }

}
