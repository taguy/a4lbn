package view;
import javax.swing.*;
import java.awt.*;
import controller.*;
/**
* Classe PlateauDeJeu
* Classe permettant d'afficher le damier du jeu et permettant à l'utilisateur de jouer
*/
public class PlateauDeJeu{
    ControleurGlobal controleurGlobal;
    String cheminCouleur;
    JPanel panelGlobal;
    JPanel panelJeu;
    Partie partie;
    JFrame frame;
    int[] pos;
    JButton sauvegarder;
    JButton quitter;
    JButton menu;
    JLabel joueurCourant;
    JLabel reveler;
    JLabel contenu;
    JLabel scoreA;
    JLabel scoreB;
    JLabel tours;
    JLabel joueurA;
    JLabel joueurB;
    /**
    * Constructeur du la classe
    * @param partie : la partie concernée par l'affichage
    * @param ct : le controleurGlobal fournissant la base de données d'informations
    */
    public PlateauDeJeu(Partie partie, ControleurGlobal ct){
        this.controleurGlobal = ct;
        this.cheminCouleur = this.controleurGlobal.getCheminCouleur();
        this.partie = partie;
        System.out.println("frame jeu créé");
        this.frame = new JFrame();
        this.frame.setTitle("ProjetZ Space");
        this.frame.setSize(1000,750);

        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new BorderLayout());
        this.panelJeu = new JPanel();
        panelJeu.setLayout(new GridLayout(7,8));
        //panelJeu.setSize(825,750);
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(new BoxLayout(panelOptions, BoxLayout.PAGE_AXIS));
        JPanel panelOptionsLigne = new JPanel();

        panelOptionsLigne.setLayout(new BoxLayout(panelOptionsLigne, BoxLayout.PAGE_AXIS));
        this.contenu = new JLabel(new ImageIcon(this.cheminCouleur+"blanc.png"));
        this.joueurA = new JLabel("J1 : "+this.partie.getJoueurA().getNom());
        this.joueurB = new JLabel("J2 : "+this.partie.getJoueurB().getNom());
        this.scoreA = new JLabel("ScoreA : "+this.partie.getScoreA());
        this.scoreB = new JLabel("ScoreB : "+this.partie.getScoreB());
        this.tours = new JLabel("Tours : "+this.partie.getTours());

        this.menu = new JButton("Menu");
        this.quitter = new JButton("Quitter");
        this.sauvegarder = new JButton("Sauvegarder");
        this.joueurCourant = new JLabel("A toi de jouer : "+this.partie.getJoueurCourant().getNom());
        this.reveler = new JLabel("reveler : Clic droit sur la pièce  ");

        panelOptionsLigne.add(this.quitter);
        panelOptionsLigne.add(this.menu);
        panelOptionsLigne.add(this.sauvegarder);
        panelOptionsLigne.add(Box.createVerticalStrut(25));
        panelOptionsLigne.add(this.joueurCourant);
        panelOptionsLigne.add(Box.createVerticalStrut(35));
        panelOptionsLigne.add(this.joueurA);
        panelOptionsLigne.add(this.joueurB);
        panelOptionsLigne.add(Box.createVerticalStrut(25));
        panelOptionsLigne.add(this.scoreA);
        panelOptionsLigne.add(this.scoreB);
        panelOptionsLigne.add(this.tours);
        panelOptionsLigne.add(Box.createVerticalStrut(25));
        panelOptionsLigne.add(this.reveler);
        panelOptionsLigne.add(this.contenu);



        panelOptions.add(panelOptionsLigne);


        this.rafraichir();
        this.panelGlobal.add(panelOptions, BorderLayout.WEST);
        this.panelGlobal.add(this.panelJeu, BorderLayout.CENTER);
        this.frame.add(panelGlobal);

        this.frame.setLocationRelativeTo(null); //Centre la frame sur l'écran
        this.frame.setVisible(true);


