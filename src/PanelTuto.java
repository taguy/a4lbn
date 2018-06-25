package view;
import controller.*;
import javax.swing.*;
import java.awt.*;
/**
* Classe PanelTuto
* Classe permettant de créer le panel panelTuto nécéssaire à la classe Menu
*/
public class PanelTuto{
    ControleurGlobal controleurGlobal;
    Menu m;
    JPanel panelGlobal;
    String cheminDonnees;
    String cheminLogo;
    //String cheminCouleur;
    JLabel logo;
    JLabel titre;
    JLabel titre1;
    JLabel titre2;
    JLabel titre3;
    JLabel titre4;
    JLabel image1;
    JLabel image2;
    JLabel image3;
    JButton retour;
    JButton quitter;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelTuto(Menu m, ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.cheminLogo = this.controleurGlobal.getCheminLogo();
        this.cheminDonnees = this.controleurGlobal.getCheminDonnees();

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

        JPanel panelLigne10 = new JPanel();
        panelLigne10.setLayout(new BoxLayout(panelLigne10, BoxLayout.LINE_AXIS));

        this.titre = new JLabel("Tutoriels");
        this.titre.setFont(new Font("Arial",Font.BOLD,25));


        this.titre2 = new JLabel("Déroulement de la partie");
        this.titre2.setFont(new Font("Arial",Font.BOLD,20));

        this.titre3 = new JLabel("Manger, liberer, reveler un pion");
        this.titre3.setFont(new Font("Arial",Font.BOLD,20));

        this.logo = new JLabel(new ImageIcon(this.cheminLogo));
        this.quitter = new JButton("Quitter");
        this.retour = new JButton("Retour");

        this.image1 = new JLabel(new ImageIcon(this.cheminDonnees+"reglesJeu/regles.png"));
        panelLigne2.add(this.titre);
        panelLigne3.add(this.image1);
        panelLigne4.add(this.quitter);
        panelLigne4.add(Box.createHorizontalStrut(70));
        panelLigne4.add(this.retour);

        panelLigne1.add(panelLigne2);
        panelLigne1.add(Box.createVerticalStrut(5));
        panelLigne1.add(panelLigne3);
        panelLigne1.add(Box.createVerticalStrut(10));
        panelLigne1.add(panelLigne4);

        panelGlobal.add(this.logo ,BorderLayout.NORTH);
        panelGlobal.add(panelLigne1, BorderLayout.CENTER);

        this.quitter.addActionListener(new EcouteurTuto(this, 1));
        this.retour.addActionListener(new EcouteurTuto(this, 2));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelTuto(){
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
}
