package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


public class Utilitaire {

	/**
	 * Vérifie que les reponses de l'utilisateur sont valides, comprises entre deux bornes et ont une certaine longueur 
	 * @param a la premiere borne
	 * @param b la seconde borne
	 * @param longueur la longueur voulu
	 * @param message Le message de l'interaction utilisateur
	 * @return l'entier
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

			if (reponse.equalsIgnoreCase("exit")) {
				ret = "exit";
			} else if (reponse != null && message != null) {
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

		} while (!check);

		return ret;
	}

	/**
	 * Retoune un tableau d'entiers correspondant a la position x et y
	 * @param message Le message de l'interaction utilisateur
	 * @return ret : le tableau  d'entiers
	 */
	 public static int[] stringToInt(String message){
		 int[] ret = new int[2];

		 String tmp;
		 do {
			 System.out.println("La reponse doit avoir deux chiffres : ex : '00'\n");
			 tmp = reponseUtilisateur(message, 0, 76, 2);

			 if (tmp.equals("exit") && message.equals("Quelle la position destination ?")) {
				 break;
			 }

		 } while (tmp.length() != 2);

		 if (tmp.equals("exit")) {
			 ret[0] = -1;
			 ret[1] = -1;
		 } else {
			 ret[0] = Integer.parseInt(tmp.substring(0, 1)); // récupération du chiffre des dizaines
			 ret[1] = Integer.parseInt(tmp.substring(1, 2)); // récupération du chiffre des unités
		 }

		 return ret;
	 }
}
