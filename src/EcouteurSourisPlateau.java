package controller;
import java.awt.event.*;
import view.*;
import model.*;
import javax.swing.*;
/**
* Classe EcouteurSourisPlateau
* interactions avec les clique de souris pur la classe PlateauDeJeu
*/
public class EcouteurSourisPlateau extends MouseAdapter{
    PlateauDeJeu p;
    /**
    * Constructeur de la classe
    * @param p : le plateau de jeu appelant
    */
    public EcouteurSourisPlateau(PlateauDeJeu p){
        this.p = p;
    }
    /**
    * Réalise differentes actions en fonctions du clique de la souris
    * @param e : l'action crée par l'interaction avec la souris
    */
    public void mouseClicked(MouseEvent e){
        JLabel a = (JLabel)e.getSource();
        if(e.getButton() == 1){ //révèle le pion contenu si clic droit
            this.p.posAct(a.getName());
        }
        if(e.getButton() == 3){ //révèle le pion contenu si clic droit
            this.p.posDest(a.getName());
            this.p.reveler();
        }
    }
}
