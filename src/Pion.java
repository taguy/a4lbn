package controller;
/**
 * Classe de la modelisation d'un pion
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
public class Pion implements java.io.Serializable {

    /** Le contenu dans le pion */
    private Pion contenu;

    /** La taille du pion */
    private int taille;

    /** vrai si le pion est passé de l'autre côté du damier (et ne peut plus être bougé), faux sinon */
    private boolean posFin;

    /** Le joueur à qui appartient le pion */
    private Joueur leJoueur;

      /**
       * Le constructeur de la classe qui intialise ses attributs pour création de l'objet
       * @param taille la taille du pion
       * @param leJoueur le joueur concerné
       */
      public Pion(int taille, Joueur leJoueur){
    	  if(taille < 1 || taille > 4){
    		  System.out.println("Erreur Pion : taille inapropriée (trop grande ou trop petite)");
    	  }else{
    		  this.taille = taille;
    		  this.contenu = null;
    		  this.leJoueur = leJoueur;
              this.posFin = false;
    	  }
      }

      /**
       * Le constructeur de la classe qui initialise ses attributs après sauvegarde
       * @param taille la taille du pion
       * @param leJoueur le joueur concerné
       * @param contenu le pion contenu dans le pion (peut être null)
       * @param posFin le booleen posFin du pion
       */
      public Pion(int taille, Joueur leJoueur, Pion contenu, boolean posFin){
    	  if(taille < 1 || taille > 4){
    		  System.out.println("Erreur Pion : taille inapropriée (trop grande ou trop petite)");
    	  }else{
    		  this.taille = taille;
    		  this.contenu = contenu;
    		  this.leJoueur = leJoueur;
              this.posFin = posFin;
    	  }
      }


      /**
       * L'accesseur du contenu
       * @return un pion ou null
       */
      public Pion getContenu(){
    	   return this.contenu;
      }


      /**
       * L'accesseur de la taille
       * @return le int comme contenu
       */
      public int getTaille(){
    	   return this.taille;
      }


      /**
       * Modificateur du contenu du pion
       * @param pion le pion a mettre
       */
      void setContenu(Pion pion){
    	  this.contenu = pion;
      }


      /**
       * Retourne le joueur
       * @return le Joueur
       */
      public Joueur getJoueur(){
    	  return this.leJoueur;
      }


      /**
       * Modificateur de l'attribut posFin
       * @param b le booleen à mettre
       */
      void setPosFin(boolean b){
        this.posFin = b;
      }


      /**
       * Récupère l'attribut posFin
       * @return vrai si le pion a traversé le damier, faux sinon
       */
      public boolean getPosFin(){
        return this.posFin;
      }


      /** Retourne le pion sous la forme d'une String
       * @return Le nom du joueur et la taille du pion
       */
      public String toString(){
          String ret = null;
          ret = this.leJoueur.getNom().substring(0,2)+ " " + this.taille;
          return ret;
      }
}
