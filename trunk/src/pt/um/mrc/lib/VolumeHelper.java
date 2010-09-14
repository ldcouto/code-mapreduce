package pt.um.mrc.lib;


/**
 * A set of useful methods to retrieve the volume from the given input.
 * 
 * @author Luis Duarte Couto
 * @author Tiago Alves Veloso
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
        return text != null ? text.split(RegexConstants.NEW_LINE_SPLIT).length : 0;
    }

    public static int countLoCUnformatted(String code)
    {
        String s = removeComments(code);
        return countLinesOfCode(s);
    }

    private static String removeComments(String input)
    {
        String r = input.replaceAll(RegexConstants.COMMENT_REGEX, "");
        return r;
    }
}
