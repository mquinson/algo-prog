package recipients;

import java.util.ArrayList;

public class Solveur {



	private int[] capacites;

	private int capaciteCible;
	private Solution meilleureSolution ;

	public Solveur(int[] capacites, int capaciteCible) {
		/*
		System.out.print("New solveur: (");
		for (int i:capacites) 
			System.out.print(i+",");
		System.out.println(")->"+capaciteCible);
		*/
		this.capacites = capacites;
		this.capaciteCible = capaciteCible;
	}

	public boolean resoudre(){
	   long step=-1;
		// on effectue une pseudo recursion:
		// * on empile les solutions a traiter, a chaque tour de boucle, 
		//   on traite une solution et on empile ses solutions filles 
		//   (comme on le ferait avec une fonction recursive )
		// * quand on trouve une solution, on est pas sûr que ca soit la meilleure,
		// * on laisse l'algorithme en chercher d'autre TANT QUE la solution 
		//   courante a moins d'operation que la meilleure solution 
		//   (sinon ca ne sert a rien de continuer, elle sera de toute facon plus mauvaise)


		// Pour vérifier l'originalité des solutions visitées
		StockageHashTable stock = new StockageHashTable();  

		// File pour stocker le travail restant
		ArrayList<Solution> todo = new ArrayList<Solution>(); 
		todo.add( new Solution( capacites )); // ajout de la situation initiale

		Boolean solutionTrouvee = false;
		meilleureSolution = todo.get(0); 

		while( !todo.isEmpty() ){  ///tant qu'il reste du taf
		   Solution s = todo.remove(0); ///premier element de la file
		   if ((++step) % 100000 ==0)
		     {
			System.out.println("Depth:"+s.getNbOperations()+"; "+step+" steps done; "+stock.size()+" unique states visited; Still "+todo.size()+" states to explore");
		     }
		   

			// Si on a plus d'espoir de trouver une meilleure solution, on coupe la boucle
			if (solutionTrouvee && meilleureSolution.getNbOperations() >= s.getNbOperations() )
				break;

			// Si c'est un état déjà visité, on continue avec le suivant
			if (stock.contains(s))
				continue;

			if( s.estSolution(capaciteCible) ){ 
				solutionTrouvee = true;
				meilleureSolution = s;

			} else { // ajout des enfants a la file
				for( int source = 0 ; source < capacites.length+1 ; source ++ ){
					for( int cible = 0 ; cible < capacites.length+1 ; cible ++ ){

						if( cible != source ){
							todo.add( new Solution( s , new Operation( cible , source ) ) );
						}
					}
				}
			}

		}
		if( !solutionTrouvee ) {
			//System.out.println("insoluble");
			return false;
		}
		
	   System.out.println("Trouvé! Depth:"+meilleureSolution.getNbOperations()+"; "+step+" steps done; "+stock.size()+" unique states visited; Still "+todo.size()+" states to explore");
	   return true;
	}
	public String remonteParent(Solution s ){
		if( s != null ){
			return remonteParent(s.getParent())+"\n"+
				( (s.getOperationDeNaissance()==null)?"":(s.getOperationDeNaissance()) )+"\n"+
				s.asciiArt();
		}
		return "";
	}
	public Solution getMeilleure() {
		return meilleureSolution;
	}

}
