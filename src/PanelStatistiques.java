package view;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
* Classe PanelChargerPartie
* Classe permettant de créer le panel panelChargerPartie nécéssaire à la classe Menu
*/
public class PanelStatistiques{
    ControleurGlobal controleurGlobal;
    ArrayList<Statistiques> lesStats;
    Menu m;
    JPanel panelGlobal;
    String cheminLogo;
    JLabel titre;
    JLabel logo;
    JButton retour;
    JButton quitter;
    JPanel statistiques;
    /**
    * Constructeur du la classe
    * @param m : le menu appelant
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PanelStatistiques(Menu m, ControleurGlobal ct){
        this.m = m;
        this.controleurGlobal = ct;
        this.cheminLogo = this.controleurGlobal.getCheminLogo();
        this.lesStats = this.controleurGlobal.getLesStats();

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

        JPanel conteneurStatistiques = new JPanel();
        conteneurStatistiques.setLayout(new BoxLayout(conteneurStatistiques, BoxLayout.LINE_AXIS));

        this.statistiques = new JPanel();
        this.statistiques.setLayout(new BoxLayout(statistiques, BoxLayout.PAGE_AXIS));


        this.logo = new JLabel(new ImageIcon(this.cheminLogo));
        this.quitter = new JButton("Quitter");
        this.retour = new JButton("Retour");
        this.titre = new JLabel("Statistiques");
        this.titre.setFont(new Font("Arial",Font.BOLD,25));
        this.miseAjourStatistiques();
        conteneurStatistiques.add(this.statistiques);
        panelLigne2.add(this.titre);
        panelLigne3.add(Box.createHorizontalStrut(50));
        panelLigne4.add(this.quitter);
        panelLigne4.add(Box.createHorizontalStrut(70));
        panelLigne4.add(this.retour);

        panelLigne1.add(panelLigne2);
        panelLigne1.add(panelLigne3);
        panelLigne1.add(conteneurStatistiques);
        panelLigne1.add(panelLigne4);

        panelGlobal.add(this.logo ,BorderLayout.NORTH);
        panelGlobal.add(panelLigne1, BorderLayout.CENTER);

        this.quitter.addActionListener(new EcouteurStatistiques(this, 1));
        this.retour.addActionListener(new EcouteurStatistiques(this, 2));

    }
    /**
    * Retourne le panel PanelChargerPartie nécéssaire à la classe Menu
    * @return this.panelGlobal : le panel global à retourner
    */
    public JPanel getPanelStatistiques(){
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
    private void miseAjourStatistiques(){
        this.statistiques.removeAll();
        if(this.lesStats != null){
            if(this.lesStats.size() == 0){
                this.statistiques.add(new JLabel("Aucunes statistiques sauvagerdées"));
            }
            else{
                for(int i = 0; i < this.lesStats.size(); i++){
                    this.statistiques.add(new JLabel(this.lesStats.get(i).getJoueur().getNom()+" Victoire : "+this.lesStats.get(i).getNbVictoires()+" Parties :"+this.lesStats.get(i).getNbParties()+" Pion mangé :"+this.lesStats.get(i).getNbPionsMange()));
                }
            }
        }
        this.m.rafraichir();
    }
}
