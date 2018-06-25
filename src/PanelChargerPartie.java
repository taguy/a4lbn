package view;
import java.awt.*;
import javax.swing.*;
import controller.*;
import java.util.ArrayList;
/**
* Classe PanelChargerPartie
* Classe permettant de créer le panel panelChargerPartie nécéssaire à la classe Menu
*/
public class PanelChargerPartie{
    ControleurGlobal controleurGlobal;
    String cheminLogo;
    ArrayList<Partie> lesParties;
    String[] nomParties;
    JComboBox<String> boxParties;
    JPanel panelGlobal;
    JButton retour;
    JButton quitter;
    JButton valider;
    JLabel texte;
    JLabel titre;
    Menu m;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelChargerPartie(Menu m , ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.lesParties = controleurGlobal.getLesParties();
        this.nomParties = controleurGlobal.getNomParties();
        this.cheminLogo = controleurGlobal.getCheminLogo();
        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new BorderLayout());

        JPanel panelLigne1 = new JPanel();
        panelLigne1.setLayout(new BoxLayout(panelLigne1, BoxLayout.PAGE_AXIS));
        JPanel panelLigne2 = new JPanel();
        panelLigne2.setLayout(new BoxLayout(panelLigne2, BoxLayout.LINE_AXIS));
        JPanel panelLigne3 = new JPanel();
        panelLigne3.setLayout(new BoxLayout(panelLigne3, BoxLayout.LINE_AXIS));
        JPanel panelLigne4 = new JPanel();
        panelLigne4.setLayout(new BoxLayout(panelLigne4, BoxLayout.LINE_AXIS));
        JPanel panelLigne5 = new JPanel();
        panelLigne5.setLayout(new BoxLayout(panelLigne5, BoxLayout.LINE_AXIS));

        this.titre = new JLabel("Charger une partie");
        this.titre.setFont(new Font("Arial",Font.BOLD,25));
        this.texte = new JLabel("Choisir une partie");
        this.valider = new JButton("Valider");
        this.quitter = new JButton("Quitter");
        this.retour = new JButton("Retour");
        this.boxParties = new JComboBox<String>(this.nomParties);

        panelLigne2.add(new JLabel(new ImageIcon(this.cheminLogo)));
        panelLigne3.add(this.titre);
        panelLigne4.add(this.texte);

        panelLigne1.add(panelLigne2);
        panelLigne1.add(panelLigne3);
        panelLigne1.add((Box.createVerticalStrut(25)));
        panelLigne1.add(panelLigne4);


        panelLigne5.add(this.quitter);
        panelLigne5.add(Box.createHorizontalStrut(50));
        panelLigne5.add(this.valider);
        panelLigne5.add(Box.createHorizontalStrut(50));
        panelLigne5.add(this.retour);

        this.panelGlobal.add(panelLigne1,BorderLayout.NORTH);
        this.panelGlobal.add(this.boxParties,BorderLayout.CENTER);
        this.panelGlobal.add(panelLigne5,BorderLayout.SOUTH);

        this.quitter.addActionListener(new EcouteurChargerPartie(this, 0));
        this.retour.addActionListener(new EcouteurChargerPartie(this, 1));
        this.valider.addActionListener(new EcouteurChargerPartie(this, 2));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelChargerPartie(){
        return this.panelGlobal;
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
    /**
    * Creer un plateau de jeu en fonction de l'interaction de l'utilisateur sur le JObjets
    */
    public void valider(){
        System.out.println("valider");
        System.out.println("La partie choisie :  "+this.boxParties.getSelectedItem());
        for(Partie e : this.lesParties){
            if(this.boxParties.getSelectedItem() == e.getNom()){
                PlateauDeJeu p = new PlateauDeJeu(e,this.controleurGlobal);
                this.m.quitter();
            }
        }
    }
}
