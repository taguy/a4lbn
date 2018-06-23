package model;
import controller.*;
import java.util.*;
import java.io.*;
/**
 * Classe lanceur qui lance le jeu projetZ
 * @author Brice Laigo
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 * @version 1.0
 */
public class Lanceur{
	/** Mode console ou mode graphique */
	private static boolean graphique;
	/** Toutes les parties enregistrées */
	private static ArrayList<Partie> lesParties;
	/** Tous les joueurs enregistrés */
	private static ArrayList<Joueur> lesJoueurs;

	private static ArrayList<Statistiques> lesStats;

	public static void main(String[] args){
		menu();
		scenario();
	}

	/**
	 * Menu appelé par le main.
	 * Permet de créer la partie, charger des joueurs, charger une partie
	 */
	public static void menu() {
		init();

		if (Utilitaire.reponseUtilisateur("\nBienvenue dans le jeu projetZ !\n Tapez '1' si vous voulez jouer en mode graphique, Tapez '2' sinon\n puis tapez Entree\n", 1, 2, 1).equals("1")) {
			graphique = true;
		}

		if (!graphique) {
			if (Utilitaire.reponseUtilisateur("\nTapez '1' si vous voulez creer une partie, Tapez '2' si vous voulez charger une partie\n puis tapez Entree\n", 1, 2, 1).equals("1")) {
				creerPartie();
			} else {
				for (int i = 0; i < lesParties.size(); i++) {
					System.out.println(i + " " + lesParties.get(i).getJoueurA().getNom() + " " + lesParties.get(i).getJoueurB().getNom() + " " + lesParties.get(i).getNom() + " " + lesParties.get(i).getTours() + " " + lesParties.get(i).getScoreA() + " " + lesParties.get(i).getScoreB());
				}

				String reponse = Utilitaire.reponseUtilisateur("\nEntrez le numero de la partie voulue\n puis tapez Entree\n",0, (lesParties.size()-1), ((lesParties.size()-1)+"").length());
				lesParties.get(Integer.parseInt(reponse));
			}
		}
	}

	/**init
	* Créé la partie et charge les joueurs
	*/
   private static void creerPartie() {
	   Scanner in = new Scanner(System.in);
	   String reponse = "";

 	   System.out.println("\nChoisssez un nom de partie :\n");
	   reponse = in.nextLine();

	   Joueur joueurA = choixJoueur();
	   Joueur joueurB = choixJoueur();

	   lesJoueurs.add(joueurA);
	   lesJoueurs.add(joueurB);


	   lesParties.add(new Partie(lesJoueurs.get(lesJoueurs.size()-2), lesJoueurs.get(lesJoueurs.size()-1), reponse));

	   lesParties.get(lesParties.size()-1).getJoueurA().setDamier(lesParties.get(lesParties.size()-1).getDamier());
	   lesParties.get(lesParties.size()-1).getJoueurB().setDamier(lesParties.get(lesParties.size()-1).getDamier());
   }


	public static void scenario(){
        lesParties.get(lesParties.size()-1).jouer();

		if (Utilitaire.reponseUtilisateur("Tapez 1 si vous voulez Sauvegarder, 2 sinon", 1, 2, 1).equals(1)) {
			lesParties.remove(lesParties.size()-1);

			lesJoueurs.remove(lesJoueurs.size()-1);
			lesJoueurs.remove(lesJoueurs.size()-1);
		}

		Sauvegarde.sauveLesParties(lesParties);
		Sauvegarde.sauveLesJoueurs(lesJoueurs);
		Sauvegarde.sauveLesStats(lesStats);
    }


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
 				System.out.println("\nEntrez votre nom du joueur\n puis tapez Entree\n");
 				reponse = in.nextLine();
 				if (reponse != null && reponse.length() > 1) {
 					ret = new Joueur(reponse);
 				} else {
 					System.out.println("reponse nulle ou trop petite");

 					ret = null;
 				}
 			} else if (reponse.equals((lesJoueurs.size()+1)+"")) {
 				System.out.println("\nEntrez votre nom de l'IA\n puis tapez Entree\n");
 				reponse = in.nextLine();

 				if (reponse != null && reponse.length() > 1) {
 						ret = new IA(reponse);
 				} else {
 						System.out.println("reponse nulle ou trop petite");

 						ret = null;
 				}
 			} else {
 				ret = lesJoueurs.get(Integer.parseInt(reponse));
 				lesJoueurs.remove(Integer.parseInt(reponse));
 			}

 		} while (ret == null);

 		return ret;
 	}


	private static void init() {
		lesParties = new ArrayList<Partie>(0);
		Sauvegarde.initLesParties(lesParties);

		lesJoueurs = new ArrayList<Joueur>(0);
		Sauvegarde.initLesJoueurs(lesJoueurs);

		lesStats = new ArrayList<Statistiques>(0);
		Sauvegarde.initLesStats(lesStats);

	}
}
