package pt.um.mrc.lib;

import java.util.regex.Matcher;


public class VolumeHelper
{
    protected VolumeHelper()
    {}

    public static int countLinesOfCode(String code)
    {
        int lines = 0;
        
        Matcher m = RegexConstants.NON_EMPTY_LINE.matcher(code);
        
        while(m.find()){
            lines++;
        }
        
        return lines;
    }
}
