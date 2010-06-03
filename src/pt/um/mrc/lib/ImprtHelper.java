package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A set of useful methods to retrieve information from the imports
 * declarations.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
 */
public class ImprtHelper
{

    /**
     * Protected constructor since it is a static only class
     */
    protected ImprtHelper()
    {}

    /**
     * Finds declared imports in the given input text..
     * 
     * @param text
     *            the input text
     * @return the array list of declared imports in the input text.
     */
    public static List<String> findImports(String text)
    {
        ArrayList<String> importedPackages = new ArrayList<String>();

        // Import RegEx
        Matcher m = RegexConstants.IMPORT_PATTERN.matcher(text);

        // Get the package name
        while (m.find())
        {
            // Store the matched key and remove all the ';' and 'static' keyword
            String matchedKey = m.group().replaceAll(";", "");
            matchedKey = matchedKey.replaceAll("static ", "");

            // Split the matched key by whitespaces
            String[] tmp = matchedKey.split(RegexConstants.WHITESPACES_REGEX);

            // Set the output value to the imported package
            importedPackages.add(tmp[1]);
        }

        return importedPackages;
    }

    /**
     * Import matcher.
     * 
     * @param packageToCheck
     *            the package to check
     * @param importedPackages
     *            the imported packages
     * @return true, if successful
     */
    public static boolean importMatcher(String packageToCheck, List<String> importedPackages)
    {

        Pattern mulImport = Pattern.compile(RegexConstants.DOTSTAR_REGEX);

        for (String s : importedPackages)
        {
            Matcher m = mulImport.matcher(s);
            if (m.find())
            {
                String aux = s.replaceAll("\\*", "");
                if (packageToCheck.indexOf(aux) >= 0)
                    return true;
            }

            if (s.indexOf(packageToCheck) >= 0)
                return true;
        }

        return false;
    }

    /**
     * Compute imported classes from the input text.
     * 
     * @param text
     *            the input text
     * @param internalClassPkgInfo
     *            the internal package and class information
     * @return the list of valid imports in the input text
     */
    public static List<String> compImportedClasses(String text, Map<String, ArrayList<String>> internalClassPkgInfo)
    {
        Pattern multImport = Pattern.compile(RegexConstants.DOTSTAR_REGEX);
        Matcher m = multImport.matcher(text);

        if (m.find())
            return compMultImports(text, internalClassPkgInfo);

        return compSinglImport(text, internalClassPkgInfo);
    }

    private static List<String> compMultImports(String s, Map<String, ArrayList<String>> internalClassPkgInfo)
    {

        String packageName = PckgHelper.extractPkgNameStar(s);

        return internalClassPkgInfo.get(packageName);
    }

    private static List<String> compSinglImport(String s, Map<String, ArrayList<String>> internalClassPkgInfo)
    {
        List<String> r = new ArrayList<String>(1);

        String[] pkgAndClass = PckgHelper.extractPkgClassNames(s);

        List<String> classes = internalClassPkgInfo.get(pkgAndClass[0]);

        if (classes != null)
        {
            for (String cls : classes)
                if (cls.equals(pkgAndClass[1]))
                    r.add(cls);
        }

        return r;
    }
}
