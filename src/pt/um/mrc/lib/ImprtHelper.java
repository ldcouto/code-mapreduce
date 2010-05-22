package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImprtHelper
{

    protected ImprtHelper()
    {}

    public static ArrayList<String> findImportedPackages(String text)
    {
        ArrayList<String> importedPackages = new ArrayList<String>();

        // Import RegEx
        Matcher m = Patterns.IMPORT_PATTERN.matcher(text);

        // Get the package name
        while (m.find())
        {
            // Store the matched key and remove all the ';' and 'static' keyword
            String matchedKey = m.group().replaceAll(";", "");
            matchedKey = matchedKey.replaceAll("static ", "");

            // Split the matched key by whitespaces
            String[] tmp = matchedKey.split("\\s");

            // Set the output value to the imported package
            importedPackages.add(tmp[1]);
        }

        return importedPackages;
    }

    public static boolean importMatcher(String packageToCheck, List<String> importedPackages)
    {

        Pattern mulImport = Pattern.compile("(\\.\\*)$");

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
}
