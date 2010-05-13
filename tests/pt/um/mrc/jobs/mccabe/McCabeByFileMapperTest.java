package pt.um.mrc.jobs.mccabe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class McCabeByFileMapperTest
{
    private Mapper<Text, Text, Text, IntWritable> mapper;
    private MapDriver<Text, Text, Text, IntWritable> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new McCabeByFileMapper();
        driver = new MapDriver<Text, Text, Text, IntWritable>(mapper);
    }

    @Test
    public final void testMap()
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
        
        driver.withInput(new Text("file1.java"),new Text(input));
        driver.withOutput(new Text("file1.java"), new IntWritable(1));
        driver.withOutput(new Text("file1.java"), new IntWritable(1));
        driver.runTest();
    }
}
