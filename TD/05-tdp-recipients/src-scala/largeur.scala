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

def egal(src:Array[Int], dst:Array[Int]):Boolean= {
    for (i <- 0 to src.length-1)
        if (src(i) != dst(i))
            return false;
    return true;
}
def contient(liste:List[Array[Int]], elm:Array[Int]):Boolean= {
    for (e <- liste) 
        if (egal(e,elm))
            return true;
    return false;
}

def trouvé(candidat:Array[Int], cible:Int):Boolean = {
    for (i<-0 to candidat.length-1)
        if (candidat(i) == cible)
            return true;
    return false;
}
def duplique(src:Array[Int]): Array[Int] = {
    var res = Array.fill(src.length)(0)
    for (i<-0 to src.length-1)
        res(i) = src(i);
    return res;
}
def affiche(pre:String, tab:Array[Int], post:String) {
    print(pre)
    for (i <- 0 to tab.length-1) 
        print((if (i!=0) ", " else "")+(tab(i)));
    println(post)
}
// BEGIN CHERCHE
var vus:List[Array[Int]] = Nil;
def cherche(capa:Array[Int], cible:Int):Boolean = {
    val NB_RECIPIENTS = capa.length
    def lambda(rang:Int):Boolean= {   
        println("Profondeur "+rang+". Nombres de solutions pour l'instant: "+(vus.size))
        for (old <- vus) {
            for (src <- 0 to NB_RECIPIENTS) {     // on choisit un recipient source 
                for (dst <- 0 to NB_RECIPIENTS) { // et un recipient cible ... 
                    var faitQQCH = false;
                    var copy = duplique(old);
                    if (src == dst) {
                        /* rien */
                    } else if (src==NB_RECIPIENTS) { // on remplit depuis la fontaine
                        if (copy(dst) != capa(dst)) {
                            remplir(dst,capa,copy);
                            faitQQCH = true
                        }
                    } else if (dst==NB_RECIPIENTS) { // on vide dans la fontaine
                        if (copy(src) != 0) {
                            vider(src, capa, copy);
                            faitQQCH = true;
                        }
                    } else {
                        if (copy(src) != 0 && copy(dst) != capa(dst)) { 
                            transvaser(src,dst, capa, copy);
                            faitQQCH = true;
                        }
                    } 
                    if (trouvé(copy, cible)) {
                        println("Trouvé en "+(rang+1)+" coups");
                        return true;
                    }
                    // 
                    // La question 2 de l'exercice 5 est simplement d'ajouter le !contient ci-dessous
                    if (faitQQCH && !contient(vus,copy)) { 
                        // affiche("Pas bon. ",copy,"");
                        vus = copy :: vus;
                    }
                }
            }
        }
        return lambda(rang+1)
    }   
    print("Cherche "+cible+" avec les capacités {");
    for (i <- 0 to capa.length-1) 
        print((if (i!=0) ", " else "")+(capa(i)));
    println("}.");

    vus =  Array.fill(capa.length)(0):: Nil
    return lambda(0)
}
// END CHERCHE

cherche(Array(5,7), 4); // L'exemple de l'énoncé

//cherche(Array(100,24,25), 42)
// java.lang.OutOfMemoryError: GC overhead limit exceeded

//cherche(Array(34, 55, 89, 144), 1); // L'instance d'Émmanuel
