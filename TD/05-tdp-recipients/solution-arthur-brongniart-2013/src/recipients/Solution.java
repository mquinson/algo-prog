package recipients;


public class Solution {
	
	/// une solution contient un tableau des recipients dans un etat donné
	/// ces recipients n'appartiennent qu'a la solution (reference unique)
	/// le parent est la solution a partir de laquelle on obtient cette solution (de part le parcours en largeur on est sûrs de l'optimalité en nb de transvasement)
	/// operationDeNaissance est l'operation effectuee sur parent pour obtenir la solution courante
	
	private Recipient[] recipients;  /// le 1er indice contient la fontaine , puis les differents recipients
	
	private Solution parent;
		
	private int profondeur=0; // longueur du chemin qui mène a cet état depuis la situation initiale
	
	private Operation operationDeNaissance;
		
	public Solution( int[] tableauDeCapacite ){ /// constructeur appel� a l'initialisation
		
		recipients  = new Recipient[ tableauDeCapacite.length +1 ];
		
		int capaciteMax = 0;
		
		for( int i =  1 ; i < recipients.length ; i++ ){
			recipients[i] = new Recipient( tableauDeCapacite[i-1] );
			
			capaciteMax = Math.max( capaciteMax ,  tableauDeCapacite[i-1] ); /// recherche du maximum
		}
		
		recipients[0] = new Fontaine( capaciteMax+1 );
		
	}
	
	public Solution( Solution s ){  ///constructeur clone ( duplique le tableau de recipient )
		
		recipients = new Recipient[ s.getRecipients().length ];
		profondeur=s.profondeur;
		
		for( int i = 0 ; i < s.getRecipients().length ; i ++ ){
			
			recipients[i] = s.getRecipients()[i].clone();
			
		}
	}
	public Solution( Solution s , Operation op ){ /// duplique l'objet solution puis applique l'operation
		
		this( s );
		
		operationDeNaissance = op;
		parent = s;
		profondeur=s.profondeur+1;
		
		recipients[ op.source ].tranvaseDans( recipients[ op.cible ] );
		
	}
	
	public Boolean estSolution( int capacite ){ /// si un des recipients possede le contenu recherch�
		
		for( Recipient r : recipients ){
			
			if( r.getContenu() == capacite ) return true;
			
		}
		return false;
	}

	public Recipient[] getRecipients() {
		return recipients;
	}
	
	
	
	public int getNbOperations() {
		return profondeur;
	}

	public Operation getOperationDeNaissance() {
		return operationDeNaissance;
	}
	
	public String toString(){
		
		String re = "";
		for( Recipient r : recipients ){
			re += " . "+r;
		}
		return re;
	}

	public Solution getParent() {
		return parent;
	}

	@Deprecated
	public void copiePropriete(Solution s) { 
		
		/// on a s qui decrit la meme situation que this, mais s y arrive plus rapidement, 
		/// on copie le parcourt de s qui est meilleur , ainsi si une solution fille de this termine, on aura un chemin optimal
		/// le nbOperation des fils est mis a jour automatiquement, ce qui est necessaire puisque que c'est uen condition d'arret
		
		parent = s.getParent();
		operationDeNaissance = s.getOperationDeNaissance();
	}

	public String asciiArt() {
		String re = "";
		for( int i = 1 ; i < recipients.length ; i ++ ){
			re += "  "+recipients[i];
			//re += "\n"+recipients[i].asciiArt();
		}
		return re;
	}
}
