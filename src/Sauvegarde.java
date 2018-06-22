package model;
import controller.*;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Sauvegarde {
    private static final String CHEMINJOUEURS = "../files/lesJoueurs.txt";
    private static final String CHEMINPARTIES = "../files/lesParties.txt";
    private static final String CHEMINSTATS = "../files/lesStats.txt";
    private static final String CHEMINPIONS = "../files/lesPions.txt";


    /**
 	 * Extrait les infos du fichier de sauvegarde parties
 	 * @return la hashMap contenant tous les joueurs enregistrés
 	 */
 	private static ArrayList<String> extraire(String fileName) {
 		ArrayList<String> ret = new ArrayList<String>(0);

 		try {
 			BufferedReader in = new BufferedReader(new FileReader (fileName));
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
	public static void initLesParties(ArrayList<Partie> lesParties){
        ArrayList<String> liste = extraire(CHEMINPARTIES);
		StringTokenizer stk;

		for (int i = 0; i < liste.size(); i++) {
			stk = new StringTokenizer(liste.get(i));
			//Joeur A, jB,nom, tours, scoreA score B

			lesParties.add(new Partie(new Joueur(stk.nextToken()), new Joueur(stk.nextToken()), stk.nextToken(), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
			lesParties.get(i).getJoueurA().setDamier(lesParties.get(i).getDamier());
			lesParties.get(i).getJoueurB().setDamier(lesParties.get(i).getDamier());
		}

        initLesPions(lesParties);
	}

    private static void initLesPions(ArrayList<Partie> lesParties) {
        ArrayList<String> liste = extraire(CHEMINPIONS);


    }

    /**
	 * initialise les parties
	 * @param map - la hashMap de toutes les parties
	 */
	public static void initLesJoueurs(ArrayList<Joueur> joueurs){
        ArrayList<String> liste = extraire(CHEMINJOUEURS);
		StringTokenizer stk;

		for (int i = 0; i < liste.size(); i++) {
			joueurs.add(new Joueur(liste.get(i)));
		}

	}

 	/**
 	 * initialise les joueurs qui participeront à la partie en cours
 	 * @param map - la hashMap de tous les joueurs
 	 */
 	public static void initLesStats(ArrayList<Statistiques> stats, ArrayList<Joueur> joueurs){
        ArrayList<String> liste = extraire(CHEMINSTATS);
		StringTokenizer stk;
        String tmp;
        int j;
        boolean check;
        Joueur joueur;

		for (int i = 0; i < joueurs.size(); i++) {
			stk = new StringTokenizer(liste.get(i));

            tmp = stk.nextToken();

            check = false;
            j = -1;
            do {
                j++;

                if (joueurs.get(j).getNom().equals(tmp)) {
                    check = true;
                }
            } while (j< joueurs.size() && !check);

            if (check) {
                stats.add(new Statistiques(joueurs.get(j), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Double.parseDouble(stk.nextToken())));
            }
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

       /**
 	   * Sauvegarder les stats
 	   */
 	  public static void sauveStats(ArrayList<Statistiques> liste){
 		  try {
              FileOutputStream fos = new FileOutputStream(CHEMINSTATS);
 			  fos.close();

 			  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(CHEMINSTATS)));

 	          for (Statistiques s : liste) {
 		           out.println(s.getJoueur().getNom() + " " + s.getNbParties() + " " + s.getNbVictoires() + " " + s.getNbPionsMange() + " " + s.getNbTempsDeJeuTotal());
 			   }

 			  out.close();
 		  } catch(IOException e) {
 			  e.printStackTrace();
 		  }
 	   }
}
