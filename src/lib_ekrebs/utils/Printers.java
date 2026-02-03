package lib_ekrebs.utils;

public class Printers
{
    public static String AddTabLevel(String str, int TabLevel)
    {
        return (str.replace("\n", "\n"+"\t".repeat(TabLevel)));
    }
}
