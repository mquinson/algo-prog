val hauteur = 3 // défini l'instance du problème
val taille = hauteur*(hauteur+1)/2

var permutations:List[Array[Int]] = Nil // Là où on va stocker les permutations

def duplique(src:Array[Int]):Array[Int] = {
  val dst=Array.fill(src.length)(0)
  for (i <- 0 to src.length-1) 
    dst(i) = src(i)
  return dst
}

def genere(rang:Int, tab:Array[Int]) { // Génère les permutations
  if (rang>=tab.length) {  // On a déjà tout généré, on arrête
    permutations = duplique(tab)::permutations // On sauve une copie dans la liste générée
    if (permutations.size % 1000 == 0){ // affiche l'état courant pour quand ca dure
        for (i <- 0 to tab.length-1) print(tab(i))
        print(" ")
    }

  } else {                 // Tout n'est pas défini
    for (valeur <- 1 to tab.length) { // pour toutes les valeurs possibles
      var dejaPris = false
      for (i <- 0 to rang-1) 
        if (tab(i) == valeur) 
           dejaPris = true
    
      if (!dejaPris) {  // Si elle n'est pas encore prise, 
        tab(rang) = valeur  // on la prend
        genere(rang+1, tab) // et on considère les cases suivantes
      }
    }
  }
}

genere(0, Array.fill(taille)(0)) // Le paramètre qui dit la taille, c'est le paramètre de fill (6 ici)

println("Trouvé "+permutations.size+" permutations")
for (tab <- permutations) {
  for (i <- 0 to tab.length-1) print(tab(i))
  print(" ")
}
println

// Fin génération, début du test

// précondition: ligne >= 1 && ligne <= hauteur && diag <= ligne && diag <= hauteur
def indiceLigne(ligne:Int, diag:Int): Int  = {
 if (ligne<1 || ligne > hauteur)
   println("ligne "+ligne+" pas définie")
 if (diag>ligne || diag > hauteur)
   println("diag "+diag+" pas définie à la ligne "+ligne)
 return ligne * (ligne - 1 ) / 2 + diag - 1
}

def correcte(tab:Array[Int]): Boolean = {
  var permutation = new String()
  for (i <- 0 to tab.length-1) permutation += tab(i)

  for (ligne <- 1 to hauteur-1) 
    for (diag <- 1 to ligne-1) {
      println(permutation+"("+ligne+","+diag+") ")
      val n1 = tab(  indiceLigne(ligne+1, diag+1)  )
      val n2 = tab(  indiceLigne(ligne+1, diag)    )
      val n3 = tab(  indiceLigne(ligne,   diag)    )

//      println(permutation+"("+ligne+","+diag+"): "+n3+". "+n1+"-"+n2+"="+math.abs(n1-n2))
      if (math.abs(n1-n2) != n3)
        return false
    }
  return true
}

println("Filtre les pyramides correctes")
for (permutation <- permutations) 
  if (correcte(permutation)) {
    print("Permutation correcte: ")
    for (i <- 0 to permutation.length-1) print(permutation(i))
    println("!!")
  }
println("Fin de la recherche")