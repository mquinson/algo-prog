/** Classe implémentant une solution au problème du sac à dos.
 *
 * Elle peut également être utilisée pour manipuler des solutions partielles 
 *(ie, en cours de construction).
 */

public class KnapsackSolution {
    /** pour chaque objet i, il est dans le sac à dos ssi pris[i] est vrai */
    private boolean [] pris;
    /** valeur totale du contenu du sac */
    private int valeurTotale;

    /** valeurs de chaque objet (dans un tableau).
     * Pratique pour les methodes prendObjet et poseObjet */
    private int valeur[];

    /******************************************/
    /*** Constructeur et fonctions basiques ***/

    /* Initialise une solution à partir de l'instance du problème passée en paramètre */
    public KnapsackSolution(int valeur[]) {
	pris = new boolean[valeur.length];

	for (int i=0; i<pris.length; i++) 
	    pris[i]=false;

	valeurTotale = 0;
	this.valeur = valeur;	
    }

    /* un getter ou deux */
    public boolean [] getPris() { return pris; }
    public /*@pure@*/ boolean getPris(int i) { return pris[i]; }
    public /*@pure@*/ int getValeur() { return valeurTotale; }

    public String toString() {
	return toString(pris.length);
    }

    /* N'affiche que jusqu'à un certain objet (affiche une solution partielle) */
    public String toString(int objMax) {
	String s = "";
       //s += "Solution Courante:  ";
	for (int i=0; i<objMax; i++)
	    if (pris[i])
		s += " O ";
	    else
		s += " N ";
        while (s.length() < valeur.length*3) 
	  s+= "...";
	s += "; Valeur: " + valeurTotale;
	return s;
    }


    /***********************************/
    /*** Methodes d'usage et d'acces ***/

    //@ requires !getPris(i) ;
    public void prendObjet(int i)   { 
	pris[i] = true; 
	valeurTotale += valeur[i];
    }
    //@ requires getPris(i) ;
    public void poseObjet(int i) {
	pris[i] = false; 
	valeurTotale -= valeur[i];
    }

    /**************************/
    /*** Fonctions avancées ***/

    /* Crée une copie de l'objet courant */
    public KnapsackSolution duplique() {
	KnapsackSolution res = new KnapsackSolution(valeur);
	for (int i=0; i<valeur.length; i++) {
	    if (pris[i]) {
		res.prendObjet(i);
	    }
	}
	return res;
    }
}
