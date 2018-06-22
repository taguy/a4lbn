
package projetZ;
/**
 * Classe de la modelisation d'un pion
 * @author L. GERARDI
 * @author B. LAIGO
 * @author N. NGUYEN
 */
public class Pion {

  private Pion contenu;
  /** Le contenu dans le pion */
  private int taille;
  /** La taille du pion */
  private boolean posFin;

  private Joueur leJoueur;
  /** Le joueur a qui appartient le pion */

  /**
   * Le constructeur de la classe qui intialise ses attributs
   *@param taille la taille du pion
   *@param leJoueur le joueur concerné
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
   * L'accesseur du contenu
   * @return la classe Pion comme contenu
   */
  public Pion getContenu(){
	   return this.contenu;
  }

  /**
   * L'accesseur du taille
   * @return le int comme contenu
   */
  public int getTaille(){
	   return this.taille;
  }


  /**
   * Modificateur du contenu
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
   * @param pion le pion a mettre
   */
  void setPosFin(boolean b){
    this.posFin = b;
  }

  /**
   * recupeère de l'attribut posFin
   * @return ret : boolean
   */
  public boolean getPosFin(){
    return this.posFin;
  }

  public String toString(){
      String ret = null;
      ret = this.leJoueur.getNom().substring(0,2)+ " " + this.taille;
      return ret;
  }
}
