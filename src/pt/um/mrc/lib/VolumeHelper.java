package pt.um.mrc.lib;

public class VolumeHelper
{
    protected VolumeHelper()
    {}

    public static int countLinesOfCode(String code)
    {
        // TODO: Review this RegEx
        return code.split("[^\\s]*\\n").length;
    }
}
