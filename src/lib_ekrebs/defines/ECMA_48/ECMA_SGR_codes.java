package lib_ekrebs.defines.ECMA_48;

/**
 * ECMA-48 SGR (8.3.117) Select Graphic Rendition codes - Complete set
 * @see <a href="https://ecma-international.org/wp-content/uploads/ECMA-48_5th_edition_june_1991.pdf">ECMA-48 SGR §8.3.117</a>
 *
 */
public final class ECMA_SGR_codes
{
    private static final String CSI   = "\u001B[";
    private static final String FINAL = "\u006D";

    // ===== RESET =====
    public static final String RESET  = CSI + "0"  + FINAL;

    // ===== FOREGROUND COLORS (30-39) =====
    public static final String BLACK      = CSI + "30" + FINAL;
    public static final String RED        = CSI + "31" + FINAL;
    public static final String GREEN      = CSI + "32" + FINAL;
    public static final String YELLOW     = CSI + "33" + FINAL;
    public static final String BLUE       = CSI + "34" + FINAL;
    public static final String MAGENTA    = CSI + "35" + FINAL;
    public static final String CYAN       = CSI + "36" + FINAL;
    public static final String WHITE      = CSI + "37" + FINAL;
    public static final String FG_DEFAULT = CSI + "39" + FINAL;

    // ===== BRIGHT FOREGROUND (90-97 - xterm extension) =====
    public static final String BRIGHT_BLACK  = CSI + "90" + FINAL;
    public static final String BRIGHT_RED    = CSI + "91" + FINAL;
    public static final String BRIGHT_GREEN  = CSI + "92" + FINAL;
    public static final String BRIGHT_YELLOW = CSI + "93" + FINAL;
    public static final String BRIGHT_BLUE   = CSI + "94" + FINAL;
    public static final String BRIGHT_MAGENTA=CSI + "95" + FINAL;
    public static final String BRIGHT_CYAN   = CSI + "96" + FINAL;
    public static final String BRIGHT_WHITE  = CSI + "97" + FINAL;

    // ===== BACKGROUND COLORS (40-49) =====
    public static final String BG_BLACK     = CSI + "40" + FINAL;
    public static final String BG_RED       = CSI + "41" + FINAL;
    public static final String BG_GREEN     = CSI + "42" + FINAL;
    public static final String BG_YELLOW    = CSI + "43" + FINAL;
    public static final String BG_BLUE      = CSI + "44" + FINAL;
    public static final String BG_MAGENTA   = CSI + "45" + FINAL;
    public static final String BG_CYAN      = CSI + "46" + FINAL;
    public static final String BG_WHITE     = CSI + "47" + FINAL;
    public static final String BG_DEFAULT   = CSI + "49" + FINAL;

    // ===== BRIGHT BACKGROUND (100-107 - xterm extension) =====
    public static final String BG_BRIGHT_BLACK  = CSI + "100" + FINAL;
    public static final String BG_BRIGHT_RED    = CSI + "101" + FINAL;
    public static final String BG_BRIGHT_GREEN  = CSI + "102" + FINAL;
    public static final String BG_BRIGHT_YELLOW = CSI + "103" + FINAL;
    public static final String BG_BRIGHT_BLUE   = CSI + "104" + FINAL;
    public static final String BG_BRIGHT_MAGENTA=CSI + "105" + FINAL;
    public static final String BG_BRIGHT_CYAN   = CSI + "106" + FINAL;
    public static final String BG_BRIGHT_WHITE  = CSI + "107" + FINAL;

    // ===== TEXT STYLES (1-9, 21-29) =====
    // Intensity
    public static final String BOLD        = CSI + "1"  + FINAL;
    public static final String BOLD_OFF    = CSI + "22" + FINAL;
    public static final String FAINT       = CSI + "2"  + FINAL;
    public static final String FAINT_OFF   = CSI + "22" + FINAL;

    // Italics
    public static final String ITALIC        = CSI + "3"  + FINAL;
    public static final String ITALIC_OFF    = CSI + "23" + FINAL;

    // Underline
    public static final String UNDERLINE        = CSI + "4"  + FINAL;
    public static final String UNDERLINE_OFF    = CSI + "24" + FINAL;

    // Blink
    public static final String BLINK_SLOW        = CSI + "5"  + FINAL;
    public static final String BLINK_SLOW_OFF    = CSI + "25" + FINAL;
    public static final String BLINK_RAPID       = CSI + "6"  + FINAL;
    public static final String BLINK_RAPID_OFF   = CSI + "26" + FINAL;

    // Strike-through
    public static final String STRIKETHROUGH        = CSI + "9"  + FINAL;
    public static final String STRIKETHROUGH_OFF    = CSI + "29" + FINAL;

    // Invisible (concealed)
    public static final String INVISIBLE        = CSI + "8"  + FINAL;
    public static final String INVISIBLE_OFF    = CSI + "28" + FINAL;

    // Frame / Encircle (rare)
    public static final String FRAME        = CSI + "51" + FINAL;
    public static final String FRAME_OFF    = CSI + "54" + FINAL;
    public static final String ENCIRCLE     = CSI + "52" + FINAL;
    public static final String ENCIRCLE_OFF = CSI + "54" + FINAL;

    // Overline
    public static final String OVERLINE        = CSI + "53" + FINAL;
    public static final String OVERLINE_OFF    = CSI + "55" + FINAL;

    // ===== RGB 24-bit (xterm-256 extension) =====
    public static String RGB(int r, int g, int b)
    {
        return (CSI + String.format("38;2;%d;%d;%d", r, g, b)  + FINAL);
    }

    public static String BG_RGB(int r, int g, int b)
    {
        return (CSI + String.format("48;2;%d;%d;%d", r, g, b) + FINAL);
    }

    // Extract the numeric part from existing constants like "31" from CSI + "31" + FINAL
    public static String BG_FG(String background, String foreground)
    {
        String fgNum;
        String bgNum;

        fgNum = foreground.substring(CSI.length(), foreground.length() - FINAL.length());
        bgNum = background.substring(CSI.length(), background.length() - FINAL.length());
        return (CSI + fgNum + ";" + bgNum + FINAL);
    }

    // Extract the numeric part from existing constants like "31" from CSI + "31" + FINAL
    public static String FG_BG(String foreground, String background)
    {
        String fgNum;
        String bgNum;

        fgNum = foreground.substring(CSI.length(), foreground.length() - FINAL.length());
        bgNum = background.substring(CSI.length(), background.length() - FINAL.length());
        return (CSI + fgNum + ";" + bgNum + FINAL);
    }

    public static String SGR_COMBINE(String... sgr_codes)
    {
        StringBuilder nums;
        String num;

        nums = new StringBuilder();
        for (String code : sgr_codes)
        {
            num = code.substring(CSI.length(), code.length() - FINAL.length());
            if (nums.length() > 0)
                nums.append(";");
            nums.append(num);
        }
        return CSI + nums + FINAL;
    }
}

