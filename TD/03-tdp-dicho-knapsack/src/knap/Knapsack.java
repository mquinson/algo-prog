public class Knapsack {

    /** Les données du problème,
     * On copie ici plutot que de les passer en paramètre de la récursion, 
     * puisqu'ils ne changent pas au cours de la récursion. */
    private int [] valeur;
    private int capacite;
    
    /** La meilleure solution connue pour l'instant. 
     * La fonction récursive a (entre autres) des paramètres du même type 
     * qu'elle modifie lorsqu'elle construit sa solution. Lorsqu'une solution
     * meilleure que la meilleure connue est trouvée, elle est stockée ici.
     */
    private KnapsackSolution meilleure;
    
    
    /* Constructeur */
    public Knapsack(int c, int [] vals){
	capacite = c;
	valeur = vals;

	meilleure = new KnapsackSolution(vals);	
    }
    
    /* quelques Getter pour des attributs privés */
    public int getMeilleureValeur(){return meilleure.getValeur();}
    public boolean [] getMeilleureSolution(){return meilleure.getPris();}


    /* change l'objet en chaine de caractères pour le visualiser. Pratique pour debugger */
    public String toString(){
	return toString(valeur.length);
    }  

    /* idem, mais n'affiche que les premiers objets (ie, une solution partielle) */
    public String toString(int objMax) {
	String s = "";
	s = s + "\nValeurs:  ";
	for (int i=0; i<objMax; i++)
	    s = s + valeur[i] + " ";
	s = s + meilleure.toString();
	s = s + "\nCapacité: " + capacite;
	return s;
    }
 
    private void chercheRec(int profondeur, KnapsackSolution courante) throws Exception {
	System.out.print("(n="+profondeur+") Explore "+courante.toString(profondeur));
	if (courante.getValeur() > capacite) {
	    System.out.println("  *** Oups, ca deborde (backtrack!) ***");
	    return;
	}

	if (courante.getValeur() > meilleure.getValeur()) {
	    System.out.print("   Nouvelle meilleure solution ");
	    meilleure = courante.duplique();
	} else {
	    System.out.print("    ");
	}
       
	if (profondeur == valeur.length) {
	    System.out.println("(Cas terminal)");
	    return;
	} else {       
	    System.out.println("(Cas général)");
	}
       
       
       /*
	if (courante.getValeur() == capacite) {
	    System.out.println("   *** Solution parfaite ***");
	    throw new Exception();
	}*/

	/* Prend l'objet et récurse */
	courante.prendObjet(profondeur);
	chercheRec(profondeur+1, courante);

	/* Pose l'objet et récurse */
	courante.poseObjet(profondeur);
	chercheRec(profondeur+1, courante);
    }

    public void cherche() {
	try {
	    chercheRec(0, new KnapsackSolution(valeur));
	} catch (Exception e) {}
    }
}