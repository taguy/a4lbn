package model;
import controller.*;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

/**
 * Classe servant à la sauvegarde des joueurs, des parties et des statistiques
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
public class Sauvegarde {
    /** Répertoire contenant les index (qui eux-même) contiennent les chemins menant aux fichiers de sauvegarde */
    private static final String INDEX = "../files/";

    /** Répertoire contenant les sauvegardes de joueurs */
    private static final String CHEMINJOUEURS = "../files/lesJoueurs/";

    /** Répertoire contenant les sauvegardes de parties */
    private static final String CHEMINPARTIES = "../files/lesParties/";

    /** Répertoire contenant les sauvegardes de statistiques */
    private static final String CHEMINSTATS = "../files/lesStats/";


    /**
     * Extrait les lignes contenues dans un fichier
     * @return Un ArrayList de String contenant les lignes
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
	 * Initialise les joueurs
 	 * @param lesJoueurs L'ArrayList contenant les joueurs
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
 	 * Initialise les statistiques
 	 * @param lesStats L'ArrayList contenant les statistiques
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
	 * Sauvegarde les parties
	 * @param lesParties L'ArrayList contenant les parties
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


     /**
  	  * Sauvegarde les joueurs
   	  * @param lesJoueurs L'ArrayList contenant les joueurs
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
       * Sauvegarde les statistiques
       * @param lesStats L'ArrayList contenant les statistiques
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
