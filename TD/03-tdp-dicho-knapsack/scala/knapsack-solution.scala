/** Problème du sac à dos */

object knapsack { // KILLLINE

// Diverses fonctions d'aide
////////////////////////////

/** Calcule le gain d'un sac à dos donné */
def valeurTotale(objets:Array[Boolean], poids:Array[Int]):Int = {
  var total = 0
  for (i <- 0 to objets.length -1)
    if (objets(i)) 
      total += poids(i)
  return total
}

/** Affiche si les objets sont pris ou non dans une solution partielle (en cours de construction)
 *
 * Le second argument est la taille déja construite.
 */
def affiche(objets:Array[Boolean], objetMax:Int) {
	      
  // on utilise un min pour se protéger du cas (fréquent) où cette
  // fonction est appellée avec un second argument supérieur à la
  // taille du tableau.
  for (i <- 0 to math.min(objetMax, objets.length-1))
    if (objets(i))
      print(" O ")
    else
      print(" N ")
  if (objetMax < objets.length-1)
    for (i <- objetMax to objets.length-2)
      print("...")
  print(";")
}


/** Dupplique un sac dans un autre, pour se souvenir de la meilleure solution connue */
def dupplique(src:Array[Boolean], dst:Array[Boolean]) {
  for (i <- 0 to src.length-1) 
    dst(i) = src(i)
}

/** indique dans le paramètre que l'objet spécifié est maintenant pris
    (cette fonction est juste là pour se simplifier la vie) */
def mettreDansSac(objets:Array[Boolean], obj:Int) {
    if (objets(obj)) 
        println("L'objet "+obj+" est déjà pris; ignore la requête.");
    objets(obj) = true
}
/** indique dans le paramètre que l'objet spécifié est maintenant posé */
def retireDuSac(objets:Array[Boolean], obj:Int) {
    if (!objets(obj)) 
        println("L'objet "+obj+" est déjà posé; ignore la requête.");
     objets(obj) = false
}

// La fonction principale
/////////////////////////

/** La fonction publique, pour chercher la meilleure solution */
def cherche(poids:Array[Int] , capacite:Int) {

  // on va beaucoup utiliser cette valeur, alors on fait un alias 
  // pour simplifier les écritures suivantes
  val len = poids.length
  
  // Affiche l'instance du problème
  print("Poids des objets: ")
  for (i <- 0 to poids.length-1) 
    print(" "+poids(i)+" ")
  println("; Capacite: "+capacite)
  
  // variable locale pour sauvegarder la meilleure solution connue à tout moment
  var meilleure:Array[Boolean] = Array.fill(len)(false)

  // BEGINKILL TODO: Placez ici l'appel récursif, avec les valeurs initiales des paramètres
  // Initialise l'appel récursif
  chercheRec(0,  // On commence le remplissage au rang 0
             Array.fill(len)(false) // Cette écriture crée un tableau
                                    // dans lequel la valeur 'false'
                                    //est répétée 'len' fois.
  ) // fin des paramètres de chercheRec. La récursion est lancée.
  // ENDKILL

  // Affiche la meilleure solution trouvée
  println
  print("Meilleure solution trouvée:")
  for (i <- 0 to meilleure.length-1)
    print(" "+poids(i)+":"+(if (meilleure(i)) "O" else "N")+";")
  println(" Valeur:"+valeurTotale(meilleure,poids)+" (la capacité était "+capacite+")")
  
  
  // BEGINKILL TODO: Definissez ici l'appel récursif à proprement parler
  // Défini l'appel récursif
  def chercheRec(profondeur:Int, courante:Array[Boolean]) {

    val valeur = valeurTotale(courante,poids)
    
    print(" (prof="+profondeur+") Explore ")
    affiche(courante,profondeur)
    print(" Valeur: "+valeur)
    
    if (valeur > capacite) {
      println(" *** Oups, ca deborde (backtrack!) ***")
      return
    }
    if (valeurTotale(courante, poids) > valeurTotale(meilleure, poids)) {
      dupplique(courante,meilleure)
      print(" Nouvelle meilleure solution ");
    } else {
      print("   ")
    }
    if (profondeur == poids.length) {
      println("(Cas terminal)");
      return;
    } else {       
      System.out.println("(Cas général)");
    }
						           
					
    /* Prend l'objet et récurse */
    mettreDansSac(courante,profondeur);
    chercheRec(profondeur+1, courante);
	
    /* Pose l'objet et récurse */
    retireDuSac(courante, profondeur);
    chercheRec(profondeur+1, courante);
				
  }
  // ENDKILL
} // Fin de la fonction cherche() principale



// Le code de test, qui appelle la fonction publique
////////////////////////////////////////////////////
/* KILLLINE */def main(args:Array[String]) { 
/* KILLLINE */  if (args.length == 0) {
/* KILLLINE */    println("Usage: knapsack <capacite> <obj1> <obj2> ... <objN>")
/* KILLLINE */    println("Arguments par défaut: 10 5 3 2")
cherche(Array(5,4,3,2), 10)
/* KILLLINE */    
/* KILLLINE */  } else {  
/* KILLLINE */    val capa = args(0).toInt
/* KILLLINE */    val objets:Array[Int] = new Array(args.length-1)
/* KILLLINE */    for (i <- 0 to objets.length-1)
/* KILLLINE */      objets(i) = args(i+1).toInt
/* KILLLINE */    cherche(objets, capa)  
/* KILLLINE */  }
/* KILLLINE */}
/* KILLLINE */
/* KILLLINE */}