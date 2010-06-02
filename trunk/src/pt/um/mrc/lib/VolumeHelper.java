package pt.um.mrc.lib;

import java.util.regex.Matcher;

/**
 * A set of useful methods to retrieve the volume from the given input.
 */
public class VolumeHelper
{

    /**
     * Protected constructor since it is a static only class
     */
    protected VolumeHelper()
    {}

    /**
     * Count lines of code.
     * 
     * @param text
     *            the input text
     * @return the number of lines of code.
     */
    public static int countLinesOfCode(String text)
    {
        int lines = 0;

        Matcher m = RegexConstants.NON_EMPTY_LINE.matcher(text);

        while (m.find())
        {
            lines++;
        }

        return lines;
    }
    
    public static int countLoCUnformatted(String code){
        String s = removeComments(code);
        return countLinesOfCode(s);
    }

  
    private static String removeComments(String input){
        String r = input.replaceAll(RegexConstants.COMMENT_REGEX, "");
        return r;
    }
}
