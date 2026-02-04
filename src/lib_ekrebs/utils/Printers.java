package lib_ekrebs.utils;
import static lib_ekrebs.defines.ECMA_48.ECMA_SGR_codes.*;
import static lib_ekrebs.defines.box_drawing_chars.BoxDrawingChars.*;

public class Printers
{
    public static String AddTabLevel(String str, int TabLevel)
    {
        return (str.replace("\n", "\n"+LINE_LIGHT_VERTICAL+"\t".repeat(TabLevel)));
    }

    public static String toDisplay(String str)
    {
        if (str == null)
            System.out.println(BG_RED+" Error:"+RESET+"str = null");
        return(str.replace("\n", "\n│").replace("│─", "┌─").replace("│\n┌", "│\n├")+"\n└");
    }

    public static String toMacaron(String str, String color)
    {
        String result;

        if (str == null)
            return "";
        result = str.trim();
        if (!result.startsWith(" "))
        {
            result = " "+result;
        }
        if (!result.endsWith(":"))
        {
            result = result + ":";
        }
        return ("\n─"+UNDERLINE+color+result+RESET);
    }
}
