package projetZ;
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
		if(joueur != null){
			this.joueur = joueur;
		}else{
			System.out.println("Le joueur n'existe pas (Statistiques)");
		}
	}

	/**
	 * Constructeur de la classe Statistiques qui initialise les attributs
	 * @param joueur - le joueur a qui appartiennent les statistiques
	 */
	public Statistiques(Joueur joueur, int nbParties, int nbVictoires, int nbPionsMange, double tempsDeJeuTotal){
		if(joueur != null){
			this.joueur = joueur;
			if(nbVictoires >= 0){
				this.nbVictoires = nbVictoires;
				if(nbPionsMange >= 0){
					this.nbPionsMange = nbPionsMange;
					if(tempsDeJeuTotal >= 0){
						this.tempsDeJeuTotal = tempsDeJeuTotal;
						if(nbParties  >= 0){
							this.nbParties = nbParties;
						}else{System.out.println("Nombre de parties invalid");}
					}else{System.out.println("Temps de jeu invalid");}
				}else{System.out.println("Nombre de pions mange invalid");}
			}else{System.out.println("Nombre de victoires invalid");}
		}else{System.out.println("Joueur invalid");}
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
	 * Met a jour les statistiques du joueur
	 * @param index - index de l'attribut a modifier
	 * @param valeur - la valeur a ajouter
	 */
	 public void updateStat(int nbParties, int nbVictoires, int nbPionsMange, double tempsDeJeuTotal){
         if(nbVictoires >= 0){
             this.nbVictoires = nbVictoires;
             if(nbPionsMange >= 0){
                 this.nbPionsMange = nbPionsMange;
                 if(tempsDeJeuTotal >= 0){
                     this.tempsDeJeuTotal = tempsDeJeuTotal;
                     if(nbParties  >= 0){
                         this.nbParties = nbParties;
                     }else{System.out.println("(Update) Nombre de parties invalid");}
                 }else{System.out.println("(Update) Temps de jeu invalid");}
             }else{System.out.println("(Update) Nombre de pions mange invalid");}
         }else{System.out.println("(Update) Nombre de victoires invalid");}
     }

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
