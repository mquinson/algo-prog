package recipients;

import java.util.Hashtable;

public class StockageHashTable implements StockageSolution {

	///pas optimal
	
	
	private Hashtable<String , Solution > table;
	
	public StockageHashTable(){
		table = new Hashtable<String , Solution >();
	}
	
	public Boolean contains(Solution s) {
		String id = s.toString();
		
		if( !table.containsKey( id) ){
			
			table.put( id , s );
			return false;
			
		} else {
			
			Solution sDansTable = table.get(id);
			
			if( s.getNbOperations() < sDansTable.getNbOperations() ){
				System.out.println("Oups, mieux que ce que j'avais avant. Comment est-ce possible dans un parcours en largeur??");
				System.exit(1);
				/* sDansTable.copiePropriete(s); */
				/* table.remove(""+s); */
				/* table.put( ""+s , s ); */
			}
			
			return true;
		}
	
	}
   public int size() {
      return table.size();
   }
   
	
	@Deprecated
	public Boolean estDansLatable( String st ){
		return table.containsKey( st );
	}
	@Deprecated
	public Solution get( String st ){
		return table.get(st);
	}
	@Deprecated
	public void set(Solution s){
		table.remove(""+s);
		table.put( ""+s , s );
	}
}
