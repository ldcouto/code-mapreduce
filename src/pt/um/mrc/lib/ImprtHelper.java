package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImprtHelper
{
    protected ImprtHelper()
    {}

    public static ArrayList<String> findImports(String text)
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

    public static List<String> compImportedClasses(String s, Map<String, ArrayList<String>> internalClassPkgInfo)
    {

        Pattern multImport = Pattern.compile("(\\.\\*)$");
        Matcher m = multImport.matcher(s);

        if (m.find())
            return compMultImports(s, internalClassPkgInfo);

        return compSinglImport(s, internalClassPkgInfo);
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
