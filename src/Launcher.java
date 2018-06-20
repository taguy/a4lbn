package projetZ;
import java.util.*;
import java.io.*;
import utili.Utilitaire;
/**
 * Classe lanceur qui lance le jeu projetZ
 * @author Brice Laigo
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 * @version 1.0
 */
public class Launcher{
	/** Mode console ou mode graphique */
	private static boolean graphique;
	/** Toutes les parties enregistrées */
	private static ArrayList<Partie> lesParties;
	/** Tous les joueurs enregistrés */
	private static ArrayList<Joueur> lesJoueurs;

	private static final String SAUVJOUEURS = "../files/lesJoueurs.txt";

	private static final String SAUVPARTIES = "../files/lesParties.txt";

	public static void main(String[] args){
		menu();
		scenario();
	}

	/**
	 * Menu appelé par le main.
	 * Permet de créer la partie, charger des joueurs, charger une partie
	 */
	public static void menu() {
		System.out.println();

		if (Utilitaire.reponseUtilisateur("\nBienvenue dans le jeu Arcanor !\n Tapez '1' si vous voulez jouer en mode graphique, Tapez '2' sinon\n puis tapez Entree\n", 1, 2, 1).equals("1")) {
			graphique = true;
		}

		if (!graphique) {
			loadPartie();

			System.out.println();

			if (Utilitaire.reponseUtilisateur("\nTapez '1' si vous voulez creer une partie, Tapez '2' si vous voulez charger une partie\n puis tapez Entree\n", 1, 2, 1).equals("1")) {
				createPartie();
			} else {
				for (int i = 0; i < lesParties.size(); i++) {
					System.out.println(i + " " + lesParties.get(i).getJoueurA().getNom() + " " + lesParties.get(i).getJoueurB().getNom() + " " + lesParties.get(i).getNom() + " " + lesParties.get(i).getTours() + " " + lesParties.get(i).getScoreA() + " " + lesParties.get(i).getScoreB());
				}
System.out.println(lesParties.size());
				String reponse = Utilitaire.reponseUtilisateur("\nEntrez le numero de la partie voulue\n puis tapez Entree\n",0, (lesParties.size()-1), ((lesParties.size()-1)+"").length());
				lesParties.get(Integer.parseInt(reponse));
			}
		}
	}

	/**
	 * Créé la partie et charge les joueurs
	 */
	private static void createPartie() {
		Scanner in = new Scanner(System.in);
		String reponse = "";

		initializeLesJoueurs();

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

	private static Joueur choixJoueur() {
		Joueur ret = null;
		Scanner in = new Scanner(System.in);
		String reponse = null;

		System.out.println("\n\n");

		for (int i = 0; i < lesJoueurs.size(); i++) {
			System.out.println(i + " " + lesJoueurs.get(i).getNom());
		}

		System.out.println("\n" + lesJoueurs.size() + " Nouveau Joueur");
		System.out.println(lesJoueurs.size()+1 + " Nouvelle IA\n");


		if (reponse.equals(lesJoueurs.size()+"")) {
			System.out.println("\nEntrez votre nom du joueur\n puis tapez Entree\n");
			reponse = in.nextLine();
			if (reponse != null) {
				ret = new Joueur(reponse);
			} else {
				System.out.println("reponse nulle");
			}
		} else if (reponse.equals((lesJoueurs.size()+1)+"")) {
			System.out.println("\nEntrez votre nom de l'IA\n puis tapez Entree\n");
			reponse = in.nextLine();

			if (reponse != null) {
					ret = new IA(reponse);
			} else {
					System.out.println("reponse nulle");
			}
		} else {
			ret = lesJoueurs.get(Integer.parseInt(reponse));
			lesJoueurs.remove(Integer.parseInt(reponse));
		}

		return ret;
	}


	/**
	 * charge une partie et ses joueurs (déjà existants)
	 */
	private static void loadPartie(){
		initializeLesParties(extractPartie());
	}


	/**
	 * Extrait les infos du fichier de sauvegarde parties
	 * @return la hashMap contenant tous les joueurs enregistrés
	 */
	private static ArrayList<String> extractPartie() {
		ArrayList<String> ret = new ArrayList<String>(0);

		try {
			lesParties = new ArrayList<Partie>(0);
			BufferedReader in = new BufferedReader(new FileReader (SAUVPARTIES));
			String s = in.readLine();
			while(s != null) {
				ret.add(s);
				s = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}


	/**
	 * initialise les joueurs qui participeront à la partie en cours
	 * @param map - la hashMap de tous les joueurs
	 */
	private static void initializeLesJoueurs(){
		try {
			lesJoueurs = new ArrayList<Joueur>(0);
			BufferedReader in = new BufferedReader(new FileReader (SAUVJOUEURS));
			String s = in.readLine();

			while(s != null) {
				lesJoueurs.add(new Joueur(s));
				s = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * initialise les parties
	 * @param map - la hashMap de toutes les parties
	 */
	private static void initializeLesParties(ArrayList<String> list){
		StringTokenizer stk;

		for (int i = 0; i < list.size(); i++) {
			stk = new StringTokenizer(list.get(i));
			//Joeur A, jB,nom, tours, scoreA score B

			lesParties.add(new Partie(new Joueur(stk.nextToken()), new Joueur(stk.nextToken()), stk.nextToken(), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
			lesParties.get(i).getJoueurA().setDamier(lesParties.get(i).getDamier());
			lesParties.get(i).getJoueurB().setDamier(lesParties.get(i).getDamier());
		}

	}

	public static void scenario(){
        lesParties.get(lesParties.size()-1).jouer();
    }


}
