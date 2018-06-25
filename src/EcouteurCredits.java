package controller;
import java.awt.event.*;
import view.*;
import model.*;
/**
* Classe EcouteurCredits
* interactions avec les JObjets de la classe PanelCredits
*/
public class EcouteurCredits implements ActionListener{
    PanelCredits p;
    int selecteur;
    /**
    * Constructeur de la classe
    * @param p : le panel appelant
    * @param selecteur : une valeur de type int permettant la selection d'une action
    */
    public EcouteurCredits(PanelCredits p, int selecteur){
        this.p = p;
        this.selecteur = selecteur;
    }
    /**
    * Réalise differentes actions en fonctions du selecteur
    * @param e : l'action crée par l'interaction avec un JObjet
    */
    public void actionPerformed(ActionEvent e){
        if(this.selecteur == 1){
            this.p.quitter();
        }
        if(this.selecteur == 2){
            this.p.retour();
        }
    }
}
