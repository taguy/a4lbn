package controller;
/**
 * Classe de l'Intelligence Artificielle qui joueras automatiquement
 * @author J. BAUTISTA
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 * @author R. PRECIGOUT
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
	 */
	public int[][] auto(){
		ArrayList<int[]> listePosDest = new ArrayList<int[]>();
		int[][] ret = new int[2][2];
		int[] posAct = this.posAct();
		int[] nouvelleposDest = new int[2];

		ret[0][0] = posAct[0];
		ret[0][1] = posAct[1];

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((posAct[0]+i >= 0 && posAct[1]+j >= 0) && (posAct[0]+i <this.getDamier().length && posAct[1]+j < this.getDamier()[0].length)) {
					nouvelleposDest[0] = posAct[0]+i;
					nouvelleposDest[1] = posAct[1]+j;
				}

				if (this.verifPosDestination(posAct[0], posAct[1], nouvelleposDest[0], nouvelleposDest[1])) {
					listePosDest.add(nouvelleposDest);
				}
			}
		}

		int i = (int) (Math.random() * listePosDest.size());
		posAct = listePosDest.get(i);

		ret[1][0] = posAct[0];
		ret[1][1] = posAct[1];

		return ret;
	}


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
