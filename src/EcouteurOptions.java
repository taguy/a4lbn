package controller;
import java.awt.event.*;
import view.*;
import model.*;
/**
* Classe EcouteurOptions
* interactions avec les JObjets de la classe PanelOptions
*/
public class EcouteurOptions implements ActionListener{
    PanelOptions p;
    int selecteur;
    /**
    * Constructeur de la classe
    * @param p : le panel appelant
    * @param selecteur : une valeur de type int permettant la selection d'une action
    */
    public EcouteurOptions(PanelOptions p, int selecteur){
        this.p = p;
        this.selecteur = selecteur;
    }
    /**
    * Réalise differentes actions en fonctions du selecteur
    * @param e : l'action crée par l'interaction avec un JObjet
    */
    public void actionPerformed(ActionEvent e){
        if(this.selecteur == 3){
            this.p.quitter();
        }
        if(this.selecteur == 4){
            this.p.retour();
        }
        if(this.selecteur == 0){
            this.p.miseAjourSkins(0);
        }
        if(this.selecteur == 1){
            this.p.miseAjourSkins(1);
        }
        if(this.selecteur == 2){
            this.p.miseAjourSkins(2);
        }
    }
}
