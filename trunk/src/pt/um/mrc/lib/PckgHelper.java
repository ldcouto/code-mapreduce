package pt.um.mrc.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A set of useful methods to retrieve information from the package declaration.
 */
public class PckgHelper
{

    /**
     * Protected constructor since it is a static only class
     */
    protected PckgHelper()
    {}

    /**
     * Finds the defined package in the input text.
     * 
     * @param text
     *            the input text
     * @return the declared package
     */
    public static String findPackage(String text)
    {
        // Variable to return
        String pckg = "<default>";

        // Package RegEx
        Matcher packageMatcher = RegexConstants.PACKAGE_PATTERN.matcher(text);

        // Get the package name
        if (packageMatcher.find())
        {
            // Store the matched string, deleting the ';'
            String matchedKey = packageMatcher.group().replaceAll(";", "");

            // Split the string by white spaces
            String[] tmp = matchedKey.split(RegexConstants.WHITESPACES_REGEX);

            pckg = tmp[1];
        }

        return pckg;
    }

    /**
     * Extracts the package name from DOTSTAR imports declaration.
     * 
     * @param text
     *            the input text
     * @return the package declaration
     */
    public static String extractPkgNameStar(String text)
    {
        String r = text.replaceAll("(\\.\\*)", "");
        return r;
    }

    /**
     * Extracts the package and class names from a complete import declaration.
     * 
     * @param text
     *            the input text
     * @return a string array containing the package name in the first position
     *         and the complete class name in the second
     */
    public static String[] extractPkgClassNames(String text)
    {
        String[] r;
        r = new String[2];

        Pattern p = Pattern.compile("([a-zA-Z0-9_.]*)\\.");
        Matcher m = p.matcher(text);
        m.find();

        r[1] = text;
        r[0] = m.group(1);

        return r;
    }
}
