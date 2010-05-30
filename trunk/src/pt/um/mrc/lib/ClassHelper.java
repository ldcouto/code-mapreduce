package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.regex.Matcher;

import pt.um.mrc.util.datatypes.Pair;
import pt.um.mrc.util.datatypes.PairImpl;

public class ClassHelper
{
    protected ClassHelper()
    {}

    public static ArrayList<String> findClasses(String text)
    {
        ArrayList<String> r = new ArrayList<String>();

        Matcher classMatcher = Patterns.CLASS_HEADER_PATTERN.matcher(text);

        while (classMatcher.find())
        {
            String classHeader = new String(classMatcher.group().replaceAll("\\{", ""));
            if (classHeader.indexOf("extends") >= 0)
            {
                String[] classTmp = classHeader.split("extends");

                classHeader = classTmp[0].trim();
            }

            if (classHeader.indexOf("implements") >= 0)
            {
                String[] classTmp = classHeader.split("implements");

                classHeader = classTmp[0].trim();
            }

            String[] classNameAux = classHeader.split("class");
            classHeader = classNameAux[1].trim();

            r.add(classHeader);
        }

        return r;
    }

    public static ArrayList<Pair<String, String>> findClassAndSuperClass(String text)
    {
        ArrayList<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();

        Matcher classMatcher = Patterns.CLASS_HEADER_PATTERN.matcher(text);

        while (classMatcher.find())
        {
            String classHeader = classMatcher.group().replaceAll("\\{", "");
            String[] classTmp = classHeader.split("extends");

            String[] classNameAux = classTmp[0].split("class");
            String className = classNameAux[1].trim();

            if (classTmp.length > 1)
            {
                String[] superClassAux = classTmp[1].split("implements");
                String superClass = superClassAux[0].trim();
                pairs.add(new PairImpl<String, String>(className, superClass));
            }
        }

        return pairs;
    }
}
