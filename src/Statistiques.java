package model;
import controller.*;

/**
 * Classe statistiques qui regroupe toutes les statistiques
 * d'un joueur durant son temps de jeu
 * @author Brice Laigo
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 */
public class Statistiques implements java.io.Serializable {
	/** Nombre de parties jouées */
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
	 * Constructeur de la classe Statistiques qui initialise les attributs pour création
	 * @param joueur - le joueur a qui appartiennent les statistiques
	 */
	public Statistiques(Joueur joueur){
		if(joueur != null){
			this.joueur = joueur;
		}else{
			System.out.println("Le joueur n'existe pas (Statistiques)");
		}
	}


	/**
	 * Imprime une version des statistiques pour en avoir un apercu
	 * @return les statistiques en version chaine de caractère
	 */
	public String toString(){
		String stats ="Statistiques du joueur "+joueur.getNom() +"\n"+
		"Nombre de parties jouées : " +this.nbParties +"\n" +
		"Nombre de parties gagnées : "+ this.nbVictoires +"\n" +
		"Taux de victoire : "+ this.nbVictoires/this.nbParties*100 +"\n" +
		"Nombre de pions mangés : "+this.nbPionsMange +"\n" +
		"Temps perdu sur Arcanor : "+this.tempsDeJeuTotal;
		return stats;
	}


	/**
	 * Met a jour les statistiques du joueur
 	 * @param nbParties le nombre de parties jouées à ajouter
 	 * @param nbVictoires Le nombre de victoires à ajouter
 	 * @param nbPionsMange le nombre de pions mangés à ajouter
 	 * @param tempsDeJeuTotal Le temps de jeu à ajouter
 	 */
	 public void updateStats(int nbParties, int nbVictoires, int nbPionsMange, double tempsDeJeuTotal){
         if(nbVictoires >= 0){
             this.nbVictoires += nbVictoires;
             if(nbPionsMange >= 0){
                 this.nbPionsMange += nbPionsMange;
                 if(tempsDeJeuTotal >= 0){
                     this.tempsDeJeuTotal += tempsDeJeuTotal;
                     if(nbParties  >= 0){
                         this.nbParties += nbParties;
                     }else{System.out.println("(Update) Nombre de parties invalide");}
                 }else{System.out.println("(Update) Temps de jeu invalide");}
             }else{System.out.println("(Update) Nombre de pions mange invalide");}
         }else{System.out.println("(Update) Nombre de victoires invalide");}
     }


	/**
 	 * Récupère le joueur concerné par ces statistiques
 	 * @return le joueur
 	 */
	public Joueur getJoueur(){
	   return this.joueur;
   	}


	/**
	 * Récupère le nombre de parties jouées par le joueur
	 * @return le nombre de parties jouées
	 */
	public int getNbParties(){
		return this.nbParties;
	}


	/**
	 * Récupère le nombre de parties gagnées par le joueur
	 * @return le nombre de parties gagnées
	 */
	public int getNbVictoires(){
		return this.nbVictoires;
	}


	/**
	 * Récupère le nombre de pions mangés par le joueur
	 * @return le nombre de pions mangés
	 */
	public int getNbPionsMange(){
		return this.nbPionsMange;
	}


	/**
	 * Récupère le temps de jeu total du joueur
	 * @return le temps de jeu total du joueur
	 */
	public double getNbTempsDeJeuTotal(){
		return this.tempsDeJeuTotal;
	}
}
