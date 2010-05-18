package pt.um.mrc.util.io;

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

import pt.um.mrc.util.datatypes.MethodID;

public class JMethodRecordReaderTest
{

    JMethodRecordReader javaRRGoodFile;
    JMethodRecordReader javaRREmptyFile;
    JMethodRecordReader javaRREmptyFile1Char;
    FileSplit fileSplitGood;
    FileSplit fileSplitEmpty;
    FileSplit fileSplitEmpty1Char;
    MockMapContextWrapper<Text, Text, Text, Text> mockContext;
    MockMapContext mapMockContext;
    TaskAttemptContext tac;
    List<Pair<Text, Text>> inputs;
    Counters counters;

    @Before
    public void setUp() throws Exception
    {
        fileSplitGood = new FileSplit(new Path("somefile"), 0, 149, null);
        fileSplitEmpty1Char = new FileSplit(new Path("somefile_empty"), 0, 1, null);
        fileSplitEmpty = new FileSplit(new Path("somefile_empty"), 0, 0, null);
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

        javaRRGoodFile = new JMethodRecordReader();
        javaRRGoodFile.initialize(fileSplitGood, tac);

        javaRREmptyFile1Char = new JMethodRecordReader();
        javaRREmptyFile1Char.initialize(fileSplitEmpty1Char, tac);

        javaRREmptyFile = new JMethodRecordReader();
        javaRREmptyFile.initialize(fileSplitEmpty, tac);

    }

    @Test
    public void testInitializeInputSplitTaskAttemptContext() throws IOException,
            InterruptedException
    {

        JMethodRecordReader jrrToTest = new JMethodRecordReader();
        jrrToTest.initialize(fileSplitEmpty, tac);

        Map<MethodID, Text> expectedMethods = new HashMap<MethodID, Text>();
        String expectedPackageName = null;
        String expectedFileName = null;
        CompilationUnit expectedCU = new CompilationUnit();
        FileSplit expectedSplit = fileSplitEmpty;
        List<MethodID> expectedMKeys = null;
        int expectedCurrM = -1;

        Configuration auxJob = tac.getConfiguration();
        Path auxPath = new Path("somefile_empty");
        FileSystem auxFS = auxPath.getFileSystem(auxJob);

        FSDataInputStream expectedFileIn = auxFS.open(auxPath);

        FSDataInputStream actualFileIn = jrrToTest.getFileIn();
        Map<MethodID, Text> actualMethods = jrrToTest.getMethods();
        String actualPackageName = jrrToTest.getPackageName();
        String actualFileName = jrrToTest.getFileName();
        CompilationUnit actualCU = jrrToTest.getCu();
        FileSplit actualSplit = jrrToTest.getfSplit();
        List<MethodID> actualMKeys = jrrToTest.getmKeys();
        int actualCurrM = jrrToTest.getCurrM();

        // FIXME can't seem to text the InputStreams
        // Assert.assertSame(expectedFileIn, actualFileIn);
        Assert.assertEquals(expectedMethods, actualMethods);
        Assert.assertEquals(expectedPackageName, actualPackageName);
        Assert.assertEquals(expectedFileName, actualFileName);
        Assert.assertEquals(expectedCU, actualCU);
        Assert.assertEquals(expectedSplit, actualSplit);
        Assert.assertEquals(expectedMKeys, actualMKeys);
        Assert.assertEquals(expectedCurrM, actualCurrM);

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
        boolean actual = javaRREmptyFile1Char.nextKeyValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public final void testGetCurrentKey() throws IOException, InterruptedException
    {
        javaRRGoodFile.nextKeyValue();
        MethodID expected = new MethodID("map[Text key, Text value, Context context]",
                "FindPackagesMapper", "somefile", "pt.um.mrc.jobs.imprt");
        MethodID actual = javaRRGoodFile.getCurrentKey();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public final void testGetCurrentValue() throws IOException, InterruptedException
    {
        javaRRGoodFile.nextKeyValue();

        StringBuilder sb = new StringBuilder();
        sb.append("@Override");

        sb
                .append("protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {");
        sb.append("packge.set(PckgHelper.findPackage(value.toString()));");
        sb.append("context.write(packge, new Text(\"\"));");
        sb.append("}");

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
        float expected = 0.5f;
        javaRRGoodFile.nextKeyValue();
        float actual = javaRRGoodFile.getProgress();
        Assert.assertEquals(expected, actual, 0.001);

    }

    @Test
    public final void testClose() throws InterruptedException, IOException
    {
        javaRRGoodFile.close();

        FSDataInputStream expectedFileIn = null;
        int expectedCurrM = Integer.MAX_VALUE;
        Map<MethodID, Text> expectedMethods = null;
        List<MethodID> expectedKeys = null;

        FSDataInputStream actualFileIn = javaRRGoodFile.getFileIn();
        int actualCurrM = javaRRGoodFile.getCurrM();
        Map<MethodID, Text> actualMethods = javaRRGoodFile.getMethods();
        List<MethodID> actualKeys = javaRRGoodFile.getmKeys();

        // TODO Still can't compare the filesystems!
        // Assert.assertEquals(expectedFileIn, actualFileIn);
        Assert.assertEquals(expectedCurrM, actualCurrM);
        Assert.assertEquals(expectedMethods, actualMethods);
        Assert.assertEquals(expectedKeys, actualKeys);
    }

}
