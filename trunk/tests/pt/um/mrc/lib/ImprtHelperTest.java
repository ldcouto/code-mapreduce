package pt.um.mrc.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

public class ImprtHelperTest {

	@Test
	public final void testConstructor() {
		ImprtHelper cls = new ImprtHelper();

		assertNotNull(cls);
	}

	@Test
	public final void testFindImportedPackages_ValidInput01() {
		String input = "import org.junit.Test;\n" + "import java.util.ArrayList;";

		ArrayList<String> expected = new ArrayList<String>();
		expected.add("org.junit.Test");
		expected.add("java.util.ArrayList");

		ArrayList<String> result = ImprtHelper.findImports(input);

		assertEquals(expected, result);
	}

	@Test
	public final void testFindImportedPackages_ValidInput02() {
		String input = "import org.junit.Test;\n" + "import static org.junit.Assert.*;";

		ArrayList<String> expected = new ArrayList<String>();
		expected.add("org.junit.Test");
		expected.add("org.junit.Assert.*");

		ArrayList<String> result = ImprtHelper.findImports(input);

		assertEquals(expected, result);
	}

	@Test
	public final void testFindImportedPackages_InvalidInput02() {
		String input = "class java.util.ArrayList;";

		ArrayList<String> expected = new ArrayList<String>();

		ArrayList<String> result = ImprtHelper.findImports(input);

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

}
