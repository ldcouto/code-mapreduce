package pt.um.mrc.util.io;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

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
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper.MockMapContext;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.ClassID;

public class JClassRecordReaderTest
{
    JClassRecordReader javaRRGoodFile = new JClassRecordReader();
    JClassRecordReader javaRREmptyFile = new JClassRecordReader();
    JClassRecordReader javaRRClassFile = new JClassRecordReader();
    FileSplit fileSplitGood;
    FileSplit fileSplitEmpty;
    FileSplit fileSplitClass;
    MockMapContextWrapper<Text, Text, Text, Text> mockContext;
    MockMapContext mapMockContext;
    TaskAttemptContext tac;
    List<Pair<Text, Text>> inputs;
    Counters counters;

    @Before
    public void setUp() throws Exception
    {
        fileSplitGood = new FileSplit(new Path("TestMats/somefile"), 0, 149, null);
        fileSplitEmpty = new FileSplit(new Path("TestMats/somefile_empty"), 0, 0, null);
        fileSplitClass = new FileSplit(new Path("TestMats/somefile_class"), 0, 15, null);
        Text foo = new Text("foo");
        Text bar = new Text("bar");
        Pair<Text, Text> p1 = new Pair<Text, Text>(foo, bar);
        inputs = new ArrayList<Pair<Text, Text>>();
        inputs.add(p1);

        counters = new Counters();
        mockContext = new MockMapContextWrapper<Text, Text, Text, Text>();

        mapMockContext = mockContext.getMockContext(inputs, counters);

        tac = new TaskAttemptContext(mapMockContext.getConfiguration(), mapMockContext
                .getTaskAttemptID());

        javaRRGoodFile = new JClassRecordReader();
        javaRRGoodFile.initialize(fileSplitGood, tac);

        javaRREmptyFile = new JClassRecordReader();
        javaRREmptyFile.initialize(fileSplitEmpty, tac);
    }

    @Test
    public final void testConstructor(){
        JClassRecordReader obj = new JClassRecordReader();
        
        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof JClassRecordReader);
    }
    
    @Test
    public final void testInitialize() throws Exception
    {
        JClassRecordReader jrrToTest = new JClassRecordReader();
        jrrToTest.initialize(fileSplitClass, tac);

        Map<ClassID, Text> expectedMethods = new HashMap<ClassID, Text>();
        expectedMethods.put(new ClassID("Potato","somefile_class","<default>"), new Text(""));
        String expectedPackageName = "<default>";
        String expectedFileName = "somefile_class";
        FileSplit expectedSplit = fileSplitClass;
        List<ClassID> expectedMKeys = new ArrayList<ClassID>(expectedMethods.keySet());
        int expectedCurrM = -1;

        Configuration auxJob = tac.getConfiguration();
        Path auxPath = new Path("TestMats/somefile_class");
        FileSystem auxFS = auxPath.getFileSystem(auxJob);
        FSDataInputStream expectedFileIn = auxFS.open(auxPath);
        CompilationUnit expectedCU = JavaParser.parse(expectedFileIn);

        FSDataInputStream actualFileIn = jrrToTest.getFileIn();
        Map<ClassID, Text> actualMethods = jrrToTest.getMethods();
        String actualPackageName = jrrToTest.getPackageName();
        String actualFileName = jrrToTest.getFileName();
        CompilationUnit actualCU = jrrToTest.getCu();
        FileSplit actualSplit = jrrToTest.getfSplit();
        List<ClassID> actualMKeys = jrrToTest.getmKeys();
        int actualCurrM = jrrToTest.getCurrM();

        Assert.assertEquals(expectedMethods, actualMethods);
        Assert.assertEquals(expectedPackageName, actualPackageName);
        Assert.assertEquals(expectedFileName, actualFileName);
        Assert.assertEquals(expectedCU, actualCU);
        Assert.assertEquals(expectedSplit, actualSplit);
        Assert.assertEquals(expectedMKeys, actualMKeys);
        Assert.assertEquals(expectedCurrM, actualCurrM);

        // Compare the InputStreams
        long expectedFileInPosition = expectedFileIn.getPos();
        long actualFileInPosition = actualFileIn.getPos();

        Assert.assertEquals(expectedFileInPosition, actualFileInPosition);
    }

    @Test
    public final void testNextKeyValue_NormalFile() throws IOException, InterruptedException
    {
        boolean expected = true;
        boolean actual = javaRRGoodFile.nextKeyValue();
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public final void testNextKeyValue_EmptyFile() throws IOException, InterruptedException
    {
        boolean expected = false;
        boolean actual = javaRREmptyFile.nextKeyValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public final void testGetCurrentKey() throws IOException, InterruptedException
    {
        javaRRGoodFile.nextKeyValue();
        ClassID expected = new ClassID("ASTParser", "somefile", "japa.parser");
        ClassID actual = javaRRGoodFile.getCurrentKey();
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public final void testGetCurrentValue() throws IOException, InterruptedException
    {
        javaRRGoodFile.nextKeyValue();

        StringBuilder sb = new StringBuilder();

        // TODO: Must rebuild the expected string
        sb.append("private boolean jj_3R_173() {\n");
        sb.append("    Token xsp;\n");
        sb.append("    xsp = jj_scanpos;\n");
        sb.append("    if (jj_3_6()) {\n");
        sb.append("       jj_scanpos = xsp;\n");
        sb.append("       if (jj_3R_200()) {\n");
        sb.append("           jj_scanpos = xsp;\n");
        sb.append("           if (jj_3R_201()) {\n");
        sb.append("               return true;\n");
        sb.append("           }\n");
        sb.append("       }\n");
        sb.append("    }\n");
        sb.append("    return false;\n");
        sb.append("}\n");

        String expected = sb.toString().replaceAll("\\s", "");

        Text aux = javaRRGoodFile.getCurrentValue();
        String actual = aux.toString().replaceAll("\\s", "");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public final void testGetProgress_Start() throws IOException, InterruptedException
    {
        float expected = 0f;
        float actual = javaRREmptyFile.getProgress();
        Assert.assertEquals(expected, actual, 0.001);
    }
    
    @Test
    public final void testGetProgress_Underway() throws IOException, InterruptedException
    {
        float expected = 1.0f;
        javaRRGoodFile.nextKeyValue();
        float actual = javaRRGoodFile.getProgress();
        Assert.assertEquals(expected, actual, 0.001);

    }

    @Test
    public final void testClose() throws InterruptedException, IOException, Exception
    {
        javaRRGoodFile.close();

        int expectedCurrM = -1;
        Map<ClassID, Text> expectedMethods = null;
        List<ClassID> expectedKeys = null;
        
        Configuration auxJob = tac.getConfiguration();
        Path auxPath = new Path("TestMats/somefile");
        FileSystem auxFS = auxPath.getFileSystem(auxJob);
        FSDataInputStream expectedFileIn = auxFS.open(auxPath);
        JavaParser.parse(expectedFileIn);
        
        FSDataInputStream actualFileIn = javaRRGoodFile.getFileIn();
        int actualCurrM = javaRRGoodFile.getCurrM();
        Map<ClassID, Text> actualMethods = javaRRGoodFile.getMethods();
        List<ClassID> actualKeys = javaRRGoodFile.getmKeys();

        Assert.assertEquals(expectedCurrM, actualCurrM);
        Assert.assertEquals(expectedMethods, actualMethods);
        Assert.assertEquals(expectedKeys, actualKeys);
        
        // Compare the InputStreams
        long expectedPos = expectedFileIn.getPos();
        long actualPos = actualFileIn.getPos();
        
        Assert.assertEquals(expectedPos, actualPos);
    }
}
