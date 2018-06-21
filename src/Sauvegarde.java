package projetZ;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Sauvegarde {
    private static final String CHEMINJOUEURS = "../files/lesJoueurs.txt";
    private static final String CHEMINPARTIES = "../files/lesParties.txt";

    /**
 	 * Extrait les infos du fichier de sauvegarde parties
 	 * @return la hashMap contenant tous les joueurs enregistrés
 	 */
 	private static ArrayList<String> extractPartie() {
 		ArrayList<String> ret = new ArrayList<String>(0);

 		try {
 			BufferedReader in = new BufferedReader(new FileReader (CHEMINPARTIES));
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
	 * initialise les parties
	 * @param map - la hashMap de toutes les parties
	 */
	public static void initializeLesParties(ArrayList<Partie> lesParties){
        ArrayList<String> liste = extractPartie();
		StringTokenizer stk;

		for (int i = 0; i < liste.size(); i++) {
			stk = new StringTokenizer(liste.get(i));
			//Joeur A, jB,nom, tours, scoreA score B

			lesParties.add(new Partie(new Joueur(stk.nextToken()), new Joueur(stk.nextToken()), stk.nextToken(), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
			lesParties.get(i).getJoueurA().setDamier(lesParties.get(i).getDamier());
			lesParties.get(i).getJoueurB().setDamier(lesParties.get(i).getDamier());
		}

	}


 	/**
 	 * initialise les joueurs qui participeront à la partie en cours
 	 * @param map - la hashMap de tous les joueurs
 	 */
 	public static void initializeLesJoueurs(ArrayList<Joueur> liste){
 		try {
 			BufferedReader in = new BufferedReader(new FileReader (CHEMINJOUEURS));
 			String s = in.readLine();

 			while(s != null) {
 				liste.add(new Joueur(s));
 				s = in.readLine();
 			}
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}

	 /**
      * Sauvegarder la Partie
      */
     public static void sauveParties(ArrayList<Partie> liste){
         try {
             FileOutputStream fos = new FileOutputStream(CHEMINPARTIES);
             fos.close();

             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(CHEMINPARTIES)));

			 for (Partie p : liste) {
				 out.println(p.getJoueurA().getNom() + " " + p.getJoueurB().getNom() + " " + p.getNom() + " " + p.getTours() + " " + p.getScoreA() + " " + p.getScoreB());
			 }

             out.close();
         } catch(IOException e) {
             e.printStackTrace();
         }
      }

	  /**
	   * Sauvegarder la Partie
	   */
	  public static void sauveJoueurs(ArrayList<Joueur> liste){
		  try {
			  FileOutputStream fos = new FileOutputStream(CHEMINJOUEURS);
			  fos.close();

			  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(CHEMINJOUEURS)));

			for (Joueur j : liste) {
				out.println(j.getNom());
			}

			  out.close();
		  } catch(IOException e) {
			  e.printStackTrace();
		  }
	   }
}
