public class Test {      
    
    public static void main(String[] args) {
	int valeurs [] = {5,4,3,2};
	int capacite = 10;

       if (args.length > 0) {
	  capacite = Integer.parseInt(args[0]);
	  valeurs = new int[args.length-1];
	  for (int i=1;i<args.length;i++) {
	     valeurs[i-1] = Integer.parseInt(args[i]);
	  }	  
       }
       
       
	Knapsack KS = new Knapsack(capacite, valeurs);
        System.out.print("Test de knapsack (capacite:"+capacite+" ; valeurs:");
       for (int i=0;i<valeurs.length;i++) {
	  if (i!=0) System.out.print(",");
	  System.out.print(valeurs[i]);
       }
       System.out.println(")");
	KS.cherche();
	System.out.println("Meilleure solution: "+KS);
		
    }
}
