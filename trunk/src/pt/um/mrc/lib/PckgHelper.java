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
    

	public static String extractPkgNameStar(String s) {
		String r = s.replaceAll("(\\.\\*)", "");
		return r;
	}

	public static String[] extractPkgClassNames(String s) {
		String[] r;
		r = new String[2];
		String[] aux = s.split("\\.");
		StringBuilder sb= new StringBuilder();
		
		r[1]=aux[aux.length-1]; 
		
		for (int i=0; i<aux.length-1; i++)
		{
			sb.append(aux[i]);
			if (i != aux.length-2)
				sb.append('.');
		}	
		
		r[0] = sb.toString();
		return r;
	}
}
