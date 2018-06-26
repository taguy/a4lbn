package view;
import controller.*;
import javax.swing.*;
import java.awt.*;
/**
* Classe PanelCredits
* Classe permettant de créer le panel panelCredits nécéssaire à la classe Menu
*/
public class PanelCredits{
    ControleurGlobal controleurGlobal;
    Menu m;
    JPanel panelGlobal;
    String cheminLogo;
    JLabel info;
    JLabel logo;
    JButton retour;
    JButton quitter;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelCredits(Menu m, ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.cheminLogo = this.controleurGlobal.getCheminLogo();

        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new BorderLayout());
        JPanel panelLigne0 = new JPanel();
        panelLigne0.setLayout(new BoxLayout(panelLigne0, BoxLayout.PAGE_AXIS));
        JPanel panelLigne1 = new JPanel();
        panelLigne1.setLayout(new BoxLayout(panelLigne1, BoxLayout.LINE_AXIS));
        JPanel panelLigne2 = new JPanel();
        panelLigne2.setLayout(new BoxLayout(panelLigne2, BoxLayout.LINE_AXIS));


        this.logo = new JLabel(new ImageIcon(this.cheminLogo));
        this.quitter = new JButton("Quitter");
        this.retour = new JButton("Retour");
        this.info = new JLabel("© Réalisé par GERARDI Lorenzo, LAIGO Brice et NGUYEN Nguyen");
        panelLigne1.add(this.info);
        panelLigne2.add(this.quitter);
        panelLigne2.add(Box.createHorizontalStrut(50));
        panelLigne2.add(this.retour);

        panelLigne0.add(panelLigne1);
        panelLigne0.add(Box.createVerticalStrut(25));
        panelLigne0.add(panelLigne2);


        panelGlobal.add(this.logo ,BorderLayout.NORTH);
        panelGlobal.add(panelLigne0, BorderLayout.CENTER);

        this.quitter.addActionListener(new EcouteurCredits(this, 1));
        this.retour.addActionListener(new EcouteurCredits(this, 2));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelCredits(){
        return this.panelGlobal;
    }
    /**
    * Creer un nouvelle objet Menu pour retourner en arriere
    */
    public void retour(){
        this.m.quitter();
        Menu m = new Menu(this.controleurGlobal);
    }
    /**
    * Quitte la fenêtre menu appelante
    */
    public void quitter(){
        this.m.quitter();
    }
}
