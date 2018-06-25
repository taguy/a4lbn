package controller;
import java.awt.event.*;
import view.*;
import model.*;
/**
* Classe EcouteurNouvellePartie
* interactions avec les JObjets de la classe PanelNouvellePartie
*/
public class EcouteurNouvellePartie implements ActionListener{
    PanelNouvellePartie p;
    int selecteur;
    /**
    * Constructeur de la classe
    * @param p : le panel appelant
    * @param selecteur : une valeur de type int permettant la selection d'une action
    */
    public EcouteurNouvellePartie(PanelNouvellePartie p, int selecteur){
        this.p = p;
        this.selecteur = selecteur;
    }
    /**
    * Réalise differentes actions en fonctions du selecteur
    * @param e : l'action crée par l'interaction avec un JObjet
    */
    public void actionPerformed(ActionEvent e){
        if(this.selecteur == 0){
            this.p.miseAjourNouveauJoueurA();
        }
        if(this.selecteur == 1){
            this.p.miseAjourNouveauJoueurB();
        }
        if(this.selecteur == 2){
            this.p.valider();
        }
        if(this.selecteur == 3){
            this.p.retour();
        }
        if(this.selecteur == 4){
            this.p.quitter();
        }

    }
}
