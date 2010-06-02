package pt.um.mrc.lib;

import java.util.regex.Matcher;

/**
 * A set of useful methods to retrieve the cyclomatic complexity from the given
 * input.
 */
public class McCabeHelper
{

    /**
     * Protected constructor since it is a static only class
     */
    protected McCabeHelper()
    {}

    /**
     * Counts the number of control statements in the the input text and adds
     * one to it+s final result.
     * 
     * @param text
     *            the input text
     * @return the number of control statements found in the given input text
     *         added by one
     */
    public static int countMcCabe(String text)
    {
        int mcCabe = 1;

        Matcher m = RegexConstants.CONTROL_STATEMENT_PATTERN.matcher(removeStrings(text));

        while (m.find())
        {
            mcCabe++;
        }

        return mcCabe;
    }

    private static String removeStrings(String text)
    {
        Matcher m = RegexConstants.STRING_LITERAL.matcher(text);

        return m.replaceAll("");
    }

}
