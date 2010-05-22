package pt.um.mrc.lib;

import pt.um.mrc.util.datatypes.Pair;
import pt.um.mrc.util.datatypes.PairImpl;

public class VolumeHelper
{
    protected VolumeHelper()
    {}

    public static int countLinesOfCode(String code)
    {
        return code.split("\\n").length;
    }

    public static Pair<String, Integer> processKeyValue(String text)
    {
        Pair<String, Integer> p = new PairImpl<String, Integer>();

        String[] aux1 = text.split("\t");
        String[] aux2 = aux1[0].split("\\-");

        StringBuilder sb = new StringBuilder();
        sb.append(aux2[0]);

        for (int i = 1; i < aux2.length - 1; i++)
        {
            sb.append('-');
            sb.append(aux2[i]);
        }

        p.setKey(sb.toString());
        p.setValue(Integer.parseInt(aux1[1]));

        return p;
    }
}
