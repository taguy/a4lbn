package controller;
import model.*;

import view.*;

/**
 * Classe de modélisation d'une partie
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
 public class Partie implements java.io.Serializable {

     /** Le nombre de tours avant l'enregistrement */
     private int tours;

     /** Le score du joueur n°1 */
     private int scoreA;

    /** Le score fu joueur n°2 */
    private int scoreB;

    /** Le nom de la partie */
    private String nom;

    /** Le joueur n°1 */
    private Joueur joueurA;

    /** Le joueur n°2 */
    private Joueur joueurB;

    /** Le joueur actuel */
    private Joueur courant;

    /** Le damier contenant les pions */
    private Pion[][] damier;

    /** Le temps de jeu */
    private double tempsDeJeu;

    /** Le score Maximale */
    private final int SCOREMAX = 12;


   /**
    * Le constructeur de la classe qui intialise les attributs pour création de l'objet
    * @param joueurA le 1er joueur
    * @param joueurB le 2e joueur
    * @param nom - le nom de la partie
    */
   public Partie(Joueur joueurA, Joueur joueurB, String nom){
       if(nom != null && joueurA != null && joueurB != null) {
           this.nom = nom;
           this.joueurA = joueurA;
           this.joueurB = joueurB;
           this.scoreA = 0;
           this.scoreB = 0;
           this.tempsDeJeu = 0;
           this.tours = 1;

           this.damier = new Pion[8][7];
           this.courant = joueurA;

           initPions(joueurA);
           initPions(joueurB);
       }
       else{
          System.out.println("Partie :Erreur les parmètres sont invalides ");
       }
   }


   /**
    * Initialise les pions
    * @param joueur le joueur qui controle les pions
    */
   private void initPions(Joueur joueur){
     int xa = 0;
     int xb = 0;
     if (joueur == this.joueurA){
       for ( xa = 0; xa <= 7; xa += 3){
         this.damier [xa][0] = new Pion(3, joueur);
         this.damier [xa][0].setContenu( new Pion(4, joueur));
         this.damier [xa+1][0] = new Pion(1, joueur);
         this.damier [xa+1][0].setContenu( new Pion(2, joueur));
       }
     }else if( joueur == this.joueurB){
       for (xa = 0; xa <= 7; xa += 3){
         this.damier [xa][6] = new Pion(1, joueur);
         this.damier [xa][6].setContenu( new Pion(2, joueur));
         this.damier [xa+1][6] = new Pion(3, joueur);
         this.damier [xa+1][6].setContenu( new Pion(4, joueur));
       }
     }
   }


   /**
    * Accesseur du 1er joueur
    * @return la classe Joueur qui represente le 1er joueur
    */
   public Joueur getJoueurA(){
       return this.joueurA;
   }


   /**
    * Accesseur du 2e joueur
    * @return la classe Joueur qui represente le 2e joueur
    */
   public Joueur getJoueurB(){
       return this.joueurB;
   }


   /**
    * Accesseur du joueur courant
    * @return la classe Joueur qui represente le joueur courant
    */
   public Joueur getJoueurCourant(){
       return this.courant;
   }


   /**
    * Accesseur du nombre de tours
    * @return le int du nombre de tours
    */
   public int getTours(){
       return this.tours;
   }

   /**
    * Accesseur du damier
    * @return le damier
    */
   public Pion[][] getDamier(){
       return this.damier;
   }

	/**
	 * Donne le nom de la partie
	 * @return le nom de la partie
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * Retourne le score du joueur A
	 * @return le score du joueur A
	 */
	public int getScoreA(){
		return this.scoreA;
	}

	/**
	 * Retourne le score du joueur B
	 * @return le score du joueur B
	 */
	public int getScoreB(){
		return this.scoreB;
	}

    /**
     * Retourne le temps de jeu
     * @return le temps de jeu
     */
    public double getTempsDeJeu() {
        return this.tempsDeJeu;
    }

   /**
	 * Ajoute des points de score au joueur A
	 * @param points - les points à ajouter
	 */
	private void addPointsA(int points){
        this.scoreA += points;
	}

	/**
	 * Ajoute des points de score au joueur B
	 * @param points - les points à ajouter
	 */
	private void addPointsB(int points){
        this.scoreB += points;
	}

    /**
     * Récupère la condition de fin de Partie
     * @return vrai si la partie est finie, faux sinon
     */
    public boolean getFinDuJeu() {
        return this.finDuJeu();
    }

  /**
  */


    public void posFin(){
        for(int i = 0; i <= 7; i++){
            if(this.damier[i][0] != null){
                if(this.damier[i][0].getJoueur() == this.joueurB && !this.damier[i][0].getPosFin()){
                    this.damier[i][0].setPosFin(true);
                    addPointsB(damier[i][0].getTaille());
                }
            }
        }

        for(int j = 0; j <= 7; j++){
            if(this.damier[j][6] != null){
                if(this.damier[j][6].getJoueur() == this.joueurA && !this.damier[j][6].getPosFin()){
                    this.damier[j][6].setPosFin(true);
                    addPointsA(damier[j][6].getTaille());
                }
            }
        }
    }

	/**
	 * Joue un tour de jeu
	 */
	public void jouer(){
        int[] posAct = new int[2];
        int[] posDest = new int[2];
        int tmp = -50;


         do {
            System.out.println (this.courant.getNom() + " Sensei a toi de jouer ! \ntapez 'exit' pour sortir");

            System.out.println(this);

            if (this.courant instanceof IA) {
                int[][] tab = this.courant.auto();
                posAct[0] = tab[0][0];
                posAct[1] = tab[0][1];

                posDest[0] = tab[1][0];
                posDest[1] = tab[1][1];

             } else {
                posAct = Utilitaire.stringToInt("Quelle est la position du pion a déplacer ?");
                posDest = Utilitaire.stringToInt("Quelle la position destination ?");
            }

            if (posAct[0] == -1 || posDest[0] == -1) {
                break;
            }

            if (this.courant instanceof IA) {
                tmp = this.courant.verifDeplacement(posAct,posDest, new java.util.Random().nextBoolean());
            }else {
                tmp = this.courant.verifDeplacement(posAct,posDest);
            }

            if (tmp == 0) {
                if (this.courant == this.joueurA) {
                    this.courant = this.joueurB;
                } else {
                    this.courant = this.joueurA;
                    this.tours++;
                }
            }

                this.posFin();
        } while (!this.finDuJeu());

            if (posAct[0] != -1 && posDest[0] != -1) {
                if (this.courant == joueurA) {
                    this.courant = joueurB;
                } else {
                    this.courant = joueurA;
                }

                System.out.println(this);
                System.out.println(this.courant.getNom()+" a gagné");
            }
	  }


        /**
    	 * Joue un tour de jeu
    	 */
    	public void jouer(ControleurGlobal c){
            Partie p = this;
            PlateauDeJeu plateau;

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    plateau = new PlateauDeJeu(p, c);
                }
            });

            int[] posAct = new int[2];
            int[] posDest = new int[2];
            int tmp = -50;

            plateau.rafraichir();

             do {
                if (this.courant instanceof IA) {
                    int[][] tab = this.courant.auto();
                    posAct[0] = tab[0][0];
                    posAct[1] = tab[0][1];

                    posDest[0] = tab[1][0];
                    posDest[1] = tab[1][1];

                 } else {
                     do {
                        posAct = plateau.getPos();
                    } while (posAct == null);

                    do {
                       posDest = plateau.getPos();
                   } while (posDest == null && (posDest[0] == posAct[0] && posDest[1] == posAct[1]));
                }


                if (this.courant instanceof IA) {
                    tmp = this.courant.verifDeplacement(posAct,posDest, new java.util.Random().nextBoolean());
                }else {
                    tmp = this.courant.verifDeplacement(posAct,posDest);
                }

                if (tmp == 0) {
                    plateau.rafraichir();

                    if (this.courant == this.joueurA) {
                        this.courant = this.joueurB;
                    } else {
                        this.courant = this.joueurA;
                        this.tours++;
                    }
                }

                    this.posFin();
            } while (!this.finDuJeu());
        }


	/**
	 * Rends vrai si la partie est finie, faux sinon
     * @return vrai si la partie est finie, faux sinon
	 */
	private boolean finDuJeu(){
		boolean ret = false;
		if(this.scoreA >= SCOREMAX || this.scoreB >= SCOREMAX){
			ret = true;
		}

		return ret;
	}


    /**
     * Retourne la partie sous forme de String
     * @return Les joueurs et leur score, le joueur actuel, le damier et les pions cachés
     */
    public String toString(){
      String ret = "Tours : " + this.tours + "\n" + this.joueurA.getNom() + " : " + this.scoreA + "\n" + this.joueurB.getNom() + " : "+this.scoreB + "\n  0    1    2    3    4    5    6    7    \n";

      for(int i = 0; i < this.damier[0].length; i++){
          for(int k = 0; k < this.damier.length; k++){
              if (this.damier[k][i] == null) {
                  ret += "|    ";
              } else {
                  ret += "|"+this.damier[k][i];
              }
          }
          ret +="|  "+ i + "\n";
      }

      for(int y = 0; y < this.damier[0].length; y++){

          for(int x = 0; x < this.damier.length; x++){

              if (this.damier[x][y] != null){

                  if (this.damier[x][y].getJoueur() == this.courant){
                      ret += this.damier[x][y]+"(1er)";

                      if (this.damier[x][y].getContenu() != null){
                          ret += " -> "+this.damier[x][y].getContenu()+"(2e)";

                          if (this.damier[x][y].getContenu().getContenu() != null){
                              ret += " -> "+this.damier[x][y].getContenu().getContenu()+"(3e)";

                              if (this.damier[x][y].getContenu().getContenu().getContenu() != null){
                                  ret += " -> " + this.damier[x][y].getContenu().getContenu().getContenu()+"(4e)";

                              }else{
                                  ret += "\n";
                              }
                          }else{
                              ret += "\n";
                          }
                      }else{
                          ret += "\n";
                      }
                  }
              }
          }
        }

        return ret;
    }
 }
