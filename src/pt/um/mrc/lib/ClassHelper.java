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

        Matcher classMatcher = RegexConstants.CLASS_HEADER_PATTERN.matcher(text);

        while (classMatcher.find())
        {
            String classHeader = new String(classMatcher.group().replaceAll(RegexConstants.CLASS_HEADER_TERMINATOR, ""));
            if (classHeader.indexOf(RegexConstants.EXTENDS_LITERAL) >= 0)
            {
                String[] classTmp = classHeader.split(RegexConstants.EXTENDS_LITERAL);

                classHeader = classTmp[0].trim();
            }

            if (classHeader.indexOf(RegexConstants.IMPLEMENTS_LITERAL) >= 0)
            {
                String[] classTmp = classHeader.split(RegexConstants.IMPLEMENTS_LITERAL);

                classHeader = classTmp[0].trim();
            }

            String[] classNameAux = classHeader.split(RegexConstants.CLASS_LITERAL);
            classHeader = classNameAux[1].trim();

            r.add(classHeader);
        }

        return r;
    }

    public static ArrayList<Pair<String, String>> findClassAndSuperClass(String text)
    {
        ArrayList<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();

        Matcher classMatcher = RegexConstants.CLASS_HEADER_PATTERN.matcher(text);

        while (classMatcher.find())
        {
            String classHeader = classMatcher.group().replaceAll(RegexConstants.CLASS_HEADER_TERMINATOR, "");
            String[] classTmp = classHeader.split(RegexConstants.EXTENDS_LITERAL);

            String[] classNameAux = classTmp[0].split(RegexConstants.CLASS_LITERAL);
            String className = classNameAux[1].trim();

            if (classTmp.length > 1)
            {
                String[] superClassAux = classTmp[1].split(RegexConstants.IMPLEMENTS_LITERAL);
                String superClass = superClassAux[0].trim();
                pairs.add(new PairImpl<String, String>(className, superClass));
            }
        }

        return pairs;
    }
}
