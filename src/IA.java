package projetZ;
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

		ret[0] = posAct;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				nouvelleposDest[0] = posAct[0]+i;
				nouvelleposDest[1] = posAct[1]+j;
				if (this.verifDeplacement(posAct, nouvelleposDest, new Random().nextBoolean()) == 0) {
					listePosDest.add(nouvelleposDest);
				}
			}
		}

		int i = (int) (Math.random() * listePosDest.size());
		posAct = listePosDest.get(i);

		ret[1] = posAct;

		return ret;
	}


	private int[] posAct() {
		ArrayList<int[]> listePosAct = new ArrayList<int[]>();
		int[] posAct = new int[2];
		int nb = 0;

		int i = 0;
		while (i < 8 && nb < 12) {

			int j = 0;
			while (j < 7 && nb < 12) {

				if (this.getDamier()[i][j] != null && this.getDamier()[i][j].getJoueur() == this) {
					posAct[0] = i;
					posAct[1] = j;

					listePosAct.add(posAct);
					if (this.getDamier()[i][j].getContenu().getJoueur() == this) {
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
