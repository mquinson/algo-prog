package recipients;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private Solveur solveur;
	public static void main(String[] args) {
//	   int[] capa = new int[] {8, 13, 21}; // Trouvé! Depth:14; 3618 steps done; 354 unique states visited; Still 618 states to explore
//	   int[] capa = new int[] {13, 21, 34}; // Trouvé! Depth:24; 11601 steps done; 1057 unique states visited; Still 1071 states to explore
//	   int[] capa = new int[] {21, 34, 55}; // Trouvé! Depth:40; 33138 steps done; 2911 unique states visited; Still 1782 states to explore	   
//	   int[] capa = new int[] {34, 55, 89}; // Trouvé! Depth:66; 92937 steps done; 7994 unique states visited; Still 2979 states to explore
//	   int[] capa = new int[] {55, 89, 144}; // Trouvé! Depth:108; 251994 steps done; 21413 unique states visited; Still 4950 states to explore	   
//	   int[] capa = new int[] {89, 144, 233}; // Trouvé! Depth:176; 677601 steps done; 57140 unique states visited; Still 8067 states to explore
//	   int[] capa = new int[] {144, 233, 377}; // Trouvé! Depth:286; 1798566 steps done; 150984 unique states visited; Still 13230 states to explore
//	   int[] capa = new int[] {233, 377, 610}; // Trouvé! Depth:464; 4758417 steps done; 398318 unique states visited; Still 21387 states to explore
//	   int[] capa = new int[] {377, 610, 987}; // Trouvé! Depth:752; 12524034 steps done; 1046573 unique states visited; Still 34830 states to explore
//	   int[] capa = new int[] {610, 987}; // Trouvé! Depth:1972; 19139 steps done; 3192 unique states visited; Still 1 states to explore
//	   int[] capa = new int[] {987,1597}; // Trouvé! Depth:1972; 23657 steps done; 3945 unique states visited; Still 7 states to explore
	   int[] capa = new int[] {1597,2584}; // Trouvé! Depth:5166; 50147 steps done; 8360 unique states visited; Still 1 states to explore
	   
	   
//	   
//	   int[] capa = new int[] {1597, 2584, 4181}; // Depth:1095; 26600000 steps done; 2220515 unique states visited; Still 46180 states to explore

	   Solveur solveur = new Solveur (capa, 1);
	   solveur.resoudre();
	   System.out.println(solveur.getMeilleure()+"\nNb operations:"+solveur.getMeilleure().getNbOperations());
//	   System.out.println(solveur.remonteParent(solveur.getMeilleure()));
	   /*
		int[] capa = new int[4];
		
		int meilleureDepth=0;
		for (int a=10;a<100;a++) {
			for (int b=10;b<100;b++) {
				for (int c=10;c<100;c++) {
					for (int d=10;d<100;d++) {
						capa = new int[] {a,b,c,d};
						for (int target=10;target <100;target++) {
							Solveur solveur = new Solveur( capa, target );
							if (!solveur.resoudre()) {
								//					System.out.println("("+capa[0]+","+capa[1]+","+capa[2]+"->"+target+" insoluble");
							} else if (solveur.getMeilleure().getNbOperations()>meilleureDepth) {
								meilleureDepth=solveur.getMeilleure().getNbOperations();
								System.out.println("("+capa[0]+","+capa[1]+","+capa[2]+","+capa[3]+")->"+target+" a un optimal en "+meilleureDepth);
							}
						}
					}
				}
			}
		}
	*/	
		//double now = System.currentTimeMillis();
		
		//System.out.println( solveur.resoudre()+ "\ntrouvé en "+
		//		(System.currentTimeMillis()-now)+" ms" );
		
//		new Main();

	}
	
	public Main(){
		
		ArrayList<Integer> capa = new ArrayList<Integer>();
		
		Boolean enougth = false;
		
		write( "entrer les capacit�s des recipients une � une ");
		while( !enougth ){
			String rep = read();
			
			try{
				int i = Integer.decode(rep);
				capa.add( i );
				write( "capacit�s : "+printArrayList(capa) );
				write( "encore une? (y/n)");
				
				
				
				enougth = !YesNo();
				
				if(!enougth )write( "capacite suivante :");
				
			}catch( NumberFormatException e ){
				write( "(ca doit �tre un entier hein ) ");
			}
		}
		
		int[] capaTab = new int[capa.size()];
		for( int i = 0 ; i < capaTab.length ; i ++ ){
			capaTab[i] = capa.get(i);
		}
		
		while( true ){
			
		
			write( "capacite a atteindre :");
			String rep = read();
			try{
				int capaAAtteindre = Integer.decode(rep);
				
				solveur = new Solveur( capaTab ,  capaAAtteindre );
				
				double now = System.currentTimeMillis();
				solveur.resoudre();
				write( solveur.remonteParent(solveur.getMeilleure()) );
				
				write( "\n solution trouv�e en "+ (System.currentTimeMillis()-now)+" ms" );
				
			}catch( NumberFormatException e ){
				write( "(ca doit �tre un entier hein ) ");
			}
			
			
			
			write( "\n rejouer (y/n)");
			
			if( !YesNo() )break;
		}
		
		write("aurevoir");
	}
	
	public Boolean YesNo(){
		String rep = read();
		if( rep.equals("y") ){
			return true;
			
		}else
		if( rep.equals("n") ){
			return false;
		}else{
			write( "taper y pour oui , n pour non" );
			return  YesNo();
		}
	}

	public void write( String s ){
		System.out.println( s );
	}
	public String read(){
		 Scanner sc = new Scanner(System.in);
	     return sc.next();
	}
	public String printArrayList( ArrayList<Integer> a ){
		String re="[";
		for( int i : a ){
			re += i+" ,";
		}
		return re.substring(0, Math.max( 0, re.length()-2) )+"]";
	}
}
