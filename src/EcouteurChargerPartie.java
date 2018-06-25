package controller;
import java.awt.event.*;
import view.*;
import model.*;
/**
* Classe EcouteurChargerPartie
* interactions avec les JObjets de la classe PanelChargerPartie
*/
public class EcouteurChargerPartie implements ActionListener{
    PanelChargerPartie p;
    int selecteur;
    /**
    * Constructeur de la classe
    * @param p : le panel appelant
    * @param selecteur : une valeur de type int permettant la selection d'une action
    */
    public EcouteurChargerPartie(PanelChargerPartie p, int selecteur){
        this.p = p;
        this.selecteur = selecteur;
    }
    /**
    * Réalise differentes actions en fonctions du selecteur
    * @param e : l'action crée par l'interaction avec un JObjet
    */
    public void actionPerformed(ActionEvent e){
        if(this.selecteur == 0){
            this.p.quitter();
        }
        if(this.selecteur == 1){
            this.p.retour();
        }
        if(this.selecteur == 2){
            this.p.valider();
        }
    }
}
