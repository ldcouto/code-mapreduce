package pt.um.mrc.lib;

import java.util.regex.Matcher;

public class PckgHelper
{
    protected PckgHelper()
    {}

    public static String findPackage(String text)
    {
        // Variable to return
        String pckg = "default package";

        // Package RegEx
        Matcher packageMatcher = Patterns.PACKAGE_PATTERN.matcher(text);

        // Get the package name
        if (packageMatcher.find())
        {
            // Store the matched string, deleting the ';'
            String matchedKey = packageMatcher.group().replaceAll(";", "");

            // Split the string by white spaces
            String[] tmp = matchedKey.split("\\s");

            pckg = tmp[1];
        }

        return pckg;
    }
}
