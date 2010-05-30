package pt.um.mrc.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VolumeHelper
{
    protected VolumeHelper()
    {}

    public static int countLinesOfCode(String code)
    {
        int lines = 0;
        
        Pattern p = Pattern.compile("[^\\s]*\\n");
        Matcher m = p.matcher(code);
        
        while(m.find()){
            lines++;
        }
        
        return lines;
    }
}
