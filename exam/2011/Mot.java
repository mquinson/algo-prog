import java.util.Hashtable;

public class Mot {
   Hashtable<String,String> dict = new Hashtable<String,String>();
   public String chercher(String prefix, char[] lettres, int k) {
       if (k==0) { // fini, c'est assez long on a notre mot
	 if (dict.containsKey(prefix))
	   return prefix; // cool on a notre mot
	 else 
	   return null; // damnit, on a genere du caca
      }
       // Si on est ici, c'est que c'est pas assez long. Ajoutons la lettre suivante alors. 
       // On teste tour a tour de prendre chacune des lettres possibles a cette position avant de faire les suivantes
      for (int i=0;i<lettres.length;i++) {
	  if (lettres[i] != '*') { // lettre pas encore prise pour les positions precedentes
	      char c = lettres[i];
	      lettres[i]='*'; // on la prend
	      String res = chercher(prefix+lettres[i],next,k-1);
	      if (res != null)
		  return res;
	      lettres[i]=c; // on restaure le tableau dans l'etat ou on l'a trouve
	  }
      }       
      return null;
   }
   
   public Mot() {
      char[] lettres = new char[] { '1','2','3'};
      
      // Ahem, cette partie n'est pas faite. Mais c'est pas dans l'enonce, non plus
      dict.put("31","");

      // On lance la demo (sur des chiffres au lieu de lettres, sans raison apparente)
      System.out.println("found: "+chercher("",lettres,2));
   }
   
   public static void main(String[] args) {
      new Mot();
   }   
}
