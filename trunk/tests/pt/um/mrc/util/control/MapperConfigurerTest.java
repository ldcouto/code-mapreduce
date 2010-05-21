package pt.um.mrc.util.control;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.Text;
import org.junit.Test;

import pt.um.mrc.jobs.pckg.PackageByFileMapper;

public class MapperConfigurerTest
{
    @Test
    public final void testMapperConfigurer()
    {
        Class<PackageByFileMapper> expectedMapperClass = PackageByFileMapper.class;
        Class<Text> expectedMapOutKey = Text.class;
        Class<Text> expectedMapOutValue = Text.class;
        
        MapperConfigurer mc = new MapperConfigurer(PackageByFileMapper.class, Text.class, Text.class);
        
        assertEquals(expectedMapperClass, mc.getMapperClass());
        assertEquals(expectedMapOutKey, mc.getMapOutKey());
        assertEquals(expectedMapOutValue, mc.getMapOutValue());
    }

}
