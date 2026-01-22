package lib_ekrebs.defines.ECMA_48;

/**
 * ECMA-48 Cursor Control Functions (8.2.7)
 * @see <a href="https://ecma-international.org/wp-content/uploads/ECMA-48_5th_edition_june_1991.pdf">ECMA-48 §8.2.7 Cursor control functions</a>
 */
public final class ECMA_Cursor_codes
{
    private static final String CSI   = "\u001B[";


    // ===== POSITION ABSOLUE =====
    /** Cursor Position (CUP) CSI Ps ; Ps H */
    public static String CUP(int row, int col) { return CSI + row + ";" + col + "H"; }
    
    /** Cursor to (row,col) - 1-based */
    public static String TO(int row, int col) { return CSI + row + ";" + col + "H"; }

    // ===== MOUVEMENTS RELATIFS =====
    /** Cursor Up Pn rows (CUU) */
    public static String UP(int n) { return CSI + n + "A"; }
    public static String UP() { return CSI + "A"; }  // 1 par défaut
    
    /** Cursor Down Pn rows (CUD) */
    public static String DOWN(int n) { return CSI + n + "B"; }
    public static String DOWN() { return CSI + "B"; }
    
    /** Cursor Forward Pn cols (CUF) */
    public static String RIGHT(int n) { return CSI + n + "C"; }
    public static String RIGHT() { return CSI + "C"; }
    
    /** Cursor Backward Pn cols (CUB) */
    public static String LEFT(int n) { return CSI + n + "D"; }
    public static String LEFT() { return CSI + "D"; }

    // ===== POSITIONS SPÉCIALES =====
    /** Cursor Home (top-left) */
    public static final String HOME     = CSI + "H";           // 1;1
    
    /** Cursor to column 1 (current row) */
    public static final String COL1    = CSI + "G";           // CHt
    
    /** Cursor Next Line */
    public static final String NEXT_LINE = CSI + "E";         // CNL
    
    /** Cursor Previous Line */
    public static final String PREV_LINE = CSI + "F";         // CPL

    // ===== SAUTS & TABS =====
    /** Tabulation (HTS) */
    public static final String TAB_SET  = CSI + "H";          // HTS
    public static final String TAB_CLEAR= CSI + "g";          // TBC (current)
    
    /** Clear All Tabs */
    public static final String TAB_CLEAR_ALL = CSI + "3g";

    // ===== VISIBILITÉ =====
    /** Cursor Visible */
    public static final String CURSOR_SHOW = CSI + "?25h";
    
    /** Cursor Invisible */
    public static final String CURSOR_HIDE = CSI + "?25l";
    
    /** Steady (non-blinking) Cursor */
    public static final String CURSOR_STEADY = CSI + "?12h";
    
    /** Blinking Cursor */
    public static final String CURSOR_BLINK = CSI + "?12l";

    // ===== SAUVEGARDE/RESTORE =====
    /** Save Cursor Position */
    public static final String CURSOR_SAVE = CSI + "s";
    
    /** Restore Cursor Position */
    public static final String CURSOR_RESTORE = CSI + "u";
}
