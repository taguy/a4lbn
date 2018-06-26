package controller;
import view.*;
import model.*;
import java.util.ArrayList;
/**
* Classe ControleurGlobal
* Cette classe permet de récuperer les information nécéssaire pour les view
*/
public class ControleurGlobal{
        ArrayList<Partie> lesParties;
        ArrayList<Joueur> lesJoueurs;
        ArrayList<Statistiques> lesStats;
        ModelArcanor model;
        String cheminLogo;
        String cheminCouleur;
        String cheminAnimation;
        String cheminSkin;
        String cheminDonnees;
        /**
        * Constructeur de la Classe
        */
        public ControleurGlobal(){
            this.model = new ModelArcanor();
            this.lesJoueurs = model.getLesJoueurs();
            this.lesParties = model.getLesParties();
            this.lesStats = model.getLesStats();
            this.cheminLogo = model.getCheminLogo();
            this.cheminCouleur = model.getCheminCouleur();
            this.cheminAnimation = model.getCheminAnimation();
            this.cheminSkin = model.getCheminSkin();
            this.cheminDonnees = model.getCheminDonnees();
        }
        /**
        * Retourne la liste des parties
        * @return model.getLesParties() : la liste des parties (ArrayList)
        */
        public ArrayList<Partie> getLesParties(){
            return model.getLesParties();
        }
        /**
        * Retourne la liste des joueurs
        * @return model.getLesJoueurs() : la liste des joueurs
        */
        public ArrayList<Joueur> getLesJoueurs(){
            return model.getLesJoueurs();
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
        * Retourne le chemin d'accès des differents skins de pion
        * @return this.cheminSkin : le chemin d'accès des differents skins de pion (String)
        */
        public String getCheminSkin(){
            return this.cheminSkin;
        }
        /**
        * Retourne le chemin d'accès au données
        * @return this.cheminDonnees : le chemin d'accès au données (String)
        */
        public String getCheminDonnees(){
            return this.cheminDonnees;
        }
        /**
        * Retourne la liste des noms des joueurs
        * @return ret : la liste des noms des joueurs (ArrayList)
        */
        public String[] getNomJoueurs(){
            String[] ret = null;
            ret = new String[this.lesJoueurs.size()];
            for(int i = 0; i < this.lesJoueurs.size();i++){
                    ret[i] = this.lesJoueurs.get(i).getNom();
            }
            return ret;
        }
        /**
         * Retourne une arrayList de statistiques
         * @return this.lesStats : la liste de stats
         */
        public  ArrayList<Statistiques>getLesStats(){
            return this.lesStats;
        }
        /**
         * Retourne le mode de visualisation
         * @return ModelArcanor.getGraphique() : le mode de visualisation
         */
        public static boolean getGraphique(){
            return ModelArcanor.getGraphique();
        }
        /**
        * Retourne la liste des noms des parties
        * @return ret : la liste des noms des parties (ArrayList)
        */
        public String[] getNomParties(){
            String[] ret = new String[this.lesParties.size()];
            for(int i = 0; i < this.lesParties.size();i++){
                ret[i] = this.lesParties.get(i).getNom();
            }
            return ret;
        }
        /**
        * Modifie le chemin d'accès des couleurs des pions
        * @param i : valeur en fonction du chemin
        */
        public void modifierCheminCouleur(int i){
            if(i == 0){
                this.cheminCouleur = "../fichiers/ihm/Pions/";
            }
            if(i == 1){
                this.cheminCouleur = "../fichiers/ihm/skin1/";
            }
            if(i == 2){
                this.cheminCouleur = "../fichiers/ihm/skin2/";
            }
        }

}
