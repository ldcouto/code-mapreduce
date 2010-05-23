package pt.um.mrc.util.mappers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mrunit.mapreduce.mock.MockMapContextWrapper;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class PackageInfoMapperTest
{
    private PackageInfoMapper<Text, Text, Text, Text> mapper;
//    private MapDriver<Text, Text, Text, Text> driver;

    @Before
    public void setUp() throws Exception
    {
        mapper = new PackageInfoMapper<Text, Text, Text, Text>();
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
}
