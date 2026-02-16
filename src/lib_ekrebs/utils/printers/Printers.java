package lib_ekrebs.utils.printers;
import static lib_ekrebs.defines.ECMA_48.ECMA_SGR_codes.*;
import static lib_ekrebs.defines.box_drawing_chars.BoxDrawingChars.*;

public class Printers
{
    public static String AddTabulationLevel(String str, int TabLevel)
    {
        return (str.replace("\n", "\n"+LINE_LIGHT_VERTICAL+"\t".repeat(TabLevel)));
    }

    public static String toDisplay(String str)
    {
        if (str == null)
            System.out.println(BG_RED+" Error:"+RESET+"str = null");
        return(str.replace("\n", "\n│").replace("│─", "┌─").replace("│\n┌", "│\n├")+"\n└");
    }

    public static String highlightFirstWords(String str, String bg_color)
    {
        bg_color = toBGColors(bg_color);
        str.replaceAll("(\\n(?:\\│)?)(\\S+)", "$1" + bg_color + "$2" + RESET);
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
            case BG_WHITE   -> {return(BG_WHITE);   }
            case WHITE      -> {return(BG_WHITE);   }
            case BG_RED     -> {return(BG_RED);     }
            case RED        -> {return(BG_RED);     }
            case BG_GREEN   -> {return(BG_GREEN);   }
            case GREEN      -> {return(BG_GREEN);   }
            case BG_BLUE    -> {return(BG_BLUE);    }
            case BLUE       -> {return(BG_BLUE);    }
            case BG_BLACK   -> {return(BG_BLACK);   }
            case BLACK      -> {return(BG_BLACK);   }
            case BG_YELLOW  -> {return(BG_YELLOW);  }
            case YELLOW     -> {return(BG_YELLOW);  }
            case BG_MAGENTA -> {return(BG_MAGENTA); }
            case MAGENTA    -> {return(BG_MAGENTA); }
            case BG_CYAN    -> {return(BG_CYAN);    }
            case CYAN       -> {return(BG_CYAN);    }
            default         -> {return(BG_WHITE);   }
        }
    }
}
