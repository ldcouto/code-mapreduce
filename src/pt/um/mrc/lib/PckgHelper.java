package pt.um.mrc.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String extractPkgNameStar(String s)
    {
        String r = s.replaceAll("(\\.\\*)", "");
        return r;
    }

    public static String[] extractPkgClassNames(String s)
    {
        String[] r;
        r = new String[2];

        Pattern p = Pattern.compile("([a-zA-Z0-9_.]*)\\.");
        Matcher m = p.matcher(s);
        m.find();

        r[1] = s;
        r[0] = m.group(1);

        return r;
    }
}
