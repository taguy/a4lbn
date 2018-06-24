package model;
import controller.*;
import java.util.*;
import java.io.*;
/**
 * Classe lanceur qui lance le jeu Arcanor
 * @author Brice Laigo
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 */
public class Lanceur{
	/** Mode console ou mode graphique */
	private static boolean graphique;

	/** Toutes les parties enregistrées */
	private static ArrayList<Partie> lesParties;

	/** Tous les joueurs enregistrés */
	private static ArrayList<Joueur> lesJoueurs;

	/** Toutes les statistiques enregistrés */
	private static ArrayList<Statistiques> lesStats;

	public static void main(String[] args){
		init();
		menu();
	}

	/**
	 * Initialise les ArrayList avec les parties, joueurs et statistiques sauvegardées
	 */
	private static void init() {
		lesParties = new ArrayList<Partie>(0);
		Sauvegarde.initLesParties(lesParties);

		lesJoueurs = new ArrayList<Joueur>(0);
		Sauvegarde.initLesJoueurs(lesJoueurs);

		lesStats = new ArrayList<Statistiques>(0);
		Sauvegarde.initLesStats(lesStats);
	}


	/**
	 * Menu appelé par le main.
	 * Permet de créer la partie, charger des joueurs, charger une partie
	 */
	public static void menu() {

		if (Utilitaire.reponseUtilisateur("\nBienvenue dans le jeu projetZ !\n Tapez '1' si vous voulez jouer en mode graphique, Tapez '2' sinon\n puis tapez Entree\n", 1, 2, 1).equals("1")) {
			graphique = true;
		}

		if (graphique) {
			//code de Lorenzo
		} else {
			console();
		}
	}


	/**
	* Créé la partie et charge les joueurs
	*/
   	private static Partie choixPartie() {
		Partie ret = null;
		Scanner in = new Scanner(System.in);
		String reponse = "";

		for (int i = 0; i < lesParties.size(); i++) {
			System.out.println(i + " " + lesParties.get(i).getJoueurA().getNom() + " " + lesParties.get(i).getJoueurB().getNom() + " " + lesParties.get(i).getNom() + " " + lesParties.get(i).getTours() + " " + lesParties.get(i).getScoreA() + " " + lesParties.get(i).getScoreB());
		}
		System.out.println("\n" + lesParties.size() + " Nouvelle Partie");

		reponse = Utilitaire.reponseUtilisateur("\nEntrez le numero de la partie voulue\n puis tapez Entree\n",0, lesParties.size(), (lesParties.size()+"").length());

		if (reponse.equals(lesParties.size()+"")) {
			boolean check = false;
			do {
				System.out.println("\nEntrez le nom de la partie\n puis tapez Entree\n");
				reponse = in.nextLine();

				if (reponse.length() < 2) {
					System.out.println("reponse nulle ou trop petite");
				} else {
					check = true;
				}
			} while (!check);

			if (reponse != null && reponse.length() > 1) {
				Joueur joueurA = choixJoueur();
			   	Joueur joueurB = choixJoueur();

				lesJoueurs.add(joueurA);
				lesJoueurs.add(joueurB);

			   	ret = new Partie(lesJoueurs.get(lesJoueurs.size()-2), lesJoueurs.get(lesJoueurs.size()-1), reponse);

				ret.getJoueurA().setDamier(ret.getDamier());
				ret.getJoueurB().setDamier(ret.getDamier());
			} else {

				ret = null;
			}
		} else {
			ret = lesParties.get(Integer.parseInt(reponse));
		}

		return ret;
   	}


	/**
	 * Permet de joueur après avoir initialisé les joueurs et la partie
	 */
	public static void console(){
		Partie p = choixPartie();

		if (p != null) {
			lesParties.add(p);
			lesParties.get(lesParties.size()-1).jouer();
		} else {
			System.exit(0);
		}

		if (Utilitaire.reponseUtilisateur("Tapez 1 si vous voulez Sauvegarder, 2 sinon", 1, 2, 1).equals(1)) {
			lesParties.remove(lesParties.size()-1);

			lesJoueurs.remove(lesJoueurs.size()-1);
			lesJoueurs.remove(lesJoueurs.size()-1);
		}

		Sauvegarde.sauveLesParties(lesParties);
		Sauvegarde.sauveLesJoueurs(lesJoueurs);
		Sauvegarde.sauveLesStats(lesStats);
    }


	/**
	 * Permet de choisir les joueurs sauvegardés ou de créer un joueur
	 * @return le joueur choisi
	 */
	private static Joueur choixJoueur() {
		Joueur ret = null;
 		Scanner in = new Scanner(System.in);
 		String reponse = null;

 		do {
 			System.out.println("\n\n");

 			for (int i = 0; i < lesJoueurs.size(); i++) {
 				System.out.println(i + " " + lesJoueurs.get(i).getNom());
 			}

 			System.out.println("\n" + lesJoueurs.size() + " Nouveau Joueur");
 			System.out.println(lesJoueurs.size()+1 + " Nouvelle IA\n");

 			reponse = Utilitaire.reponseUtilisateur("\nEntrez le numero du joueur voulu\n puis tapez Entree\n",0, (lesJoueurs.size()+1), ((lesJoueurs.size()+1)+"").length());

 			if (reponse.equals(lesJoueurs.size()+"")) {
				boolean check = false;
				do {
					System.out.println("\nEntrez le nom du joueur\n puis tapez Entree\n");
					reponse = in.nextLine();

					if (reponse.length() < 2) {
						System.out.println("reponse nulle ou trop petite");
					} else {
						check = true;
					}
				} while (!check);

 				ret = new Joueur(reponse);

 			} else if (reponse.equals((lesJoueurs.size()+1)+"")) {
				boolean check = false;
				do {
					System.out.println("\nEntrez le nom de l'IA\n puis tapez Entree\n");
					reponse = in.nextLine();

					if (reponse.length() < 2) {
						System.out.println("reponse nulle ou trop petite");
					} else {
						check = true;
					}
				} while (!check);

 				ret = new IA(reponse);

 			} else {
 				ret = lesJoueurs.get(Integer.parseInt(reponse));
 				lesJoueurs.remove(Integer.parseInt(reponse));
 			}

 		} while (ret == null);

 		return ret;
	}
}
