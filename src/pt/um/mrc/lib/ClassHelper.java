package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.regex.Matcher;

import pt.um.mrc.lib.Patterns;
import pt.um.mrc.util.datatypes.PairImpl;
import pt.um.mrc.util.datatypes.Pair;

public abstract class ClassHelper
{
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