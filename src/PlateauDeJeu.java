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
    int[] posAct;
    int[] posDest;
    JButton sauvegarder;
    JButton quitter;
    JButton menu;
    JLabel joueurCourant;
    JLabel reveler;
    JLabel nomPartie;
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
        //System.out.println("couleur :"+this.cheminCouleur);
        this.partie = partie;
        this.frame = new JFrame();
        this.frame.setTitle("Space Arcanor");
        this.frame.setSize(990,750);

        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new BorderLayout());
        this.panelJeu = new JPanel();
        panelJeu.setLayout(new GridLayout(7,8));
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(new BoxLayout(panelOptions, BoxLayout.PAGE_AXIS));
        JPanel panelOptionsLigne = new JPanel();

        panelOptionsLigne.setLayout(new BoxLayout(panelOptionsLigne, BoxLayout.PAGE_AXIS));
        this.nomPartie = new JLabel(this.partie.getNom());
        this.nomPartie.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.contenu = new JLabel(new ImageIcon(this.cheminCouleur+"blanc.png"));
        this.joueurA = new JLabel("• J1 : "+this.partie.getJoueurA().getNom());
        this.joueurB = new JLabel("• J2 : "+this.partie.getJoueurB().getNom());
        this.scoreA = new JLabel("• Score de "+this.partie.getJoueurA().getNom()+" : "+this.partie.getScoreA());
        this.scoreB = new JLabel("• Score de "+this.partie.getJoueurB().getNom()+" : "+this.partie.getScoreB());
        this.tours = new JLabel("• Tours : "+this.partie.getTours());

        this.joueurA.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.joueurB.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.scoreA.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.scoreB.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.tours.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        this.menu = new JButton("Menu");
        this.quitter = new JButton("Quitter");
        this.sauvegarder = new JButton("Sauvegarder");
        this.joueurCourant = new JLabel("• A toi de jouer : "+this.partie.getJoueurCourant().getNom());
        this.joueurCourant.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        this.reveler = new JLabel("• Contenu");
        this.reveler.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        panelOptionsLigne.add(this.quitter);
        panelOptionsLigne.add(this.menu);
        panelOptionsLigne.add(this.sauvegarder);
        panelOptionsLigne.add(Box.createVerticalStrut(25));;
        panelOptionsLigne.add(this.nomPartie);
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
    }
    /**
    * Rafraichit la fenêtre PlateauDeJeu avec les déplacemments des pions et l'affichage du joueur courant
    */
    public void rafraichir(){
        this.recalculer();
        this.joueurCourant.setText("• A toi de jouer : "+this.partie.getJoueurCourant().getNom());
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
        this.scoreA.setText("• Score de "+this.partie.getJoueurA().getNom()+" : "+this.partie.getScoreA());
        this.rafraichir();
    }
    /**
    * Met à jour le score du joueur B
    */
    public void miseAjourScoreB(){
        this.scoreB.setText("• Score de "+this.partie.getJoueurB().getNom()+" : "+this.partie.getScoreB());
        this.rafraichir();
    }
    /**
    * Met à jour le nombre tours de la partie
    */
    public void miseAjourTours(){
        this.tours.setText("• Tours : "+this.partie.getTours());
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
        boolean reponse = false;
        System.out.println("sauvegarder");
        JOptionPane confirmation = new JOptionPane();
        int i=JOptionPane.showConfirmDialog(null, "Voulez vous sauvegarder les statistiques du, ou des joueurs (sauf IA) ?","Veuillez confirmer votre choix",JOptionPane.YES_NO_OPTION);
        if(i == 0){
            reponse = true;
        }
    }
    /**
    * révèle le contenu d'un pion appartenant au joueur courant
    */
    public void reveler(){
    if(this.posDest != null){
        if(this.partie.getDamier()[this.posDest[0]][this.posDest[1]] != null){
            if(this.partie.getDamier()[this.posDest[0]][this.posDest[1]].getJoueur() == this.partie.getJoueurCourant()){
                if(this.partie.getDamier()[this.posDest[0]][this.posDest[1]].getContenu() != null){
                    if(this.partie.getDamier()[this.posDest[0]][this.posDest[1]].getContenu().getJoueur() == this.partie.getJoueurA()){
                        this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"pionJ1"+this.partie.getDamier()[this.posDest[0]][this.posDest[1]].getContenu().getTaille()+".png"));
                    }
                    else{
                        System.out.println("pion méchant");
                        this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"pionJ2"+this.partie.getDamier()[this.posDest[0]][this.posDest[1]].getContenu().getTaille()+".png"));
                    }
                }
                else{
                    this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"blanc.png"));
                }
                this.rafraichir();
            }
        }else{
            this.contenu.setIcon(new ImageIcon(this.cheminCouleur+"blanc.png"));
        }
    }
        this.posDest = null;
    }
    /**
    * Affiche le gagant de la partie
    */
    public void affichageGagnant(){
        JOptionPane msg = new JOptionPane();
        if(this.partie.getJoueurCourant() == this.partie.getJoueurA()){
            msg.showMessageDialog(null, "Bravo le joueur : "+this.partie.getJoueurB().getNom(), "The Winner !!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            msg.showMessageDialog(null, "Bravo le joueur : "+this.partie.getJoueurA().getNom(), "The Winner !!", JOptionPane.PLAIN_MESSAGE);
        }
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
    * Remplie le tableau posAct avec la position choisie
    */
    public void posAct(String p){
        this.posAct = new int[2];
        this.posAct[0] = Integer.parseInt(p.substring(0,1));
        this.posAct[1] = Integer.parseInt(p.substring(1));
    }
    /**
    * Remplie le tableau posDest avec la position choisie
    */
    public void posDest(String p){
        boolean liberer = false;
        this.posDest = new int[2];
        this.posDest[0] = Integer.parseInt(p.substring(0,1));
        this.posDest[1] = Integer.parseInt(p.substring(1));
        JOptionPane confirmation = new JOptionPane();
        if(posAct != null){;
            if(this.partie.getDamier()[this.posAct[0]][this.posAct[1]] != null && this.partie.getDamier()[this.posAct[0]][this.posAct[1]].getContenu() != null && this.partie.getDamier()[this.posAct[0]][this.posAct[1]].getJoueur() == this.partie.getJoueurCourant()){
                int i=JOptionPane.showConfirmDialog(null, "Voulez vous liberer le contenu de la pièces ?","Veuillez confirmer votre choix",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    liberer = true;
                }
                this.partie.jouer(this, liberer);

            }
            else{
                this.partie.jouer(this, true);
            }

        }
    }
    /**
    * Retourne le tableau correspondant à la position actuelle du pion choisie
    * @return this.pos : le tableau correspondant à la position du dernier pion choisie
    */
    public int[] getPosAct(){
        return this.posAct;
    }
    /**
    * Retourne le tableau correspondant à la position de destination du pion choisie
    * @return this.pos : le tableau correspondant à la position du dernier pion choisie
    */
    public int[] getPosDest(){
        return this.posDest;
    }
    /**
    * met à null le tableau de posAct
    */
    public void setPosActNull(){
        this.posAct = null;
    }
    /**
    * met à null le tableau de posDest
    */
    public void setPosDestNull(){
        this.posDest = null;
    }
    public void messageErreur(int i){

    }

}
