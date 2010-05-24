package pt.um.mrc.jobs.imprt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImportsByFileMapperTest
{
    Map<String,ArrayList<String>> packages;
    private ImportsByFileMapper mapper;
    private MapDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
    	
		packages = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> reds = new ArrayList<String>(Arrays.asList("pt.um.mrc.util.reducers.IdentityReducer", "pt.um.mrc.util.reducers.ReduceHelpers", "pt.um.mrc.util.reducers.SumReducer"));
		packages.put("pt.um.mrc.util.reducers", reds);
		
		ArrayList<String> maps = new ArrayList<String>(Arrays.asList("pt.um.mrc.util.mappers.CachedPackageInfoMapper", "pt.um.mrc.util.mappers.LineValuesMapper"));
		packages.put("pt.um.mrc.util.mappers", maps);
	
        mapper = new ImportsByFileMapper();
        driver = new MapDriver<Text, Text, Text, Text>(mapper);
    }

    @Test
    public final void testMap() throws Exception
    {
        StringBuilder sb = new StringBuilder();
        sb.append("import pt.um.mrc.util.mappers.*; \n");
        sb.append("import pt.um.mrc.util.reducers.IdentityReducer;");
        sb.append("import java.util.*");
        driver.withInput(new Text("f1.java"), new Text(sb.toString()));
        mapper.setInternalPackages(packages);

        List<Pair<Text, Text>> actual = new ArrayList<Pair<Text, Text>>();
        List<Pair<Text, Text>> expected = new ArrayList<Pair<Text, Text>>();

        expected.add(new Pair<Text, Text>(new Text("f1.java"), new Text("pt.um.mrc.util.mappers.CachedPackageInfoMapper")));
        expected.add(new Pair<Text, Text>(new Text("f1.java"), new Text("pt.um.mrc.util.mappers.LineValuesMapper")));        
        expected.add(new Pair<Text, Text>(new Text("f1.java"), new Text("pt.um.mrc.util.reducers.IdentityReducer")));
        
        actual = driver.run();

        Assert.assertEquals(expected, actual);
    }
}
