package utili;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Utilitaire {

	/**
	 * Vérifie que les reponses de l'utilisateur sont valides et comprises entre deux bornes
	 * @param a la premiere borne
	 * @param b la seconde borne
	 * @return l'entier si valide, -50 sinon
	 */
	public static String reponseUtilisateur(String message, int a, int b, int longueur) {
		String ret = "-50";
		String reponse;

		Scanner in = new Scanner(System.in);

		boolean check = true;

		do {
			System.out.println("\n" + message);
			reponse = in.nextLine();

			check = true;

			if (reponse != null && message != null) {
				if (reponse.equalsIgnoreCase("exit")) {
					ret = "exit";
				}
				boolean checkNumber = true;
				int i = 0;
				while (i < reponse.length() && checkNumber) {
					if (!Character.isDigit(reponse.charAt(i))) {
						checkNumber = false;
					}

						i++;
				}

				if (((reponse.length() <= longueur) && (reponse.length() > 0)) && checkNumber) {
					if (Integer.parseInt(reponse) >= a && Integer.parseInt(reponse) <= b) {
						ret = reponse;
					} else {
						System.out.println("La reponse n'est pas comprise entre " + a + " et " + b);
						check = false;
					}
				} else {
					System.out.println("Reponse trop longue ou petite, veuillez réessayer");
					check = false;
				}
			} else {
				System.out.println("Reponse nulle, veuillez réessayer");
				check = false;
			}

		} while (!check && !ret.equals("exit"));

		return ret;
	}

	/**
	 * intToString retoune un tableau d'int correspondant a la position x et y
	 * @return ret : le tableau  d'entiers
	 */
	 public static int[] stringToInt(String message){
		 int[] ret = new int[2];

		 String tmp;
		 do {
			 System.out.println("La reponse doit avoir deux chiffres : ex : '00'\n");
			 tmp = reponseUtilisateur(message, 0, 76, 2);

		 } while (tmp.length() != 2);


		 ret[0] = Integer.parseInt(tmp.substring(0, 1)); // récupération du chiffre des dizaines
		 ret[1] = Integer.parseInt(tmp.substring(1, 2)); // récupération du chiffre des unités

		 return ret;
	 }
}
