/* -*- coding: utf-8 -*-  */
/**
 * Cette classe décrit une pyramide d'entiers
 * @author Martine Gautier  Faculté des Sciences - UHP Nancy I
 * @author Martin Quinson ESIAL - UHP Nancy I
 * @version Janvier 2006
 */   
package pyramide ;
public class Pyramide {

    /* **********
     * * Champs *
     * **********/
    protected int hauteur;
    protected int[] elements;


    /* *****************
     * * Constructeurs *
     * *****************/

    /** Construire une pyramide de hauteur donnée
     *  @param h hauteur de la pyramide
     */
    //@ requires h >= 1 ;
    //@ ensures hauteur() == h ;
    public Pyramide (int h) {
        hauteur = h;
        int nbElements = (hauteur *(hauteur + 1))/2;
        elements = new int [nbElements];
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



    /** @return l'indice correspondant à la ligne lig et la diagonale diag */
    //@ requires lig >= 1 && lig <= hauteur() 
    //@          && diag >= lig && diag <= hauteur();
    private  /*@pure@*/ int indice(int lig, int diag){
        /* BEGINKILL */
        return (diag * (diag - 1 ) / 2 + lig - 1);
        /* ENDKILL */
    } 

    /** @return l'entier situé en ligne lig et diagonale diag */
    //@ requires lig >= 1 && lig <= hauteur()
    //@          && diag >= lig && diag <= hauteur() ;
    private  /*@pure@*/ int valueAt(int lig, int diag){
        return (elements[indice(lig,diag)]);
    }

    /* Range v en ligne lig et diagonale diag */
    //@ requires nombreAutorise(v) && lig>=1 && lig<=hauteur() 
    //@          &&  diag>=lig && diag<=hauteur();
    //@ ensures valueAt(lig,diag) == v ;
    private void setValueAt(int v, int lig, int diag){
        elements[indice(lig,diag)] = v;
    }

    /* *****************************
     * * Pour tester la correction *
     * *****************************/

    /* Renvoi vrai si la pyramide est correctement construite
     * 
     *  càd respecte les deux contraintes :
     *   - contient tous les entiers entre 1 et count
     *   - une case contient le nombre égal à la différence en valeur 
     *     absolue des valeurs des 2 cases situées juste au-dessus d'elle
     */
    public /*@ pure @*/ boolean correcte(){
        return correcte(hauteur);
    } 

    /* Renvoi vrai si la pyramide est correctement construite jusqu`à la diagonale diag */
    //@ requires diag >= 0 && diag <= hauteur() ;
    //@ ensures correcte() ;
    private  /*@pure@*/ boolean correcte(int diag){
        // Récursivité portant sur les diagonales de 1 à diag
        /* BEGINKILL */
        if (diag == 0)
          return true;


        for (int lig=1; lig<=diag;lig++)
          if (posCorrecte(lig,diag) == false)
            return false;

        return correcte(diag-1);
        /* ENDKILL */
    } // correcte(int)


    /* Renvoi vrai si la position pointée est correcte */        
    //@ requires lig >= 1&& lig <= diag+1 
    //@          && diag >= 1 && diag <= hauteur() ;
    public  /*@pure@*/  boolean posCorrecte(int lig, int diag) {
        /* BEGINKILL */
        // Récursivité portant sur les lignes de 1 à lig
        if (lig == diag + 1) 
            return true;

        int nbre = valueAt(lig,diag);

        /* jamais le droit de sortir de [1..count] */
        if (!nombreAutorise(nbre)) 
            return false;

        /* Aucune [autre] contrainte sur la premiere case */
        if (lig == 1 && diag == 1)
            return true;

        /* il faut toujours être original */
        if (contains(nbre,lig-1,diag)) 
            return false;

        /* Il faut être la différence des ancêtres (sauf sur la première ligne) */
        if (lig > 1) {
            int n1 = valueAt(lig-1,diag-1);
            int n2 = valueAt(lig-1,diag);

            if (nbre != Math.abs(n1 - n2) )
                return false;
        }

        /* Tous les tests ont été passés avec succès */
        return true;
        /* ENDKILL */
    } // posCorrecte(int, int)


    /* Renvoie vrai si val est dans la pyramide avant la position (lig,diag) */
    //@ requires lig >= 1 && lig <= hauteur() 
    //@          && diag >= 1 && diag <= hauteur();
    private  /*@pure@*/ boolean contains(int val, int lig, int diag) {
        /* On tient compte de l'implantation de la pyramide par soucis d'optimisation */

        int fin = indice(lig,diag);
        for (int i=0; i < fin; i++) {

            if (val == elements[i])
                return true;
        }

        return false;
    }//contains

    /* Renvoi vrai si le nombre val est dans l'intervalle [ 1 .. count] */
    private  /*@pure@*/ boolean nombreAutorise(int v){
        return (v >= 1 && v <= count());
    } 



    /* ********************************
     * * Opérations de transformation *
     * ********************************/

    /* Remplir une pyramide de façon correcte */
    //@ ensures correcte() ;
    public void remplir(){
        remplir(1);         // on commence a la diagonale 1
    } 

    /* Remplir a partir de la diagonale diag */
    //@ requires diag >= 1 && diag <= hauteur() + 1 && correcte(diag-1) ;
    //@ ensures  (\result && correcte()) || !\result  ;  
    private boolean remplir(int diag){
        /* BEGINKILL */
        // Itération conditionnelle parcourt le domaine [count .. 1]
        // Arret lorsqu'une solution est trouvee

        if (diag > hauteur) 
            return true;

        for (int k=count(); k>=1; k--) {

            // si k est déjà utilisé, on passe au tour de boucle suivant
            if (!contains(k,1,diag)) {

                // Essayer de propager k sur la diagonale

                if (propager(diag,k)) {
                    // La propagation a été faite, on essaie de remplir la suite
                    if (remplir(diag+1)) {
                        // L'appel récursif est une réussite : on tient une solution
                        return true;
                    }
                }
              
            } // k n'est pas encore pris
        }
        return false;
        /* ENDKILL */
    }//remplir(int)


    /*
     * si la propagation de val est possible sur la diagonale diag
     * modifie elements en conséquence et renvoie vrai.
     * si la propagation ne peut pas se faire, renvoie faux
     *
     *   FIXME: on peut utiliser \ensure \forall ici.
     *
     */ 
    //@ requires diag >= 1 && diag <= hauteur() && val >= 1 && val <= count();
    //@ ensures !\result  || (\result && posCorrecte(diag,diag)) ;
    private boolean propager(int diag, int val){
        // On pose val en haut de la diagonale
        setValueAt(val,1,diag);

        // On essaie de propager sur le reste de la diagonale

        /* BEGINKILL */
        // Itération conditionnelle portant sur le domaine [ 2 .. diag ]
        // On s'arrête dès que les contraintes ne sont plus respectées

        int nbre = val;
        for (int lig=2; lig <= diag; lig++) {
            /** invariant
                -- la diagonale diag est <<bien construite>> jusqu'en lig-1
                diagonale_correcte(lig-1,diag)
            **/
            /** variant         diag - lig  **/
            nbre -= valueAt(lig-1,diag-1);    // économise les accès au tableau
            nbre = Math.abs(nbre);
            // On s'assure que le nombre ainsi calculé n'est pas déjà utilisé
            if (contains(nbre,lig,diag))
                return false;

            // Le nombre n'est pas utilisé; on le pose et on continue
            setValueAt(nbre,lig,diag);

        }
        return true;
        /* ENDKILL */
    }//propager(int,int)


} // class Pyramide
