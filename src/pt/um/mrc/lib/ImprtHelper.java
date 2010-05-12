package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.regex.Matcher;

import pt.um.mrc.lib.Patterns;

public class ImprtHelper
{    
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
}
