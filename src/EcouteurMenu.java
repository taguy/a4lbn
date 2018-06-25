package controller;

import view.*;
import model.*;
import java.awt.event.*;
/**
* Classe EcouteurMenu
* interactions avec les JObjets de la classe Menu
*/
public class EcouteurMenu implements ActionListener{
  Menu menu;
  int selecteur;
  /**
  * Constructeur de la classe
  * @param menu : le panel appelant
  * @param selecteur : une valeur de type int permettant la selection d'une action
  */
  public EcouteurMenu(Menu menu, int selecteur){
    this.menu = menu;
    this.selecteur = selecteur;
  }
  /**
  * Réalise differentes actions en fonctions du selecteur
  * @param e : l'action crée par l'interaction avec un JObjet
  */
  public void actionPerformed(ActionEvent e){
   if(selecteur == 0){
     this.menu.chagerPartie();
    }
    if(selecteur == 1){
      this.menu.nouvellePartie();
    }
    if(selecteur == 2){
      this.menu.statistiques();
    }
    if(selecteur == 3){
      this.menu.options();
    }
    if(selecteur == 4){
      this.menu.tutoriel();
    }
    if(selecteur == 5){
      this.menu.credits();
    }
  }
}
