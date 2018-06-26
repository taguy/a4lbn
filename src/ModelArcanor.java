package model;
import controller.*;
import java.util.ArrayList;
/**
* Classe ModelArcanor
* Classe permettant la récupération de la base d'information nécéssaire aux controllers.
*/
public class ModelArcanor{
    ArrayList<Partie> lesParties;
    ArrayList<Joueur> lesJoueurs;
    ArrayList<Statistiques> lesStats;
    String cheminCouleur = "../fichiers/ihm/Pions/";
    String cheminLogo = "../fichiers/ihm/logo12.png";
    String cheminAnimation = "../fichiers/ihm/Animation/";
    String cheminSkin = "../fichiers/ihm/PionsSkin/";
    String cheminDonnees = "../fichiers/ihm/";
    /**
    * Constructeur de la classe
    */
    public ModelArcanor(){
        this.lesParties = Lanceur.getLesParties();
        this.lesJoueurs = Lanceur.getLesJoueurs();
        this.lesStats = Lanceur.getLesStats();
    }

    /**
    * Retourne le chemin d'accès des images pour l'animation
    * @return this.cheminAnimation : le chemin d'accès des images pour l'animation (String)
    */
    public String getCheminAnimation(){
        return this.cheminAnimation;
    }
    /**
    * Retourne le chemin d'accès des images pour les couleurs des pions
    * @return this.cheminCouleur : le chemin d'accès des images pour les couleurs des pions (String)
    */
    public String getCheminCouleur(){
        return this.cheminCouleur;
    }
    /**
    * Retourne le chemin d'accès du logo
    * @return this.cheminLogo : le chemin d'accès du logo (String)
    */
    public String getCheminLogo(){
        return this.cheminLogo;
    }
    /**
    * Retourne le chemin d'accès au données
    * @return this.cheminDonnees : le chemin d'accès au données (String)
    */
    public String getCheminDonnees(){
        return this.cheminDonnees;
    }
    /**
    * Retourne la liste des parties
    * @return this.lesParties : la liste des parties (ArrayList)
    */
    public ArrayList<Partie> getLesParties(){
        return this.lesParties;
    }
    /**
    * Retourne la liste des joueurs
    * @return this.lesJoueurs : la liste des joueurs (ArrayList)
    */
    public ArrayList<Joueur> getLesJoueurs(){
        return this.lesJoueurs;
    }
    /**
    * Retourne le chemin d'accès des differents skins de pion
    * @return this.cheminSkin : le chemin d'accès des differents skins de pion (String)
    */
    public String getCheminSkin(){
        return this.cheminSkin;
    }
    /**
     * Retourne le mode de visualisation
     * @return graphique : le mode de visualisation
     */
    public static boolean getGraphique(){
        return model.Lanceur.getGraphique();
    }
    /**
     * Retourne une arrayList de statistiques
     * @return model.Lanceur.geLestStats() : la list de stats
     */
    public static ArrayList<Statistiques> getLesStats(){
        return model.Lanceur.getLesStats();
    }

}
