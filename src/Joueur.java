package projetZ;

import java.io.FileWriter; //importation pour la méthode savejoueur
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import utili.Utilitaire;
/**
 * La classe joueur modelise un joueur
 * d'Aracanor avec son nom et son score
 * @author Brice Laigo
 * @author Lorenzo Gerardi
 * @author Nguyen Nguyen
 * @version 1.0
 */
public class Joueur{
	/** Nom du joueur */
	private String nom;
	/** Damier sur lequel peut agir le joueur */
	private Pion[][] damier;

	private final String SAUVJOUEURS = "../files/lesJoueurs.txt";

	/**
	 * Constructeur de la classe joueur qui initialise
	 * ses attributs pour créer un objet joueur
	 * @param nom - le nom du joueur (ou pseudo)
	 */
	public Joueur(String nom){
		if(nom != null && nom != ""){
			this.nom = nom;
		}
		else{
			System.out.println("Joueur(String nom) : Pas de nom attibué");
		}
	}
	/**
	 * Getter du nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom(){
		return this.nom;
	}
	/**
	 * Sauvegarde les attributs du joueurs pour qu'ils
	 * puissent être réutilisés plus tard
	 */
	void sauveJoueur(){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(SAUVJOUEURS))); //écriture du nom du joueur dans un fichier txt
			out.println(this.nom);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * Initialise le damier dans lequel le joueur peut agir
	 * @param damier - le damier dans lequel le joueur peut agir
	 */
	void setDamier(Pion[][] damier){
		if(damier != null){
			this.damier = damier;
		}
		else{
			System.out.println(" setDamier() : Le damier est null");
		}
	}
	/**
	 * Retourne le damier lié au joueur
	 *@return le damier
	 */
	public Pion[][] getDamier(){
		return this.damier;
	}

	/**
	 * vérifie l'etat du damier pour informer la methodes deplacer
	 * @param posAct - tableau correspondant à la position actuelle du pion
	 * @param posDest - tableau correspondant à la position de destination du pion
	 * @return un numero d'erreur si erreur il y a, 0 sinon
	 */
	 int verifDeplacement(int[] valPosAct, int [] valPosDest) {
		 return this.verifDeplacement(valPosAct, valPosDest, false);
	 }

	/**
	 * vérifie l'etat du damier pour informer la methodes deplacer
	 * @param posAct - tableau correspondant à la position actuelle du pion
	 * @param posDest - tableau correspondant à la position de destination du pion
	 * @return un numero d'erreur si erreur il y a, 0 sinon
	 */
	 int verifDeplacement(int[] valPosAct, int [] valPosDest, boolean liberer){
		 int posActX = valPosAct[0];
		 int posActY = valPosAct[1];
		 int posDestX = valPosDest[0];
		 int posDestY = valPosDest[1];
		 int posDestZ = 1; //decapsuler ou glisser à choisir
		int codeRetour = 0;

		//		codeRetour = 0 : le déplacemment est réalisable
		//		codeRetour = 1 : PosAct = posDest
		//		codeRetour = 2 : le pion n'appartient pas au joueur
		//		codeRetour = 3 : le déplacemment ne respecte pas les règles
		// 		codeRetour = 4 : le pion ciblée est a la posfin
		// 		codeRetour = 5 : le pion ciblée est au même joueur que le pion actuelle

		if(posActX == posDestX && posActY == posDestY){
				System.out.println("move() : La posAct est la même que la posDest <!>");
				codeRetour = 1;
		}else if(this.damier[posActX][posActY] == null || this.damier[posActX][posActY].getJoueur() != this){
				System.out.println("move() : la posAct n'a pas de pion  ou  le pion n'appartient pas au joueur <!>");
				codeRetour = 2;
		}else if(!verifPosDestination(posActX, posActY , posDestX, posDestY)){
			System.out.println("move(): la position de destination n est pas réalisable <!>");
			codeRetour = 3;
		}else if(this.damier[posDestX][posDestY] != null){
			if(this.damier[posDestX][posDestY].getPosFin() == true){
				System.out.println("Le pion de la position de destination n'est plus actif <!>");
				codeRetour = 4;
			}
			if(this.damier[posActX][posActY].getJoueur() == this.damier[posDestX][posDestY].getJoueur()){
				System.out.println("Il est interdit de manger ses pions <!>");
				codeRetour = 5;
			}
		}else if(this.damier[posActX][posActY].getPosFin() == true){
			System.out.println("Le pion de la position actuelle n'est plus actif <!>");
			codeRetour = 4;

		}
		Pion dest = this.damier[posDestX][posDestY];
		if(codeRetour == 0){
			if(dest == null && this.damier[posActX][posActY].getContenu() !=  null){
				String message = "\nTapez '1' si vous liberer le contenu, Tapez '2' si vous voulez glisser\n puis tapez Entree\n";
				posDestZ = Integer.parseInt(Utilitaire.reponseUtilisateur(message, 1, 2, 1));
			}

			if(posDestZ == 1 || liberer){
				//methode brice
				if(dest == null){
					//System.out.println("passage dans liberer");
					this.liberer(valPosAct,valPosDest);
					codeRetour = 0;
				}
				else{
				//	System.out.println("passage dans manger");
					if(this.manger(valPosAct,valPosDest) == 0){
						codeRetour = 0;
					}
					else{
						codeRetour = -1;
					}
				}
			}
			else{
				if(dest == null){
					//System.out.println("passage dans glisser");
					this.glisser(valPosAct,valPosDest);
					codeRetour = 0;
				}
				else{
					System.out.println("Glissement impossible <!>");
					codeRetour = 3;
				}
			}
		}
		return codeRetour;
	}
	/**
	* vérifie que le déplacemment respecte les règles du jeu arcanor
	* @param posActX : la pos actuelle du pion en X
	* @param posDestY : la pos actuelle du pion en Y
	* @param posActcX : la pos destination à vérifier en X
	* @param posDestY : la pos destination à vérifier en Y
	* @return ret : vrai si la position est bonne ou faux si la position n'est pas bonne
	*/
	private boolean verifPosDestination(int posActX, int posActY , int posDestX, int posDestY){
		boolean ret = false;
		if((posDestX == posActX && posDestY == posActY-1)||(posDestX == posActX && posDestY == posActY+1) // Bas Haut
		||(posDestX == posActX-1 && posDestY == posActY)||(posDestX == posActX+1 && posDestY == posActY) //Gauche droite
		||(posDestX == posActX+1 && posDestY == posActY-1)||(posDestX == posActX-1 && posDestY == posActY+1) //Diagonale haut gauche , bas droite
		||(posDestX == posActX-1 && posDestY == posActY-1)||(posDestX == posActX+1 && posDestY == posActY+1)){ //Diagonale haut droite , bas gauche
			ret = true;
		}
		return ret;
	}
	/**
	 * Bouge le pion de la position où il se situe vers la position souhaitée
	 * en laissant son contenu sur place si le cas se présente
	 * @param posAct - tableau correspondant à la position actuelle du pion
	 * @param posDest - tableau correspondant à la position de destination du pion
	 * @return un numero d'erreur si erreur il y a, 0 sinon
	 */
	int manger(int[] valPosAct, int [] valPosDest){ //code a verifier

		int posActX = valPosAct[0];
		int posActY = valPosAct[1];
		int posDestX = valPosDest[0];
		int posDestY = valPosDest[1];
		int codeRetour = -1;
		// codeRetour = -1 : défaut
		// codeRetour = 0 : focntion manger réuissie

		if(this.damier[posActX][posActY].getTaille() +1 == this.damier[posDestX][posDestY].getTaille()
		&& this.damier[posActX][posActY].getContenu() == null){

			this.damier[posActX][posActY].setContenu(this.damier[posDestX][posDestY]); //mise du pion de posDest en contenu du pion à deplacer
			this.damier[posDestX][posDestY] = this.damier[posActX][posActY]; //mise du pion à deplacer dans la pos destination
			this.damier[posActX][posActY] =  null; // pos actuelle à null
			codeRetour = 0;

		}
		else if(this.damier[posActX][posActY].getTaille() +1 == this.damier[posDestX][posDestY].getTaille()
				&& this.damier[posActX][posActY].getContenu() != null){

					Pion tmp = this.damier[posActX][posActY].getContenu(); //Sauvegarde du contenu
					this.damier[posActX][posActY].setContenu(this.damier[posDestX][posDestY]); //mise du pion de posDest en contenu du pion à deplacer
					this.damier[posDestX][posDestY] = this.damier[posActX][posActY]; //mise du pion à deplacer dans la pos destination
					this.damier[posActX][posActY] =  tmp; // mise du pion ancienement dans le pion qui été a deplacer dans la posAct
					codeRetour = 0;

		}
		else if(this.damier[posActX][posActY].getTaille() +1 != this.damier[posDestX][posDestY].getTaille()){
					System.out.println("le pion a la posDest ne peut être mangé car sa taille est trop petite ou trop grande");

		}
		return codeRetour;
	}
	/**
     * Libere le pion contenu dedans
     * @param pos la position la ou le pion contenu est libere
     */
    private void liberer(int[] valPosAct, int [] valPosDest){
        int posActX = valPosAct[0];
        int posActY = valPosAct[1];
        int posDestX = valPosDest[0];
        int posDestY = valPosDest[1];


		if(this.damier[posActX][posActY].getContenu() != null){
			this.damier[posDestX][posDestY] = this.damier[posActX][posActY];
        	this.damier[posActX][posActY] = this.damier[posDestX][posDestY].getContenu();
			this.damier[posDestX][posDestY].setContenu(null);
		}
		else{
			this.damier[posDestX][posDestY] = this.damier[posActX][posActY];
			this.damier[posActX][posActY] = null;
		}
    }
	/**
	* Glisse un pion a une position choisi
	*@param valPosAct : tableau correspondant à la pos actuelle choisi
	*@param valPosDest : tableau correspondant à la pos de destionation choisi
	*/
	private void glisser(int[] valPosAct, int [] valPosDest){
		int posActX = valPosAct[0];
		int posActY = valPosAct[1];
		int posDestX = valPosDest[0];
		int posDestY = valPosDest[1];

		Pion tmp = this.damier[posActX][posActY]; //Sauvegarde du pion à glisser dans une variable
		this.damier[posActX][posActY] = null; //position actuelle a nulle
		this.damier[posDestX][posDestY] = tmp;
	}

	public int[][] auto(){
		int[][] test = null;
		return test;
	}
}
