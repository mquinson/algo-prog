val hauteur = 5 // défini l'instance du problème
val taille = hauteur*(hauteur+1)/2

// précondition: 1 <= col <= ligne <= hauteur 
def indiceLigne(ligne:Int, col:Int): Int  =  {
 if (ligne<1 || ligne > hauteur)
   println("ligne "+ligne+" pas définie")
 if (col<1 || col>ligne)
   println("col "+col+" pas définie à la ligne "+ligne)
 return ligne * (ligne - 1 ) / 2 + col - 1
}

// BEGIN CORRECTE
def correcte(tab:Array[Int], rang:Int): Boolean = {
  var permutation = new String()
  for (i <- 0 to tab.length-1) permutation += tab(i)

  for (ligne <- 1 to hauteur-1) 
    for (diag <- 1 to ligne) {
      // n3 a forcément l'indice max, pas besoin de tester les autres
      if (indiceLigne(ligne+1, diag+1) <= rang) {
        val n3 = tab(  indiceLigne(ligne+1, diag+1)  )
        val n2 = tab(  indiceLigne(ligne+1, diag)    )
        val n1 = tab(  indiceLigne(ligne,   diag)    )

        if (math.abs(n2-n3) != n1)
          return false
      }
    }
  return true
}
// END CORRECTE

// BEGIN GENERE
def genere(rang:Int, tab:Array[Int]) { // Génère les permutations
  if (rang>=tab.length) {  // On a déjà tout généré, solution correcte!
    
    print("Permutation correcte: ")
    for (i <- 0 to tab.length-1) print(tab(i))
    println("!!")

  } else {                 // Tout n'est pas défini
    for (valeur <- 1 to tab.length) { // pour toutes les valeurs possibles
      var dejaPris = false
      for (i <- 0 to rang-1) 
        if (tab(i) == valeur) 
           dejaPris = true
    
      if (!dejaPris) {  // Si elle n'est pas encore prise, 
        tab(rang) = valeur  // on la prend
        if (correcte(tab, rang)) // Appel récursif SSI solution partielle correcte
           genere(rang+1, tab) // et on considère les cases suivantes
      }
    }
  }
}
// END GENERE

val before=System.nanoTime()
genere(0, Array.fill(taille)(0)) 
val after=System.nanoTime()

println("Took "+(after-before)+"ns")


