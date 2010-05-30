package pt.um.mrc.lib;

import java.util.regex.Matcher;

public class McCabeHelper
{
    protected McCabeHelper()
    {}

    public static int countMcCabe(String text)
    {
        int mcCabe = 1;

        Matcher m = Patterns.CONTROL_STATEMENT_PATTERN.matcher(text);

        while (m.find())
        {
            mcCabe++;
        }

        return mcCabe;
    }

}
