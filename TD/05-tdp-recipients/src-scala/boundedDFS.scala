
// BEGIN HELPERS
def remplir(obj:Int, capa:Array[Int], ctn:Array[Int]) { ctn(obj) = capa(obj) }
def vider  (obj:Int, capa:Array[Int], ctn:Array[Int]) { ctn(obj) = 0         }
def transvaser(src:Int, dst:Int, capa:Array[Int], ctn:Array[Int]) {
   val placeRestante = capa(dst) - ctn(dst)          // pas plus haut que le bord
   val transvasé = math.min(placeRestante, ctn(src)) // pas plus que dispo
   ctn(dst) += transvasé
   ctn(src) -= transvasé
}
// END HELPERS

// BEGIN SOLVE
val debug = false
def solve(rang:Int, capa:Array[Int], ctn:Array[Int], cible:Int):Boolean = { // Boolean pour Q8
    val NB_RECIPIENTS = capa.length

    // Ajout Q8: pour afficher le résultat une fois trouvée
    for (obj <- 0 to ctn.length - 1) 
        if (ctn(obj) == cible) {// On a trouvé! Retourne vrai pour le dire
            println("TROUVÉ!! Voici les coups menant à la réponse (en sens inverse)")
            return true
        }
    // Fin ajout Q8: affichage du résultat trouvé    

    // Ajout Q5: profondeur bornée
    if (rang == 0) 
        return false // Q7: retourne faux car on n'a rien trouvé
    // Fin ajout Q5: profondeur bornée

    for (src <- 0 to NB_RECIPIENTS) {     // on choisit un recipient source 
        for (dst <- 0 to NB_RECIPIENTS) { // et un recipient cible ... 
            if (src != dst) {

                // Ajout Q4: sauvegarde de l'état à restaurer
                val exsrc = if (src < NB_RECIPIENTS) ctn(src) else -1
                val exdst = if (dst < NB_RECIPIENTS) ctn(dst) else -1
                // Fin ajout Q4: sauvegarde de l'état à restaurer
                                                                       
                // Choisi et applique une décision
                var faitQQCH = false // Q7: evite les no-op
                if (src==NB_RECIPIENTS) { // on remplit depuis la fontaine
                    if (ctn(dst) != capa(dst)) { // Q7: evite les no-op
                        if (debug) print(rang+": remplir "+dst+" ~> "); // DEBUG
                        remplir(dst,capa,ctn)
                        faitQQCH = true
                    } 
                } else if (dst==NB_RECIPIENTS) { // on vide dans la fontaine
                    if (ctn(src) != 0) {  // Q7: evite les no-op
                        if (debug) print(rang+": vider "+src+" ~> "); // DEBUG
                        vider(src, capa, ctn)
                        faitQQCH = true
                    }
                } else {
                    if (ctn(src) != 0 && ctn(dst) != capa(dst)) { // Q7: evite les no-op
                        if (debug) print(rang+": transvaser "+src+" dans "+dst+" ~> "); // DEBUG
                        transvaser(src,dst, capa, ctn)
                        faitQQCH = true
                    }
                }
                if (faitQQCH) { // Q7: evite les no-op
                    for (i <- 0 to ctn.length-1) 
                        if (debug) print(ctn(i)+",");
                    if (debug) println();
            
                val trouvé = solve(rang-1, capa, ctn, cible) // version avant Q8
                        
                // Ajout Q8: affichage du résultat quand on le trouve
                if (trouvé) { // Ce chemin mène à un succès.
                    if (src==NB_RECIPIENTS) { 
                        print("Remplir "+dst+" a la fontaine. Situation après ce coup: {");
                    } else if (dst==NB_RECIPIENTS) {
                        print("Vider "+src+" dans la fontaine. Situation après ce coup: {");
                    } else {
                        print("Transvaser "+src+" dans "+dst+". Situation après ce coup: {");
                    }
                    for (i <- 0 to ctn.length-1) 
                        print((if (i!=0) ", " else "")+(ctn(i)));
                    println("}");
                } // Fin ajout Q8: affichage de la réponse trouvée

                // Ajout Q4: restauration de l'état sauvegardé
                if (src < NB_RECIPIENTS)
                    ctn(src) = exsrc 
                if (dst < NB_RECIPIENTS)
                    ctn(dst) = exdst 
                // Fin ajout Q4: restauration de l'état sauvegardé

                if (trouvé)
                    return true; // On a trouvé donc on coupe.

                } // fin Q7: fait des choses que si le coup n'est pas une no-op
            }
        } 
    }      
    return false // Ajout Q8: affichage du résultat
}
// END SOLVE

def cherche(profMax:Int, capa:Array[Int], cible:Int) {
    println("--------")
    print("Cherche "+cible+" avec les capacités {");
    for (i <- 0 to capa.length-1) 
        print((if (i!=0) ", " else "")+(capa(i)));
    println("}. Profondeur max: "+profMax);

    if (!solve(profMax, capa, Array.fill(capa.length)(0), cible))
        println("Pas de solution trouvée")
}

cherche(9, Array(5,7), 4); // L'exemple de l'énoncé

cherche(4, Array(8,5,3), 6); // La question suivante

cherche(150, Array(100,24,25) ,42) // l'instance d'Oswald
