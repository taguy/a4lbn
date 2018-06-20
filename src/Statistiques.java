package arcanor;
/**
 * Classe statistiques qui regroupe toutes les statistiques
 * d'un joueur durant son temps de jeu
 * @author Romain Precigout
 * @author Brice Laigo
 * @author Jonathan Bautista
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 * @version 1.0
 */
public class Statistiques{
	/** Nombre de parties jouée */
	private int nbParties;
	/** Nombres de victoires */
	private int nbVictoires;
	/** Nombre de pions mangés */
	private int nbPionsMange;
	/** Temps de jeu total */
	private double tempsDeJeuTotal;
	/** Joueur concerné */
	private Joueur joueur;


	/**
	 * Constructeur de la classe Statistiques qui initialise les attributs
	 * @param joueur - le joueur a qui appartiennent les statistiques
	 */
	public Statistiques(Joueur joueur){
		this.joueur = joueur;

	}

	/**
	 *Imprime une version des statistiques pour en avoir un apercu
	 *@return les statistiques en version chaine de caractère
	 */
	String display(){
		String stats ="Statistiques du joueur "+joueur.getNom() +"\n"+
		"Nombre de parties jouées : " +this.nbParties +"\n" +
		"Nombre de parties gagnées : "+ this.nbVictoires +"\n" +
		"Taux de victoire : "+ this.nbVictoires/this.nbParties*100 +"\n" +
		"Nombre de pions mangés : "+this.nbPionsMange +"\n" +
		"Temps perdu sur Arcanor : "+this.tempsDeJeuTotal;
		return stats;
	}

	/**
	 * Sauvegarde les statistiques dans un fichier
	 */
	void saveStat(){
	}

	/**
	 * Lis les statistiques a partir d'un fichier
	 * @param fileName - nom du fichier à lire
	 */
	void readStat(String fileName){
	}

	/**
	 * Met a jour les statistiques du joueur
	 * @param index - index de l'attribut a modifier
	 * @param valeur - la valeure a ajouter
	 */
	void updateStat(int index, int valeur){
	}

	/**
	 * Récupère le nombre de parties jouées par le joueur
	 * @return le nombre de parties jouées
	 */
	 int getNbParties(){
		return this.nbParties;
	}

	/**
	 * Récupère le nombre de parties gagnées par le joueur
	 * @return le nombre de parties gagnées
	 */
	 int getNbVictoires(){
		return this.nbVictoires;
	}

	/**
	 * Récupère le nombre de pions mangés par le joueur
	 * @return le nombre de pions mangés
	 */
	 int getNbPionsMange(){
		return this.nbPionsMange;
	}

	/**
	 * Récupère le temps de jeu total du joueur
	 * @return le temps de jeu total du joueur
	 */
	 Double getNbTempsDeJeuTotal(){
		return this.tempsDeJeuTotal;
	}
}
