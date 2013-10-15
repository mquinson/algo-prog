val hauteur = 3 // défini l'instance du problème
val taille = hauteur*(hauteur+1)/2

// précondition: 1 <= ligne <= diag <= hauteur 
def indiceDiag(ligne:Int, diag:Int):Int = {
    if (diag<1 || diag >hauteur) 
       println("diag "+diag+" pas définie")
     if (ligne<1 || ligne>diag)
       println("ligne "+ligne+" pas définie sur la diagonale "+diag)

    return diag * (diag - 1 ) / 2 + ligne - 1
}

// BEGIN CORRECTE
def correcteDiag(tab:Array[Int], rang:Int): Boolean = {
  var permutation = new String()
  for (i <- 0 to tab.length-1) permutation += tab(i)

  for (diag <- 1 to hauteur) 
    for (ligne <- 2 to diag) {
      // n3 a forcément l'indice max, pas besoin de tester les autres
      if (indiceDiag(ligne, diag) <= rang) {
        val n1 = tab(  indiceDiag(ligne-1, diag-1)  )
        val n2 = tab(  indiceDiag(ligne-1, diag)    )
        val n3 = tab(  indiceDiag(ligne,   diag)    )

        if (math.abs(n1-n2) != n3)
          return false
      }
    }
  return true
}
// END CORRECTE

def propage(tab:Array[Int], value:Int, diag:Int) {
    tab( indiceDiag(1, diag)  ) = value
}

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
        if (correcteDiag(tab, rang)) // Appel récursif SSI solution partielle correcte
           genere(rang+1, tab) // et on considère les cases suivantes
      }
    }
  }
}
// END GENERE

val before=System.nanoTime()
genere(0, Array.fill(taille)(0)) 
val after=System.nanoTime()

println("Took "+((after-before)/1000000000)+"."+((after-before)%1000000000)+"s")


