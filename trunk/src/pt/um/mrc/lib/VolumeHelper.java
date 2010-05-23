package pt.um.mrc.lib;


public class VolumeHelper
{
    protected VolumeHelper()
    {}

    public static int countLinesOfCode(String code)
    {
        return code.split("\\n").length;
    }

}
