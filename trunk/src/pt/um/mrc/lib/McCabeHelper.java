package pt.um.mrc.lib;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class McCabeHelper
{
    public static ArrayList<String> findControlStatements(String text)
    {
        ArrayList<String> controlStatements = new ArrayList<String>();
        
        Matcher m = Patterns.CONTROL_STATEMENT_PATTERN.matcher(text);
        
        while (m.find())
        {
            String matchedKey = m.group();
            
            controlStatements.add(matchedKey);
        }
        
        return controlStatements;
    }
}
