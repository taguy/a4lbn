package controller;

import view.*;
import model.*;
import java.awt.event.*;
/**
* Classe EcouteurTuto
* interactions avec les JObjets de la classe PanelTuto
*/
public class EcouteurTuto implements ActionListener{
  PanelTuto p;
  int selecteur;
  /**
  * Constructeur de la classe
  * @param p : le panel appelant
  * @param selecteur : une valeur de type int permettant la selection d'une action
  */
  public EcouteurTuto(PanelTuto p, int selecteur){
    this.p = p;
    this.selecteur = selecteur;
  }
  /**
  * Réalise differentes actions en fonctions du selecteur
  * @param e : l'action crée par l'interaction avec un JObjet
  */
  public void actionPerformed(ActionEvent e){
    if(selecteur == 1){
      this.p.quitter();
    }
    if(selecteur == 2){
      this.p.retour();
    }
  }
}
