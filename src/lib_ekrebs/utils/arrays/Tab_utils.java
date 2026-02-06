package lib_ekrebs.utils.arrays;

import static java.lang.System.*;

public class Tab_utils
{
	public static void init_tab(int[][] tab)
	{
		int i;

		i = 0;
		while(i < tab.length)
		{
			init_tab(tab[i]);
			i++;
		}
	}

	public static void init_tab(int[] tab)
	{
		int i;

		i = 0;
		while(i < tab.length)
		{
			tab[i] = 0;
			i++;
		}
	}

	public static void print_tab(int[][] tab, String name)
	{
		int i;

		out.printf("\n");
		i = 0;
		while(i < tab.length)
		{
			out.printf("%s[%-03d]", name, i);
			print_tab(tab[i]);
			i++;
		}
		out.printf("\n");
	}

	private static void print_tab(int[] tab)
	{
		int i;

		i = 0;
		while(i < tab.length)
		{
			out.printf("[%-03d]: %d", i, tab[i]);
			i++;
		}
	}

	public static void print_tab(int[] tab, String name)
	{
		int i;

		out.printf("\n");
		i = 0;
		while(i < tab.length)
		{
			out.printf("%s[%-03d]: %d", name, i, tab[i]);
			i++;
		}
		out.printf("\n");
	}
}
