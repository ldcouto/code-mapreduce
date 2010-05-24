package pt.um.mrc.util.mappers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import pt.um.mrc.util.datatypes.CollectionWritablePrintable;

public class CachedPackageInfoMapperTest
{

    private CachedPackageInfoMapper<Text, Text, Text, CollectionWritablePrintable> mapper;

    @Before
    public void setUp() throws Exception
    {
        mapper = new CachedPackageInfoMapper<Text, Text, Text, CollectionWritablePrintable>();
    }

    @Test
    public final void testSetup() throws IOException, InterruptedException
    {
        Counters counters = new Counters();
        List<Pair<Text, Text>> inputs = new ArrayList<Pair<Text, Text>>();

        MockMapContextWrapper<Text, Text, Text, Text> mockContext = new MockMapContextWrapper<Text, Text, Text, Text>();
        mapper.setup(mockContext.getMockContext(inputs, counters));

        Map<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();

        Map<String, ArrayList<String>> actual = mapper.getInternalPackages();

        assertEquals(expected, actual);
    }

    @Test
    public void testBuildCache() throws IOException
    {
        Path p = new Path("TestMats/pkgs");
        Path[] parray;
        parray = new Path[1];
        parray[0] = p;

        mapper.buildCache(parray);

        Map<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();
        expected.put("pt.um.mrc.util.reducers", new ArrayList<String>(Arrays.asList(
                "pt.um.mrc.util.reducers.CollectionReducer",
                "pt.um.mrc.util.reducers.IdentityReducer", "pt.um.mrc.util.reducers.ReduceHelpers",
                "pt.um.mrc.util.reducers.SumReducer")));
        expected.put("pt.um.mrc.util.mappers", new ArrayList<String>(Arrays.asList(
                "pt.um.mrc.util.mappers.CachedPackageInfoMapper",
                "pt.um.mrc.util.mappers.LineValuesMapper")));

        Map<String, ArrayList<String>> actual = mapper.getInternalPackages();

        assertEquals(expected, actual);
    }
}
