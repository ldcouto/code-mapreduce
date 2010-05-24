package pt.um.mrc.util.mappers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class PackageInfoMapperTest
{
    private CachedPackageInfoMapper<Text, Text, Text, Text> mapper;
//    private MapDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new CachedPackageInfoMapper<Text, Text, Text, Text>();
//        driver = new MapDriver<Text, Text, Text, Text>(mapper);
    }

    @Test
    public final void testSetup() throws IOException, InterruptedException
    {
    	//TODO since the cache isn't read there really isn't much to test
    	
    	Counters counters = new Counters();
    	List<Pair<Text,Text>> inputs = new ArrayList<Pair<Text, Text>>();
    	
    	MockMapContextWrapper<Text,Text,Text,Text> mockContext = new MockMapContextWrapper<Text, Text, Text, Text>();
    	mapper.setup(mockContext.getMockContext(inputs, counters));
        
    	ArrayList<String> expected = new ArrayList<String>();
    	
    	ArrayList<String> actual = mapper.getInternalPackages();
    	
    	assertEquals(expected, actual);
    }
    
	@Test
	public void testBuildCache() throws IOException{
		Path p = new Path("TestMats/pkgs");
		Path[] parray;
		parray = new Path[1];
		parray[0] = p;
		
		mapper.buildCache(parray);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("pt.um.mrc.util.reducers");
		expected.add("pt.um.mrc.util.mappers");
		expected.add("pt.um.mrc.util.io");
		expected.add("pt.um.mrc.util.datatypes");
		expected.add("pt.um.mrc.lib");
		expected.add("pt.um.mrc.jobs.volume");
		
		ArrayList<String> actual = mapper.getInternalPackages();

		assertEquals(expected, actual);
	}
}
