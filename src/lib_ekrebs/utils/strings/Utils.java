package lib_ekrebs.utils.strings;

public class Utils
{
	public static boolean is_letter(char c)
	{
        c = Character.toLowerCase(c);
        return c >= 'a' && c <= 'z';
    }

	public static char index_to_letter(int letter_index)
	{
    	if (letter_index < 0 || letter_index > 25)
        	throw new IllegalArgumentException("letter_index must be between 0 and 25");
    	return (char) ('a' + letter_index);
	}
}
