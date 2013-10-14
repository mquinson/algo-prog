package recipients;

import java.util.ArrayList;

public class StockagePolynome implements StockageSolution {

	
	private TableauRec tab;
	
	public StockagePolynome( int[] capa ){
		
		ArrayList<Integer> re = new ArrayList<Integer>();
		for( int i = 0 ; i <  capa.length ; i++ ){
			re.add( capa[i] );
		}
		
		tab = new TableauRec( re);
		
	}
	
	public Boolean contains(Solution s) {
		
		ArrayList<Integer> code = code( s );
		
		
		
		Solution sDansTab = tab.recherche(code);
		
		
		
		if( sDansTab == null ){
			
			tab.set( code , s );
			
			return false;
			
		} else {
			System.out.println( sDansTab.getNbOperations());
			if( s.getNbOperations() <  sDansTab.getNbOperations() ){
				
				sDansTab.copiePropriete(s);
				
				tab.set( code , s );
			}
			
			return true;
		}
		
	}
	private ArrayList<Integer> code(Solution s) {
		
		ArrayList<Integer> re = new ArrayList<Integer>();
		for( int i = 1 ; i <  s.getRecipients().length ; i++ ){
			re.add( s.getRecipients()[i].getContenu() );
		}
		return re;
	}
	
	private class TableauRec {  
		
		/// simule une matrice de dimension variable
		
		private TableauRec[] child;
		
		private Solution value;
		
		public TableauRec( ArrayList<Integer> capacites ){
			if( capacites.size() == 0 ){
				
				/// on est sur une feuille
				
			}else{
				child = new TableauRec[ capacites.get(0) +1 ];
				
				
				ArrayList tail = dupliqueArrayList(capacites);
				tail.remove(0);
				
				for( int  i = 0 ; i < child.length ;  i ++ ){
					child[i] = new TableauRec( tail );
				}
			}
		}
		public ArrayList dupliqueArrayList( ArrayList<Integer> a ){
			
			ArrayList b = new ArrayList();
			
			for( int i : a ){
				b.add( i );
			}
			
			return b;
		}
		public void set( ArrayList<Integer> path , Solution s  ){
			
			if( path.size() == 0 ){ 
				value = s;
			} else {
				int head = path.get(0);
				path.remove(0);
				child[ head ].set( path , s );
				
			}
		}
		public Solution recherche( ArrayList<Integer> path ){
			if( path.size() == 0 ){ 
				return value;
			}
			
			int head = path.get(0);
			path.remove(0);
			Solution s = child[ head ].recherche( path );
			
			return s;
		}
		
	}
	
}
