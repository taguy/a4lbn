package arcanor;
import java.io.*;
import utili.Utilitaire;
/**
 * Classe d'enregistrement de l'une partie
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
 public class Partie{

   private int tours;
   /** Le nombre de tours avant l'enregistrement */

   private int scoreA;
   /** Le score du joueur n°1 */

   private int scoreB;
   /** Le score fu joueur n°2 */

   private String nom;
   /** Le nom de la partie */

   private Joueur joueurA;
   /** Le joueur n°1 */

   private Joueur joueurB;
   /** Le joueur n°2 */

   private Joueur current;

   private Pion[][] damier;
   /** Le damier contenant les pions */

   private final int SCOREMAX = 12;

   private static final String SAUVPARTIES = "../files/lesParties.txt";


   /**
    * Le constructeur de la classe qui intialise les attributs
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
           this.tours = 1;

           this.damier = new Pion[8][7];
           this.current = joueurA;

           initializePions(joueurA);
           initializePions(joueurB);
       }
       else{
          System.out.println("Partie :Erreur les parmètres sont invalides ");
       }
   }

   /**
    * Le constructeur de la classe qui intialise les attributs
    * @param joueurA le 1er joueur
    * @param joueurB le 2e joueur
    * @param nom - le nom de la partie
    * @param tours - le nombre de tours déjà passés
    * @param scoreA - le score du joueur A
    * @param scoreB -le score du joueur B
    */
   public Partie(Joueur joueurA, Joueur joueurB, String nom, int tours, int scoreA, int scoreB){
     if(nom != null && joueurA != null && joueurB != null && tours > 0 && scoreA >= 0 && scoreB >= 0){
           this.joueurA = joueurA;
           this.joueurB = joueurB;
           this.scoreA = scoreA;
           this.scoreB = scoreB;
           this.tours = tours;
           this.nom = nom;

           this.damier = new Pion[8][7];
           this.current = joueurA;

       }
       else{
         System.out.println("Partie : Erreur les parmètres sont invalides");
       }
   }

   /**
    * Initialise les pions
    * @param joueur le joueur qui controle les pions
    */
   private void initializePions(Joueur joueur){
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
   Joueur getJoueurA(){
       return this.joueurA;
   }


   /**
    * Accesseur du 2e joueur
    * @return la classe Joueur qui represente le 2e joueur
    */
   Joueur getJoueurB(){
       return this.joueurB;
   }


   /**
    * Accesseur du nombre de tours
    * @return le int du nombre de tours
    */
   int getTours(){
       return this.tours;
   }

   /**
    * Accesseur du damier
    * @return le damier
    */
   Pion[][] getDamier(){
       return this.damier;
   }


   /**
    * Sauvegarder la Partie
    */
   void sauvePartie(){
       try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(SAUVPARTIES)));

           out.println(this.joueurA.getNom() + " " + this.joueurB.getNom() + " " + this.nom + " " + this.tours + " " + this.scoreA + " " + this.scoreB);
           out.close();
       }
       catch(IOException e) {
           e.printStackTrace();
       }

   }

	/**
	 * Donne le nom de la partie
	 * @return le nom de la partie
	 */
	String getNom(){
		return this.nom;
	}

	/**
	 * Retourne le score du joueur A
	 * @return le score du joueur A
	 */
	int getScoreA(){
		return this.scoreA;
	}

	/**
	 * Retourne le score du joueur B
	 * @return le score du joueur B
	 */
	int getScoreB(){
		return this.scoreB;
	}

	   /**
	 * Ajoute des points de score au joueur A
	 * @param points - les points à ajouter
	 */
	void addPointsA(int points){
        this.scoreA += points;
	}

	/**
	 * Ajoute des points de score au joueur B
	 * @param points - les points à ajouter
	 */
	void addPointsB(int points){
        this.scoreB += points;

	}

  /**
  */


  public void posFin(){
    for(int i = 0; i <= 7; i++){
        if(this.damier[i][0] != null){
            if(this.damier[i][0].getJoueur() == joueurB && !this.damier[i][0].getPosFin()){
                this.damier[i][0].setPosFin(true);
                addPointsB(damier[i][0].getTaille());

            }
        }
    }
    for(int j = 0; j <= 7; j++){
        if(this.damier[j][6] != null){
            if(this.damier[j][6].getJoueur() == joueurA && !this.damier[j][6].getPosFin()){
                this.damier[j][6].setPosFin(true);
                addPointsA(damier[j][6].getTaille());
            }
        }
    }
  }

	/**
	 * Joue un tour de jeu
	 */

	void jouer(){
        int[] posAct;
        int[] posDest;


         do {
            System.out.println (this.current.getNom() + " Sensei a toi de jouer ! \ntapez 'exit' pour sortir");

            System.out.println(this);
            if (this.current instanceof IA) {
                 int[][] tab = this.current.auto();
                 posAct = tab[0];
                 posDest = tab[1];
             } else {
                 posAct = Utilitaire.stringToInt("Quelle est la position du pion a déplacer ?");
                 posDest = Utilitaire.stringToInt("Quelle la position destination ?");
             }

                if (this.current.verifDeplacement(posAct,posDest) == 0) {
                    if (this.current == joueurA) {
                        this.current = joueurB;
                    } else {
                        this.current = joueurA;
                    }

                    this.tours++;
                }

                this.posFin();
            } while (!this.endOfTheGame());

            if (this.current == joueurA) {
                this.current = joueurB;
            } else {
                this.current = joueurA;
            }
            
            System.out.println(this);
            System.out.println(this.current.getNom()+" a gagné");
	  }


	/**
	 * Finis la partie
	 */
	boolean endOfTheGame(){
		boolean ret = false;
		if(this.scoreA >= SCOREMAX || this.scoreB >= SCOREMAX){
			ret = true;
		}
		return ret;
	}

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
        return ret;
    }
 }
