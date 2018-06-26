package view;
import java.awt.*;
import javax.swing.*;
import sun.audio.*;
import java.io.*;

import controller.*;
/**
* Classe Menu
* View du Menu du jeu
*/
public class Menu{
    ControleurGlobal controleurGlobal;
    CardLayout cl;
    String[] listCard;
    JPanel panelCardLayout;
    JFrame frame;

    JPanel card1; //frame1
    JPanel card2; //frame2
    JPanel card3; //frame3
    JPanel card4; //frame4
    JPanel card5; //frame5
    JPanel card6; //frame6
    JPanel card7; //frame6

    String cheminCouleur;
    String cheminLogo;


    JButton chargerUnePartie;
    JButton nouvellePartie;
    JButton statistiques;
    JButton options;
    JButton tutoriel;
    JButton credits;
    JLabel logo;
    JLabel vide;
    /**
    * Constructeur du la classe
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public Menu(ControleurGlobal ct){

        this.controleurGlobal = ct;
        this.cheminCouleur = controleurGlobal.getCheminCouleur();
        this.cheminLogo = controleurGlobal.getCheminLogo();

        this.frame = new JFrame();
        this.frame.setTitle("Space Arcanor");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600,350);

        this.panelCardLayout = new JPanel();
        this.cl = new CardLayout();
        this.panelCardLayout.setLayout(cl);
        this.listCard = new String[7];
        this.listCard[0] = "CARD_1";
        this.listCard[1] = "CARD_2";
        this.listCard[2] = "CARD_3";
        this.listCard[3] = "CARD_4";
        this.listCard[4] = "CARD_5";
        this.listCard[5] = "CARD_6";
        this.listCard[6] = "CARD_7";

        //Frame : De base

        this.card1 = new JPanel();
        this.card1.setLayout(new BorderLayout());
        JPanel panelCard1 = new JPanel();
        JPanel sousPanelCard1 = new JPanel();
        ImageIcon leLogo = new ImageIcon(this.cheminLogo);
        this.logo = new JLabel(leLogo);
        this.card1.add(this.logo, BorderLayout.NORTH);


        sousPanelCard1.setLayout(new GridLayout(6,1));
        this.chargerUnePartie = new JButton("Charger Une Partie");
        sousPanelCard1.add(chargerUnePartie);
        this.nouvellePartie = new JButton("Nouvelle partie");
        sousPanelCard1.add(nouvellePartie);
        this.statistiques = new JButton("Statistiques");
        sousPanelCard1.add(statistiques);
        this.options = new JButton("Options");
        sousPanelCard1.add(options);
        this.tutoriel = new JButton("Tutoriels");
        sousPanelCard1.add(tutoriel);
        this.credits = new JButton("Crédits");
        sousPanelCard1.add(credits);

        panelCard1.add(sousPanelCard1);
        this.card1.add(panelCard1);


        //Frame 2 : Nouvelle partie
        this.card2 = new JPanel();
        PanelNouvellePartie panelNouvellePartie = new PanelNouvellePartie(this, controleurGlobal );
        JPanel panelCard2 = new JPanel();
        panelCard2.setLayout(new BorderLayout());
        panelCard2.add(panelNouvellePartie.getPanelNouvellePartie());
        this.card2.add(panelCard2);
        //

        //Frame 3 :Charger Partie
        this.card3 = new JPanel();
        PanelChargerPartie panelChargerPartie = new PanelChargerPartie(this, controleurGlobal);
        JPanel panelCard3 = new JPanel();
        panelCard3.setLayout(new BorderLayout());
        panelCard3.add(panelChargerPartie.getPanelChargerPartie());
        this.card3.add(panelCard3);
        //
        //Frame 3 :Statistiques
        this.card4 = new JPanel();
        PanelStatistiques PanelStatistiques = new PanelStatistiques(this, controleurGlobal);
        JPanel panelCard4 = new JPanel();
        panelCard4.setLayout(new BorderLayout());
        panelCard4.add(PanelStatistiques.getPanelStatistiques());
        this.card4.add(panelCard4);
        //
        //Frame 4 :Options
        this.card5 = new JPanel();
        PanelOptions panelOptions = new PanelOptions(this, controleurGlobal);
        JPanel panelCard5 = new JPanel();
        panelCard5.setLayout(new BorderLayout());
        panelCard5.add(panelOptions.getPanelOptions());
        this.card5.add(panelCard5);
        //
        //Frame 5 :Tuto
        this.card6 = new JPanel();
        PanelTuto panelTuto = new PanelTuto(this, controleurGlobal);
        JPanel panelCard6 = new JPanel();
        panelCard6.setLayout(new BorderLayout());
        panelCard6.add(panelTuto.getPanelTuto());
        this.card6.add(panelCard6);
        //
        //Frame 6 :Crédits
        this.card7 = new JPanel();
        PanelCredits panelCredits = new PanelCredits(this, controleurGlobal);
        JPanel panelCard7 = new JPanel();
        panelCard7.setLayout(new BorderLayout());
        panelCard7.add(panelCredits.getPanelCredits());
        this.card7.add(panelCard7);
        //
        this.panelCardLayout.add(this.card1, listCard[0]);
        this.panelCardLayout.add(this.card2, listCard[1]);
        this.panelCardLayout.add(this.card3, listCard[2]);
        this.panelCardLayout.add(this.card4, listCard[3]);
        this.panelCardLayout.add(this.card5, listCard[4]);
        this.panelCardLayout.add(this.card6, listCard[5]);
        this.panelCardLayout.add(this.card7, listCard[6]);

        this.frame.add(this.panelCardLayout);
        this.frame.setLocationRelativeTo(null); //Centre la frame sur l'écran
        this.frame.setVisible(true);

        //Ajout des actions utilisateur
        this.chargerUnePartie.addActionListener(new EcouteurMenu(this,0));
        this.nouvellePartie.addActionListener(new EcouteurMenu(this,1));
        this.statistiques.addActionListener(new EcouteurMenu(this,2));
        this.options.addActionListener(new EcouteurMenu(this,3));
        this.tutoriel.addActionListener(new EcouteurMenu(this,4));
        this.credits.addActionListener(new EcouteurMenu(this,5));
     }
    /**
    * Modifie la fenêtre par une fenêtre de création de partie
    */
    public void nouvellePartie(){
         this.frame.setSize(600,350);
         this.frame.setLocationRelativeTo(null);
         this.cl.show(this.panelCardLayout,this.listCard[1]);
    }
    /**
    * Modifie la fenêtre par une fenêtre de chargement de partie
    */
    public void chagerPartie(){
        this.frame.setSize(600,350);
        this.frame.setLocationRelativeTo(null);
        this.cl.show(this.panelCardLayout,this.listCard[2]);
    }
    /**
    * Modifie la fenêtre par une fenêtre d'affichage de statistiques
    */
    public void statistiques(){
        this.frame.setSize(600,350);
        this.frame.setLocationRelativeTo(null);
        this.cl.show(this.panelCardLayout,this.listCard[3]);
    }
    /**
    * Modifie la fenêtre par une fenêtre d'affichage des options
    */
    public void options(){
        this.frame.setSize(600,350);
        this.frame.setLocationRelativeTo(null);
        this.cl.show(this.panelCardLayout,this.listCard[4]);
    }
    /**
    * Modifie la fenêtre par une fenêtre d'affichage des tutoriels
    */
    public void tutoriel(){
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.frame.setLocationRelativeTo(null);
        this.cl.show(this.panelCardLayout,this.listCard[5]);
    }
    /**
    * Modifie la fenêtre par une fenêtre d'affichage des crédits
    */
    public void credits(){
        this.frame.setSize(600,350);
        this.frame.setLocationRelativeTo(null);
        this.cl.show(this.panelCardLayout,this.listCard[6]);
    }
    /**
    * Rafraichit la fenêtre de menu
    */
    public void rafraichir(){
        this.frame.repaint();
        this.frame.revalidate();
    }
    /**
    * Quitte la fenêtre de menu
    */
    public void quitter(){
        this.frame.dispose();
    }
}
