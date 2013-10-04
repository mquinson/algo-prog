/* -*- coding: utf-8 -*-  */
/**
 * Cette classe décrit une pyramide d'entiers
 * @author Martine Gautier  Faculté des Sciences - UHP Nancy I
 * @author Martin Quinson ESIAL - UHP Nancy I
 * @version Janvier 2006
 */   
package pyramide ;
public class PyramideVite {

    /* **********
     * * Champs *
     * **********/
    protected int hauteur;
    protected int[] elements;
    protected boolean[] nbPris;


    /* *****************
     * * Constructeurs *
     * *****************/

    /** Construire une pyramide de hauteur donnée
     *  @param h hauteur de la pyramide 
     */
    //@ requires h >= 1 ;
    //@ ensures hauteur() == h ;

    public PyramideVite (int h) {
	hauteur = h;
	int nbElements = (hauteur *(hauteur + 1))/2;
	elements = new int [nbElements];

	nbPris = new boolean [nbElements];
	for (int i=0; i<nbElements; i++)
	    nbPris[i]=false;
    } // Pyramide(int)  


    /* ****************************
     * * Opérations d'observation *
     * ****************************/

    /** @return hauteur de la pyramide */
    public /*@pure@*/ int hauteur(){
	return hauteur ;
    } 

    /** @return le nombre de cases de la pyramide */
    public /*@pure@*/ int count(){
	return elements.length ;
    }

    /** @return une chaine représentant graphiquement la pyramide */
    public String toString(){
	String res = "Pyramide de hauteur "+hauteur()+"\n";
	for (int ligne=1; ligne <= hauteur(); ligne++) {
	    for (int colonne=ligne; colonne <= hauteur(); colonne++) {
		res += valueAt(ligne, colonne)+" " ;
	    }
	    res += "\n";
	    for (int nb_blancs=1 ; nb_blancs<=ligne ; nb_blancs++) 
		res+=" ";
	}
	return res;
    } // toString()


    /** @return l'entier situé en ligne lig et diagonale diag */
    //@ requires lig >= 1 && lig <= hauteur() && diag >= lig && diag <= hauteur() ;
    private  /*@pure@*/ int valueAt(int lig, int diag){
	return (elements[indice(lig,diag)]);
    }

    /** @return l'indice correspondant à la ligne lig et la diagonale diag */
    //@ requires lig >= 1 && lig <= hauteur() && diag >= lig && diag <= hauteur();
    private  /*@pure@*/ int indice(int lig, int diag){
        return (diag * (diag - 1 ) / 2 + lig - 1);
    } 

    /* *****************************
     * * Pour tester la correction *
     * *****************************/

    /** @return vrai si la pyramide est correctement construite
     * 
     *  càd respecte les deux contraintes :
     *   - contient tous les entiers entre 1 et count
     *   - une case contient le nombre égal à la différence en valeur 
     *     absolue des valeurs des 2 cases situées juste au-dessus d'elle
     */
    public /*@ pure @*/ boolean correcte(){
	return correcte(hauteur);
    } 

    /** @return vrai si la pyramide est correctement construite jusqu`à la diagonale diag */
    //@ requires diag >= 0 && diag <= hauteur() ;
    //@ ensures correcte() ;
    private  /*@pure@*/ boolean correcte(int diag){
	// Récursivité portant sur les diagonales de 1 à diag
	return ((diag == 0) || 
		(diagonaleCorrecte(diag,diag) && correcte(diag-1)));
    } // correcte(int)


    /** @return vrai si la diagonale diag est correcte jusqu'à la ligne lig */
    //@ requires lig >= 1&& lig <= diag+1 && diag >= 1 && diag <= hauteur() ;
    public  /*@pure@*/  boolean diagonaleCorrecte(int lig, int diag) {
	// Récursivité portant sur les lignes de 1 à lig
	if (lig == diag + 1) 
	    return true;

	int nbre = valueAt(lig,diag);

	/* jamais le droit de sortir de [1..count] */
	if (!nombreAutorise(nbre)) {
	    System.out.println("Nombre invalide "+nbre);
	    return false;
	}

	/* Aucune [autre] contrainte sur la premiere case */
	if (lig == 1 && diag == 1)
	    return true;

	/* il faut toujours être original */
	if (contains(nbre,lig-1,diag)) {
	    System.out.println("Nombre duppliqué "+nbre);
	    return false;
	}

	/* Il faut être la différence des ancêtres (sauf sur la première ligne) */
	if (lig > 1) {
	    int n1 = valueAt(lig-1,diag-1);
	    int n2 = valueAt(lig-1,diag);

	    if (nbre != Math.abs(n1 - n2) ) {
		System.out.println("soustraction ratée "+nbre);
		return false;
	    }
	}

	/* Tous les tests ont été passés avec succès */
	return true;
    } // diagonaleCorrecte(int, int)


