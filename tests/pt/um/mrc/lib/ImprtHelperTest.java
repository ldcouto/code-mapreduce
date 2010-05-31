package pt.um.mrc.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ImprtHelperTest {

	Map<String, ArrayList<String>> internalClassPkgInfo;
	
	@Before
	public void setup(){
		internalClassPkgInfo = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> reds = new ArrayList<String>(Arrays.asList("pt.um.mrc.util.reducers.IdentityReducer", "pt.um.mrc.util.reducers.ReduceHelpers", "pt.um.mrc.util.reducers.SumReducer"));
		internalClassPkgInfo.put("pt.um.mrc.util.reducers", reds);
		
		ArrayList<String> maps = new ArrayList<String>(Arrays.asList("pt.um.mrc.util.mappers.CachedPackageInfoMapper", "pt.um.mrc.util.mappers.LineValuesMapper"));
		internalClassPkgInfo.put("pt.um.mrc.util.mappers", maps);
	}
	
	@Test
	public final void testConstructor() {
		ImprtHelper cls = new ImprtHelper();

		assertNotNull(cls);
	}

	@Test
	public final void testFindImportedPackages_ValidInput01() {
		String input = "import org.junit.Test;\n" + "import java.util.ArrayList;";

		List<String> expected = new ArrayList<String>();
		expected.add("org.junit.Test");
		expected.add("java.util.ArrayList");

		List<String> result = ImprtHelper.findImports(input);

		assertEquals(expected, result);
	}

	@Test
	public final void testFindImportedPackages_ValidInput02() {
		String input = "import org.junit.Test;\n" + "import static org.junit.Assert.*;";

		List<String> expected = new ArrayList<String>();
		expected.add("org.junit.Test");
		expected.add("org.junit.Assert.*");

		List<String> result = ImprtHelper.findImports(input);

		assertEquals(expected, result);
	}

	@Test
	public final void testFindImportedPackages_InvalidInput02() {
		String input = "class java.util.ArrayList;";

		List<String> expected = new ArrayList<String>();

		List<String> result = ImprtHelper.findImports(input);

		assertEquals(expected, result);
	}

	@Test
	public void testImportMatcherAsterisk() {
		String pkg = "foo.bar";
		ArrayList<String> ipkgs = new ArrayList<String>(2);

		ipkgs.add("foo.*");
		ipkgs.add("java.util.*");
		boolean expected = true;

		boolean actual = ImprtHelper.importMatcher(pkg, ipkgs);

		assertEquals(expected, actual);

	}

	@Test
	public void testImportMatcherNoAsterisk() {
		String pkg = "foo.bar.pkg1";
		ArrayList<String> ipkgs = new ArrayList<String>(2);

		ipkgs.add("foo.bar.pkg2");
		ipkgs.add("foo.bar.pkg1");
		ipkgs.add("java.util.*");
		
		boolean expected = true;

		boolean actual = ImprtHelper.importMatcher(pkg, ipkgs);

		assertEquals(expected, actual);

	}
	
	@Test
	public void testImportMatcherNoMatch() {
		String pkg = "foo.bar";
		ArrayList<String> ipkgs = new ArrayList<String>(2);

		ipkgs.add("java.util.*");
		
		boolean expected = false;

		boolean actual = ImprtHelper.importMatcher(pkg, ipkgs);

		assertEquals(expected, actual);

	}
	
	@Test
	public void testCompImportedClasses_Star() {
		String s = "pt.um.mrc.util.mappers.*";
	
		List<String> expected = Arrays.asList("pt.um.mrc.util.mappers.CachedPackageInfoMapper", "pt.um.mrc.util.mappers.LineValuesMapper");
		List<String> actual = ImprtHelper.compImportedClasses(s, internalClassPkgInfo);
		
		assertEquals(expected, actual);	
	}

	@Test
	public void testCompImporteClasses_One() {
		String s = "pt.um.mrc.util.mappers.CachedPackageInfoMapper";
		
		List<String> expected = Arrays.asList("pt.um.mrc.util.mappers.CachedPackageInfoMapper");
		List<String> actual = ImprtHelper.compImportedClasses(s, internalClassPkgInfo);
		
		assertEquals(expected, actual);	
	}
	
}
