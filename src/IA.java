package controller;
/**
 * Classe de l'Intelligence Artificielle qui joueras automatiquement
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
import java.util.*;
public class IA extends Joueur{

	/**
	 * Constructeur de la classe IA permettant de créer un bot sachant jouer (a peu près)
	 * @param nom - le nom de l'IA
	 */
	public IA(String nom){
		super(nom);
	}

	/**
	 * Methode permettant à un joueur IA de jouer
	 * @return La position actuelle choisie et la position destination
	 */
	public int[][] auto(){
		ArrayList<int[]> listePosDest = new ArrayList<int[]>();
		int[][] ret = new int[2][2];
		int[] posAct = this.posAct();
		int[] tmp = new int[2];
		int[][] nouvelleposDest = new int[8][2];

		ret[0][0] = posAct[0];
		ret[0][1] = posAct[1];

		nouvelleposDest[0][0] = posAct[0];
		nouvelleposDest[0][1] = posAct[1]-1;

		nouvelleposDest[1][0] = posAct[0];
		nouvelleposDest[1][1] = posAct[1]+1;

		nouvelleposDest[2][0] = posAct[0]-1;
		nouvelleposDest[2][1] = posAct[1];

		nouvelleposDest[3][0] = posAct[0]+1;
		nouvelleposDest[3][1] = posAct[1];

		nouvelleposDest[4][0] = posAct[0]+1;
		nouvelleposDest[4][1] = posAct[1]-1;

		nouvelleposDest[5][0] = posAct[0]-1;
		nouvelleposDest[5][1] = posAct[1]+1;

		nouvelleposDest[6][0] = posAct[0]-1;
		nouvelleposDest[6][1] = posAct[1]-1;

		nouvelleposDest[7][0] = posAct[0]+1;
		nouvelleposDest[7][1] = posAct[1]+1;

		for (int i = 0; i < 8; i++) {
			if (this.verifPosDestination(posAct[0], posAct[1], nouvelleposDest[i][0], nouvelleposDest[i][1])) {
				if ((nouvelleposDest[i][0] >= 0 && nouvelleposDest[i][1] >= 0) && (nouvelleposDest[i][0] <this.getDamier().length && nouvelleposDest[i][1] < this.getDamier()[0].length)) {
					tmp[0] = nouvelleposDest[i][0];
					tmp[1] = nouvelleposDest[i][1];
//System.out.println("PosAct" + "tmp " + tmp[0] + " " + tmp[1]);
					listePosDest.add(tmp);
				}
			}
		}

		int i = (int) (Math.random() * listePosDest.size());

		posAct = listePosDest.get(i);

		ret[1][0] = posAct[0];
		ret[1][1] = posAct[1];

		return ret;
	}

	/**
	 * Retourne les positions des pions de l'IA
	 * @return Les positions des pions de l'IA
	 */
	private int[] posAct() {
		Pion[][] damier = this.getDamier();

		ArrayList<int[]> listePosAct = new ArrayList<int[]>();
		int[] posAct = new int[2];
		int nb = 0;

		int i = 0;
		while (i < 8 && nb < 12) {

			int j = 0;
			while (j < 7 && nb < 12) {

				if (damier[i][j] != null && damier[i][j].getJoueur() == this) {
					posAct[0] = i;
					posAct[1] = j;
System.out.println("PosAct" + " " +posAct[0] + " " + posAct[1]);
					listePosAct.add(posAct);
					if (damier[i][j].getContenu() != null && damier[i][j].getContenu().getJoueur() == this) {
						if (damier[i][j].getContenu().getContenu() != null && damier[i][j].getContenu().getContenu().getJoueur() == this) {
							if (damier[i][j].getContenu().getContenu().getContenu() != null && damier[i][j].getContenu().getContenu().getContenu().getJoueur() == this) {

								nb++;
							}
							nb++;
						}
						nb++;
					}
					nb++;
				}
				j++;
			}
			i++;
		}

		i = (int) (Math.random() * listePosAct.size());

		posAct = listePosAct.get(i);

		return posAct;
	}
}
