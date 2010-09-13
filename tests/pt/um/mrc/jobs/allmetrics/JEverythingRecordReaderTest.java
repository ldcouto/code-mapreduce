package pt.um.mrc.jobs.allmetrics;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import pt.um.mrc.util.datatypes.IDType;

public class JEverythingRecordReaderTest {

    JEverythingRecordReader javaRRGoodFile = new JEverythingRecordReader();
    JEverythingRecordReader javaRREmptyFile = new JEverythingRecordReader();
    JEverythingRecordReader javaRRClassFile = new JEverythingRecordReader();
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

        javaRRGoodFile = new JEverythingRecordReader();
        javaRRGoodFile.initialize(fileSplitGood, tac);

        javaRREmptyFile = new JEverythingRecordReader();
        javaRREmptyFile.initialize(fileSplitEmpty, tac);
    }

    @Test
    public void testInitializeInputSplitTaskAttemptContext() throws Exception
    {

    	JEverythingRecordReader jrrToTest = new JEverythingRecordReader();
        jrrToTest.initialize(fileSplitClass, tac);

        Map<ElemID, Text> expectedMethods = new HashMap<ElemID, Text>();
        ElemID id = new ElemID("Potato[ ]", "Potato", "somefile_class", "<default>", IDType.METHOD, null);

        StringBuilder sb = new StringBuilder();
        sb.append("public Potato() {\n");
        sb.append("}");
        
        expectedMethods.put(id, new Text(sb.toString()));
        
        String expectedPackageName = "<default>";
        String expectedFileName = "somefile_class";
        FileSplit expectedSplit = fileSplitClass;
        
        List<ElemID> expectedMKeys = new ArrayList<ElemID>(Arrays.asList(id));
        int expectedCurrM = -1;

        Configuration auxJob = tac.getConfiguration();
        Path auxPath = new Path("TestMats/somefile_class");
        FileSystem auxFS = auxPath.getFileSystem(auxJob);
        FSDataInputStream expectedFileIn = auxFS.open(auxPath);
        CompilationUnit expectedCU = JavaParser.parse(expectedFileIn);

        FSDataInputStream actualFileIn = jrrToTest.getFileIn();
        Map<ElemID, Text> actualMethods = jrrToTest.getElems();
        String actualPackageName = jrrToTest.getPackageName();
        String actualFileName = jrrToTest.getFileName();
        CompilationUnit actualCU = jrrToTest.getCu();
        FileSplit actualSplit = jrrToTest.getfSplit();
        List<ElemID> actualMKeys = jrrToTest.getmKeys();
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
        ElemID expected = new ElemID("jj_3R_49[ ]", "ASTParser", "somefile", "japa.parser", IDType.METHOD, null);
        ElemID actual = javaRRGoodFile.getCurrentKey();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public final void testGetCurrentValue() throws IOException, InterruptedException
    {
        javaRRGoodFile.nextKeyValue();

        StringBuilder sb = new StringBuilder();

        sb.append("private boolean jj_3R_49(){\n");
        sb.append("    if(jj_scan_token (PUBLIC)){\n");
        sb.append("        return true;\n");
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
        float expected = 0.0017793594161048532f;
        javaRRGoodFile.nextKeyValue();
        float actual = javaRRGoodFile.getProgress();
        Assert.assertEquals(expected, actual, 0.001);

    }

    @Test
    public final void testClose() throws InterruptedException, IOException, Exception
    {
        javaRRGoodFile.close();

        int expectedCurrM = -1;
        Map<ElemID, Text> expectedMethods = null;
        List<ElemID> expectedKeys = null;
        
        Configuration auxJob = tac.getConfiguration();
        Path auxPath = new Path("TestMats/somefile");
        FileSystem auxFS = auxPath.getFileSystem(auxJob);
        FSDataInputStream expectedFileIn = auxFS.open(auxPath);
        JavaParser.parse(expectedFileIn);
        
        FSDataInputStream actualFileIn = javaRRGoodFile.getFileIn();
        int actualCurrM = javaRRGoodFile.getCurrM();
        Map<ElemID, Text> actualMethods = javaRRGoodFile.getElems();
        List<ElemID> actualKeys = javaRRGoodFile.getmKeys();

        Assert.assertEquals(expectedCurrM, actualCurrM);
        Assert.assertEquals(expectedMethods, actualMethods);
        Assert.assertEquals(expectedKeys, actualKeys);
        
        // Compare the InputStreams
        long expectedPos = expectedFileIn.getPos();
        long actualPos = actualFileIn.getPos();
        
        Assert.assertEquals(expectedPos, actualPos);
    }
}