        this.quitter.addActionListener(new EcouteurPlateau(this,1));
        this.sauvegarder.addActionListener(new EcouteurPlateau(this,2));
        this.menu.addActionListener(new EcouteurPlateau(this,3));
        System.out.println("frame jeu fin");
    }
    /**
    * Rafraichit la fenêtre PlateauDeJeu avec les déplacemments des pions et l'affichage du joueur courant
    */
    public void rafraichir(){
        this.recalculer();
        this.joueurCourant.setText("A toi de jouer : "+this.partie.getJoueurCourant().getNom());
        this.frame.revalidate();
    }
    /**
    * Modifie le panel permettant d'afficher les pions sur le damier et recalcule les positions des pions dans le GridLayout
    */
    private void recalculer(){
        panelJeu.removeAll();
        for(int i = 0 ; i < this.partie.getDamier()[0].length; i++){
            for(int j = 0; j < this.partie.getDamier().length; j++){
                if(this.partie.getDamier()[j][i] == null){
                    JLabel label = new JLabel(new ImageIcon(this.cheminCouleur+"blanc.png"));
                    label.setName(""+j+i);
                    this.panelJeu.add(label);
                    label.addMouseListener(new EcouteurSourisPlateau(this));
                }
                else if(this.partie.getDamier()[j][i].getJoueur() == this.partie.getJoueurA()){
                    JLabel label = new JLabel(new ImageIcon(this.cheminCouleur+"pionJ1"+this.partie.getDamier()[j][i].getTaille()+".png"));
                    label.setName(""+j+i);
                    this.panelJeu.add(label);
                    label.addMouseListener(new EcouteurSourisPlateau(this));
                }
                else if(this.partie.getDamier()[j][i].getJoueur() == this.partie.getJoueurB()){
                    JLabel label = new JLabel(new ImageIcon(this.cheminCouleur+"pionJ2"+this.partie.getDamier()[j][i].getTaille()+".png"));
                    label.setName(""+j+i);
                    this.panelJeu.add(label);
                    label.addMouseListener(new EcouteurSourisPlateau(this));
                }
            }
        }
    }
    /**
    * Met à jour le score du joueur A
    */
    public void miseAjourScoreA(){
        this.scoreA.setText(""+this.partie.getScoreA());
        this.rafraichir();
    }
    /**
    * Met à jour le score du joueur B
    */
    public void miseAjourScoreB(){
        this.scoreB.setText(""+this.partie.getScoreB());
        this.rafraichir();
    }
    /**
    * Met à jour le nombre tours de la partie
    */
    public void miseAjourTours(){
        this.tours.setText(""+this.partie.getTours());
        this.rafraichir();
    }
    /**
    * Quitte la fenêtre menu appelante
    */
    public void quitter(){
        this.frame.dispose();
    }
    /**
    * Sauvegarde la partie en cours
    */
    public void sauvegarder(){
        System.out.println("sauvegarder");
    }
    /**
    * révèle le contenu d'un pion appartenant au joueur courant
    */
    public void reveler(){
        if(this.partie.getDamier()[this.pos[0]][this.pos[1]] != null){
            if(this.partie.getDamier()[this.pos[0]][this.pos[1]].getJoueur() == this.partie.getJoueurCourant()){
                if (this.partie.getDamier()[this.pos[0]][this.pos[1]].getContenu() != null) {
                    if(this.partie.getDamier()[this.pos[0]][this.pos[1]].getJoueur() == this.partie.getJoueurA()){
                        this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"pionJ1"+this.partie.getDamier()[this.pos[0]][this.pos[1]].getContenu().getTaille()+".png"));
                    }
                    else{
                        this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"pionJ2"+this.partie.getDamier()[this.pos[0]][this.pos[1]].getContenu().getTaille()+".png"));
                    }
                    this.rafraichir();
                }
            }
        }
    }
    /**
    * Affiche une animation pour le gagnant de la partie
    */
    public void animationGagnant(){

    }
    /**
    * Creer un nouvelle objet Menu pour retourner en arriere
    */
    public void retourMenu(){
        Menu m = new Menu(this.controleurGlobal);
        this.frame.dispose();
    }
    /**
    * Retourne la partie en cours
    * @return this.partie : la partie en cours
    */
    public Partie getPartie(){
     return this.partie;
    }
    /**
    * Affiche la postion du pion choisie
    */
    public void pionChoisit(int[][] pos){
         System.out.println("Pion choisit: "+pos);
    }
    /**
    * Remplie le tableau pos avec la position choisie
    */
    public void pos(String p){
        this.pos = new int[2];
        this.pos[0] = Integer.parseInt(p.substring(0,1));
        this.pos[1] = Integer.parseInt(p.substring(1));
        //System.out.println("posx : "+pos[0]+" posy :"+pos[1]);
    }
    /**
    * Retourne le tableau correspondant à la position du dernier pion choisie
    * @return this.pos : le tableau correspondant à la position du dernier pion choisie
    */
    public int[] getPos(){
        return this.pos;
    }
    /**
    * met à null le tableau de pos
    */
    public void setPosNull(){
        this.pos = null;
    }
}
