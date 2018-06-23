package model;
import controller.*;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Sauvegarde {
    private static final String INDEX = "../files/";
    private static final String CHEMINJOUEURS = "../files/lesJoueurs.txt";
    private static final String CHEMINPARTIES = "../files/lesParties/";
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
     		} catch (Exception e) {
     			e.printStackTrace();
     		}
     		return ret;
    }


    /**
	 * initialise les parties
	 * @param map - la hashMap de toutes les parties
	 */
	public static void initLesParties(ArrayList<Partie> lesParties){
        ArrayList<String> liste = extraire(INDEX+"lesParties.txt");
        ObjectInputStream in = null;
        Partie p = null;

        for (String s : liste) {
            try {
                in = new ObjectInputStream (new BufferedInputStream (new FileInputStream (s)));
                p = (Partie) in.readObject();
                in.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (p != null) {
                    lesParties.add(p);
                }
            }
        }
	}

    /**
	 * initialise les joueurs
	 * @param map - la hashMap de toutes les parties
	 */
	public static void initLesJoueurs(ArrayList<Joueur> lesJoueurs){
        ArrayList<String> liste = extraire(INDEX+"lesJoueurs.txt");
        ObjectInputStream in = null;
        Joueur j = null;

        for (String s : liste) {
            try {
                in = new ObjectInputStream (new BufferedInputStream (new FileInputStream (s)));
                j = (Joueur) in.readObject();
                in.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (j != null) {
                    lesJoueurs.add(j);
                }
            }
        }
	}

 	/**
 	 * initialise les joueurs qui participeront à la partie en cours
 	 * @param map - la hashMap de tous les joueurs
 	 */
 	public static void initLesStats(ArrayList<Statistiques> lesStats){
        ArrayList<String> liste = extraire(INDEX+"lesStats.txt");
        ObjectInputStream in = null;
        Statistiques s = null;

        for (String str : liste) {
            try {
                in = new ObjectInputStream (new BufferedInputStream (new FileInputStream (str)));
                s = (Statistiques) in.readObject();
                in.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (s != null) {
                    lesStats.add(s);
                }
            }
        }
 	}

	 /**
      * Sauvegarder la Partie
      */
     public static void sauveLesParties(ArrayList<Partie> lesParties){
         try {
             String s = INDEX + "lesParties.txt";

             ObjectOutputStream objOut;
             FileOutputStream fos = new FileOutputStream(s);
             fos.close();

             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(s)));

             for (Partie p : lesParties) {
                 s = CHEMINPARTIES + p.getNom() + ".txt"; //Path du fichier
                 pw.println(s); //On écrit dans l'index
                 objOut = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (s))); //On écrit dans le path
                 objOut.writeObject(p);
                 objOut.close();
            }
            pw.close();
     	} catch(Exception e){
        	e.printStackTrace();
        }
      }


      public static void sauveLesPions(ArrayList<Partie> liste){
          try {
              FileOutputStream fos = new FileOutputStream(CHEMINPIONS);
              fos.close();

              PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(CHEMINPIONS)));

            for (Partie p : liste) {
                out.println(p.getNom() + " " + p.getJoueurA().getNom() + " ");
                //for (int[] pos : p.getJoueurA().posAct()) {
                    //out.print(pos[0] + " " + pos[1] + " " + p.getDamier()[pos[0]][pos[1]] + " ");
                //}
            }

              out.close();
          } catch(Exception e) {
              e.printStackTrace();
          }
       }

	  /**
	   * Sauvegarder la Partie
	   */
	  public static void sauveLesJoueurs(ArrayList<Joueur> lesJoueurs){
          try {
              String s = INDEX + "lesJoueurs.txt";

              ObjectOutputStream objOut;
              FileOutputStream fos = new FileOutputStream(s);
              fos.close();

              PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(s)));

              for (Joueur j : lesJoueurs) {
                  s = CHEMINJOUEURS + j.getNom() + ".txt"; //Path du fichier
                  pw.println(s); //On écrit dans l'index
                  objOut = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (s))); //On écrit dans le path
                  objOut.writeObject(j);
                  objOut.close();
             }
             pw.close();
         } catch(Exception e){
             e.printStackTrace();
         }
     }

       /**
 	   * Sauvegarder les stats
 	   */
 	  public static void sauveLesStats(ArrayList<Statistiques> lesStats){
          try {
              String str = INDEX + "lesStats.txt";

              ObjectOutputStream objOut;
              FileOutputStream fos = new FileOutputStream(str);
              fos.close();

              PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(str)));

              for (Statistiques s : lesStats) {
                  str = CHEMINSTATS + s.getJoueur().getNom() + ".txt"; //Path du fichier
                  pw.println(str); //On écrit dans l'index
                  objOut = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (str))); //On écrit dans le path
                  objOut.writeObject(s);
                  objOut.close();
             }
             pw.close();
         } catch(Exception e){
             e.printStackTrace();
         }
 	   }
}