    /** @return vrai si val est dans la pyramide avant la position (lig,diag) */
    private  /*@pure@*/ boolean contains(int val) {
	//	System.out.println("Verifie "+val);
	return nbPris[val-1];
    }

    /** @return vrai si val est dans la pyramide avant la position (lig,diag) */
    //@ requires lig >= 1 && lig <= hauteur() && diag >= 1 && diag <= hauteur();
    private  /*@pure@*/ boolean contains(int val, int lig, int diag) {
	/* On tient compte de l'implantation de la pyramide par soucis d'optimisation */

	int fin = indice(lig,diag);
	for (int i=0; i < fin; i++) {

	    if (val == elements[i])
		return true;
	}

	return false;
    }//contains

    /** @return vrai si le nombre val est dans l'intervalle [ 1 .. count] */
    private  /*@pure@*/ boolean nombreAutorise(int v){
	return (v >= 1 && v <= count());
    } 



    /* ********************************
     * * Opérations de transformation *
     * ********************************/
    /* Range v en ligne lig et diagonale diag */
    //@ requires nombreAutorise(v) && lig >= 1 
    //@          && lig <= hauteur() &&  diag >= lig && diag <= hauteur() ;
    //@ ensures valueAt(lig,diag) == v ;
    private void setValueAt(int v, int lig, int diag){
	elements[indice(lig,diag)] = v;
	nbPris[v-1] = true;
    }//setValueAt

    private void unsetValueAt(int lig, int diag){
	int id = indice(lig,diag);
	nbPris[elements[id] -1] = false;
	elements[id]=0;
	
    }//unsetValueAt


    /** Remplir une pyramide de façon correcte */
    //@ ensures correcte() ;
    public void remplir(){
	remplir(1); 	// on commence a la diagonale 1
    } 

    /** Remplir a partir de la diagonale diag */
    //@ requires diag >= 1 && diag <= hauteur() + 1 && correcte(diag-1);
    //@ ensures  (\result && correcte()) || !\result  ;
    private boolean remplir(int diag){
	// Itération conditionnelle parcourt le domaine [count .. 1]
        // Arret lorsqu'une solution est trouvee

	if (diag > hauteur) 
	    return true;

	for (int k=count(); k>=1; k--) {

	    // si k est déjà utilisé, on passe au tour de boucle suivant
	    if (!contains(k)) {

		// Essayer de propager k sur la diagonale

		if (propager(diag,k)) {
		    // La propagation a été faite, on essaie de remplir la suite
		    if (remplir(diag+1)) {
			// L'appel récursif est une réussite : on tient une solution
			return true;
		    }
		}
	      
	    } // k n'est pas encore pris
        }//while
	return false;
    }//remplir(int)

    //---------------------------------------------
    /**
     * si la propagation de val est possible sur la diagonale diag
     * modifie elements en conséquence et renvoie vrai.
     * si la propagation ne peut pas se faire, renvoie faux
     */ 
    //@ requires diag >= 1 && diag <= hauteur() && val >= 1 && val <= count();
    //@ ensures (!\result)  || (\result && diagonaleCorrecte(diag,diag)) ;
    private boolean propager(int diag, int val){
	// On pose val en haut de la diagonale
        setValueAt(val,1,diag);

        // On essaie de propager sur le reste de la diagonale

        // Itération conditionnelle portant sur le domaine [ 2 .. diag ]
        // On s'arrête dès que les contraintes ne sont plus respectées

	int lig = 2;
	int nbre = val;
	while (lig <= diag) {
	    /** invariant
		-- la diagonale diag est <<bien construite>> jusqu'en lig-1
		diagonaleCorrecte(lig-1,diag)
	    **/
	    /** variant         diag - lig  **/

	    nbre -= valueAt(lig-1,diag-1);    // évite deux accès dans elements
	    nbre = Math.abs(nbre);
            // On s'assure que le nombre ainsi calculé n'est pas déjà utilisé
	    if (contains(nbre)) {
		lig--;
		while (lig>=1) {
		    unsetValueAt(lig,diag);
		    lig--;
		}
		return false;
	    }

	    // Le nombre n'est pas utilisé; on le pose et on continue
	    setValueAt(nbre,lig,diag);
	    lig++;
	}//while
	return true;
    }//propager(int,int)

    //---------------------------------------------


    /**
       invariant
       hauteur >= 1;
       count = (hauteur *(hauteur + 1))//2;

       -- de representation
       elements.upper = count

    **/

} // class Pyramide
 
	       
	    
	    
	    

	    
	    
