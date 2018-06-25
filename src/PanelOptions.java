package view;
import controller.*;
import javax.swing.*;
import java.awt.*;
/**
* Classe PanelOptions
* Classe permettant de créer le panel panelOptions nécéssaire à la classe Menu
*/
public class PanelOptions{
    ControleurGlobal controleurGlobal;
    Menu m;
    JPanel panelGlobal;
    String cheminSkin;
    String cheminLogo;
    String cheminCouleur;
    JLabel titre;
    JLabel titreChoixDuSkin;
    JLabel logo;
    JLabel skin0;
    JLabel skin1;
    JLabel skin2;
    JRadioButton checkSkin0;
    JRadioButton checkSkin1;
    JRadioButton checkSkin2;
    ButtonGroup groupe;
    JButton retour;
    JButton quitter;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelOptions(Menu m, ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.cheminLogo = this.controleurGlobal.getCheminLogo();
        this.cheminSkin = this.controleurGlobal.getCheminSkin();

        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new BorderLayout());
        JPanel panelLigne2 = new JPanel();
        panelLigne2.setLayout(new BoxLayout(panelLigne2, BoxLayout.PAGE_AXIS));
        JPanel panelLigne3 = new JPanel();
        panelLigne3.setLayout(new BoxLayout(panelLigne3, BoxLayout.LINE_AXIS));
        JPanel panelLigne4 = new JPanel();
        panelLigne4.setLayout(new BoxLayout(panelLigne4, BoxLayout.LINE_AXIS));
        JPanel panelLigne5 = new JPanel();
        panelLigne5.setLayout(new BoxLayout(panelLigne5, BoxLayout.LINE_AXIS));

        JPanel panelLigne1 = new JPanel();
        panelLigne1.setLayout(new BoxLayout(panelLigne1, BoxLayout.LINE_AXIS));

        this.logo = new JLabel(new ImageIcon(this.cheminLogo));
        this.quitter = new JButton("Quitter");
        this.retour = new JButton("Retour");
        this.groupe = new ButtonGroup();
        this.titre = new JLabel("Options");
        this.titre.setFont(new Font("Arial",Font.BOLD,25));
        this.titreChoixDuSkin = new JLabel("Choix des skins :");
        this.checkSkin0 = new JRadioButton();
        this.checkSkin1 = new JRadioButton();
        this.checkSkin2 = new JRadioButton();
        this.skin0 = new JLabel(new ImageIcon(this.cheminSkin+"skin0.png"));
        this.skin1 = new JLabel(new ImageIcon(this.cheminSkin+"skin1.png"));
        this.skin2 = new JLabel(new ImageIcon(this.cheminSkin+"skin2.png"));

        this.groupe.add(this.checkSkin0);
        this.groupe.add(this.checkSkin1);
        this.groupe.add(this.checkSkin2);

        panelLigne1.add(this.titre);

        panelLigne3.add(this.checkSkin0);
        panelLigne3.add(this.skin0);
        panelLigne3.add(this.checkSkin1);
        panelLigne3.add(this.skin1);
        panelLigne3.add(this.checkSkin2);
        panelLigne3.add(this.skin2);

        panelLigne4.add(this.quitter);
        panelLigne4.add(Box.createHorizontalStrut(70));
        panelLigne4.add(this.retour);

        panelLigne5.add(this.titreChoixDuSkin);

        panelLigne2.add(panelLigne1);
        panelLigne2.add(Box.createVerticalStrut(25));
        panelLigne2.add(panelLigne5);
        panelLigne2.add(Box.createVerticalStrut(10));
        panelLigne2.add(panelLigne3);
        panelLigne2.add(Box.createVerticalStrut(25));

        panelGlobal.add(this.logo ,BorderLayout.NORTH);
        panelGlobal.add(panelLigne2, BorderLayout.CENTER);
        panelGlobal.add(panelLigne4, BorderLayout.SOUTH);

        this.quitter.addActionListener(new EcouteurOptions(this, 3));
        this.retour.addActionListener(new EcouteurOptions(this, 4));
        this.checkSkin0.addActionListener(new EcouteurOptions(this, 0));
        this.checkSkin1.addActionListener(new EcouteurOptions(this, 1));
        this.checkSkin2.addActionListener(new EcouteurOptions(this, 2));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelOptions(){
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
    * Met a jour le chemin d'accès des skins en fonction du choix fait par l'utilisateur
    * @param i : le choix du chemin
    */
    public void miseAjourSkins(int i){
        System.out.println("miseAjourSkins");
        this.controleurGlobal.modifierCheminCouleur(i);
    }
}
