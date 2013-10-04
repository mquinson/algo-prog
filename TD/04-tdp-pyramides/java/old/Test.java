/* -*- coding: utf-8 -*-  */
/**
 * Classe de test de pyramide
 * @author Martine Gautier Faculté des Sciences - UHP Nancy I
 * @author Martin Quinson ESIAL -- UHP Nancy I
 * @version Janvier 2006
 */
package pyramide ;
public class Test {
   
  public static void main (String[] args) {

      if (args.length == 0) {
	  System.out.println("Usage: pyramide.Test <hauteur>");
	  System.exit(1);
      }
	  
      int taille = Integer.parseInt(args[0]) ;
      Pyramide pyr = new Pyramide(taille);
      pyr.remplir();
      
      if (pyr.correcte()) 
        System.out.println(pyr);
      else	
        System.out.println("Pas de solution !!");

      System.exit(0);
  }// main

}// class
