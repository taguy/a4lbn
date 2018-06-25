package view;
import java.awt.*;
import javax.swing.*;
import controller.*;

import java.awt.event.*;
import java.util.ArrayList;
/**
* Classe PanelNouvellePartie
* Classe permettant de créer le panel panelNouvellePartie nécéssaire à la classe Menu
*/
public class PanelNouvellePartie{
    static ControleurGlobal controleurGlobal;
    Menu m;
    JPanel panelGlobal;
    JPanel panelLigne4;
    JPanel panelLigne6;
    JTextField nomPartie;
    JTextField nomJoueurA;
    JTextField nomJoueurB;
    JComboBox<String> boxJoueurA;
    JComboBox<String> boxJoueurB;
    JLabel titre;
    JLabel choixDuJoueurA;
    JLabel choixDuJoueurB;
    JButton nouveauJoueurA;
    JButton nouveauJoueurB;
    JButton valider;
    JButton retour;
    JButton quitter;
    ArrayList<Joueur> lesJoueurs;
    ArrayList<Partie> lesParties;
    String[] nomsJoueurs;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelNouvellePartie(Menu m, ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.lesJoueurs = controleurGlobal.getLesJoueurs();
        this.lesParties = controleurGlobal.getLesParties();
        String[] nomsDesJoueurs = controleurGlobal.getNomJoueurs();

        //Tableau de joueur plus le joueur IA
        this.nomsJoueurs = new String[nomsDesJoueurs.length+1];
        for(int i = 0; i < nomsDesJoueurs.length; i++){
            this.nomsJoueurs[i] = "> "+nomsDesJoueurs[i];
        }
        this.nomsJoueurs[nomsDesJoueurs.length] = "IA" ;
        //

        this.panelGlobal = new JPanel();
        JPanel panelLigne1 = new JPanel();
        JPanel panelLigne2 = new JPanel();
        JPanel panelLigne3 = new JPanel();
        this.panelLigne4 = new JPanel();
        JPanel panelLigne5 = new JPanel();
        this.panelLigne6 = new JPanel();
        JPanel panelLigne7 = new JPanel();

        this.panelGlobal.setLayout(new BorderLayout());
        panelLigne1.setLayout(new BoxLayout(panelLigne1, BoxLayout.PAGE_AXIS));
        panelLigne2.setLayout(new BoxLayout(panelLigne2, BoxLayout.LINE_AXIS));
        panelLigne3.setLayout(new BoxLayout(panelLigne3, BoxLayout.LINE_AXIS));
        this.panelLigne4.setLayout(new BoxLayout(panelLigne4, BoxLayout.LINE_AXIS));
        panelLigne5.setLayout(new BoxLayout(panelLigne5, BoxLayout.LINE_AXIS));
        panelLigne7.setLayout(new BoxLayout(panelLigne7, BoxLayout.LINE_AXIS));
        this.panelLigne6.setLayout(new FlowLayout());

        this.titre = new JLabel("Nouvelle partie");

        this.nomJoueurA = new JTextField("");
        this.nomJoueurB = new JTextField("");
        this.retour = new JButton("Retour");
        this.quitter = new JButton("Quitter");
        this.valider = new JButton("<valider>");
        this.nouveauJoueurA = new JButton("Nouveau joueur A");
        this.nouveauJoueurB = new JButton("Nouveau joueur B");
        this.nomPartie = new JTextField("Nom de la nouvelle partie");
        this.boxJoueurA = new JComboBox<String>(this.nomsJoueurs);
        this.boxJoueurB = new JComboBox<String>(this.nomsJoueurs);
        this.choixDuJoueurA = new JLabel("Choix du joueur A :");
        this.choixDuJoueurB = new JLabel("Choix du joueur B :");
        panelLigne7.add(this.titre);
        this.titre.setFont(new Font("Arial",Font.BOLD,25));
        panelLigne1.add(panelLigne7);
        panelLigne1.add(Box.createVerticalStrut(25));
        panelLigne1.add(panelLigne2);
        panelLigne1.add(panelLigne3);
        panelLigne1.add(panelLigne4);
        panelLigne1.add(panelLigne5);


        panelLigne2.add(this.nomPartie);

        panelLigne3.add(this.choixDuJoueurA);
        panelLigne3.add(Box.createHorizontalStrut(175));
        panelLigne3.add(this.choixDuJoueurB);
        panelLigne3.add(Box.createHorizontalStrut(175));

        panelLigne4.add(this.boxJoueurA);
        panelLigne4.add(this.nouveauJoueurA);
        panelLigne4.add(this.boxJoueurB);
        panelLigne4.add(this.nouveauJoueurB);

        panelLigne5.add(Box.createHorizontalStrut(5));
        panelLigne5.add(this.valider);

        this.panelLigne6.add(this.quitter);
        this.panelLigne6.add(Box.createHorizontalStrut(300));
        this.panelLigne6.add(this.retour);

        panelGlobal.add(new JLabel(new ImageIcon(controleurGlobal.getCheminLogo())), BorderLayout.NORTH);
        panelGlobal.add(panelLigne1, BorderLayout.CENTER);
        panelGlobal.add(this.panelLigne6, BorderLayout.SOUTH);

        this.nouveauJoueurA.addActionListener(new EcouteurNouvellePartie(this,0));
        this.nouveauJoueurB.addActionListener(new EcouteurNouvellePartie(this,1));
        this.valider.addActionListener(new EcouteurNouvellePartie(this,2));
        this.retour.addActionListener(new EcouteurNouvellePartie(this,3));
        this.quitter.addActionListener(new EcouteurNouvellePartie(this,4));
        this.boxJoueurA.addActionListener(new EcouteurNouvellePartie(this,5));
        this.boxJoueurB.addActionListener(new EcouteurNouvellePartie(this,5));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelNouvellePartie(){

        return this.panelGlobal;
    }
    public void miseAjourNouveauJoueurA(){
        this.panelLigne4.remove(this.boxJoueurA);
        this.panelLigne4.add(this.nomJoueurA,0);
        this.m.rafraichir();
    }
    public void miseAjourNouveauJoueurB(){
        this.panelLigne4.remove(this.boxJoueurB);
        this.panelLigne4.add(this.nomJoueurB,2);
        this.m.rafraichir();
    }
    public void valider(){
        boolean j1 = false;
        boolean j2 = false;
        PlateauDeJeu plateau = null;
        Joueur joueurA = null;
        Joueur joueurB = null;
        System.out.println("valider");
        if(this.panelLigne4.getComponent(0) instanceof JTextField ){j1 = true;}
        if(this.panelLigne4.getComponent(2) instanceof JTextField ){j2 = true;}
        if(j1 && j2){
            joueurA = new Joueur(this.nomJoueurA.getText());
            joueurB = new Joueur(this.nomJoueurB.getText());
            lesParties.add(new Partie(joueurA,joueurB,this.nomPartie.getText()));
            lesJoueurs.add(joueurA);
            lesJoueurs.add(joueurB);
            System.out.println("Auncune IA p1");
        }else if(!j1 && j2){
            if(this.boxJoueurA.getSelectedItem().equals("IA")){ // une ComboBox et une IA
                joueurB = new Joueur(this.nomJoueurB.getText());
                lesParties.add(new Partie(new IA("IA"),joueurB,this.nomPartie.getText()));
                lesJoueurs.add(joueurB);
            }
            else{ //une ComboBox et pas d'IA
                joueurB = new Joueur(this.nomJoueurB.getText());
                lesParties.add(new Partie(this.lesJoueurs.get(this.boxJoueurA.getSelectedIndex()),joueurB,this.nomPartie.getText()));
                lesJoueurs.add(joueurB);
            }

        }else if(j1 && !j2){
            if(this.boxJoueurB.getSelectedItem().equals("IA")){ // une ComboBox et une IA
                joueurA = new Joueur(this.nomJoueurA.getText());
                lesParties.add(new Partie(joueurA,new IA("IA"),this.nomPartie.getText()));
                lesJoueurs.add(joueurA);
            }
            else{ //une ComboBox et pas d'IA
                joueurA = new Joueur(this.nomJoueurA.getText());
                lesParties.add(new Partie(new Joueur(this.nomJoueurA.getText()),this.lesJoueurs.get(this.boxJoueurB.getSelectedIndex()),this.nomPartie.getText()));
                lesJoueurs.add(joueurA);
            }
        }else{
            if((this.boxJoueurA.getSelectedItem().equals("IA"))&&(this.boxJoueurB.getSelectedItem().equals("IA"))){ // Deux ComboBox et deux IA
                 lesParties.add(new Partie(new IA("IA1"),new IA("IA2"),this.nomPartie.getText()));
            }
            else{
                if(this.boxJoueurA.getSelectedItem().equals("IA")){ //une ComboBox et une IA
                    joueurB = new Joueur(this.nomJoueurB.getText());
                    lesParties.add(new Partie(new IA("IA"),joueurB,this.nomPartie.getText()));
                    lesJoueurs.add(joueurB);
                }
                else if(this.boxJoueurB.getSelectedItem().equals("IA")){ //une ComboBox et une IA
                    joueurA = new Joueur(this.nomJoueurA.getText());
                    lesParties.add(new Partie(joueurA,new IA("IA"),this.nomPartie.getText()));
                    lesJoueurs.add(joueurA);
                }
                else if((!(this.boxJoueurA.getSelectedItem().equals("IA"))) && !(this.boxJoueurB.getSelectedItem().equals("IA"))){ //Deux ComboBox et pas d'IA
                    if(!(this.lesJoueurs.get(this.boxJoueurA.getSelectedIndex())).equals(this.lesJoueurs.get(this.boxJoueurB.getSelectedIndex()))){
                        lesParties.add(new Partie(this.lesJoueurs.get(this.boxJoueurA.getSelectedIndex()),this.lesJoueurs.get(this.boxJoueurB.getSelectedIndex()),this.nomPartie.getText()));
                    }
                    else{
                        JOptionPane erreur = new JOptionPane();
                        erreur.showMessageDialog(null, "Message préventif", "Attention vous ne pouvez pas jouer contre vous même", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }

        /*
            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                    System.out.println(e);
            }
        */
            //javax.swing.SwingUtilities.invokeLater(new Runnable() {
                //public void run() {
                    //PlateauDeJeu plateau = new PlateauDeJeu(lesParties.get(lesParties.size()-1), controleurGlobal);
                //}
            //});
            lesParties.get(lesParties.size()-1).getJoueurA().setDamier(lesParties.get(lesParties.size()-1).getDamier());
            lesParties.get(lesParties.size()-1).getJoueurB().setDamier(lesParties.get(lesParties.size()-1).getDamier());
            lesParties.get(lesParties.size()-1).jouer(this.controleurGlobal);


    }
    /**
    * Creer un nouvelle objet Menu pour retourner en arriere
    */
    public void retour(){
        this.m.quitter();
        Menu m = new Menu(this.controleurGlobal);
        System.out.println("retour");
    }
    /**
    * Quitte la fenêtre menu appelante
    */
    public void quitter(){
        System.out.println("quitter");
        this.m.quitter();
    }

}
