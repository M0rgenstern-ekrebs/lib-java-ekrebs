package tests;

import java.util.InputMismatchException;
import java.util.Scanner;
import static lib_ekrebs.utils.printers.imagePrinter.*;
import static lib_ekrebs.utils.printers.gifPrinter.*;

public class Main {
	public static void run_tests() {
		// Scanner sc;

		// sc.close();
		try {
			print_gif("tests/ressources/gifs/wh40k_tech_scream.gif");
		} catch (Exception e) {
			System.out.print("gif aborted");
		} finally {
			print_image("tests/ressources/images/cool_glasses.png");
		}
	}

	public static void main(String Args[]) {
		try {
			run_tests();
		} catch (Exception e) {
			System.out.print("Error running tests");
		}
	}
}