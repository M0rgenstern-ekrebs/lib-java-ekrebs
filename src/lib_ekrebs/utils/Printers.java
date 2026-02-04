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
        color = toBGColors(color);
        return ("\n─"+UNDERLINE+color+result+RESET);
    }


    public static void printMacaron(String str, String color)
    {
        System.out.println(toMacaron(str, color));
    }

    public static void printDone()
    {
        System.out.println(BOLD+GREEN+"[DONE]\n"+RESET);
    }
    
    public static void printLine(String color)
    {
        color = toBGColors(color);
        System.out.println(color+"\n"+RESET);
    }

    public static String toBGColors(String SGR_color)
    {
        switch (SGR_color)
        {
            case WHITE      -> {return(BG_WHITE);   }
            case RED        -> {return(BG_RED);     }
            case GREEN      -> {return(BG_RED);     }
            case BLUE       -> {return(BG_BLUE);    }
            case BLACK      -> {return(BG_BLACK);   }
            case YELLOW     -> {return(BG_YELLOW);  }
            case MAGENTA    -> {return(BG_MAGENTA); }
            case CYAN       -> {return(BG_CYAN);    }
            default         -> {return(BG_WHITE);   }
        }
    }
}
