
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
def trouvé(candidat:Array[Int], cible:Int):Boolean = {
    for (i<-0 to candidat.length-1)
        if (candidat(i) == cible)
            return true;
    return false;
}

// BEGIN SOLVE
var vus:List[Array[Int]] = Nil // Tous les états déjà vus
def solve(prof:Int, capa:Array[Int], ctn:Array[Int], cible:Int):Boolean = { o
    val NB_RECIPIENTS = capa.length

    if (trouvé(ctn, cible)) {
        println("TROUVÉ!! Voici les "+prof+" coups menant à la réponse (en sens inverse). "+vus.size+" états mémoizés");
        return true;
    }

    for (src <- 0 to NB_RECIPIENTS) {     // on choisit un recipient source 
        for (dst <- 0 to NB_RECIPIENTS) { // et un recipient cible ... 

            if  (   (src == dst)
                 || (src==NB_RECIPIENTS && ctn(dst) == capa(dst))
                 || (dst==NB_RECIPIENTS && ctn(src) == 0)
                 || (src!=NB_RECIPIENTS && dst!=NB_RECIPIENTS && (ctn(src) == 0 || ctn(dst) == capa(dst))) 
                    ) {                
                /* rien à faire dans le cas des no-op */

            } else {
                var cpy = duplique(ctn)

                if (src==NB_RECIPIENTS) { // on remplit depuis la fontaine
                    remplir(dst,capa,cpy)
                } else if (dst==NB_RECIPIENTS) { // on vide dans la fontaine
                    vider(src, capa, cpy)
                } else {
                    transvaser(src,dst, capa, cpy)
                }

                if (!contient(vus, cpy)) {
                    vus = cpy :: vus;
                    val trouvé = solve(prof+1,capa, cpy, cible)

                    if (trouvé) { // Ce chemin mène à un succès.
                        if (src==NB_RECIPIENTS) { 
                            print("Remplir "+dst+" à la fontaine. ");
                        } else if (dst==NB_RECIPIENTS) {
                            print("Vider "+src+" dans la fontaine. ");
                        } else {
                            print("Transvaser "+src+" dans "+dst+". ");
                        }
                        affiche("Situation après ce coup: {",cpy,"}")
                        return true
                    }
                }
            }
        } 
    }      
    return false 
}
// END SOLVE

def cherche(capa:Array[Int], cible:Int) {
    println("--------");
    affiche("Cherche "+cible+" avec les capacités {",capa,"}.")

    vus = Array.fill(capa.length)(0)::Nil

    if (!solve(0,capa, Array.fill(capa.length)(0), cible))
        println("Pas de solution trouvée")
}

cherche(Array(5,7), 4); // L'exemple de l'énoncé

cherche(Array(8,5,3), 6); // La question suivante

cherche(Array(100,24,25) ,42); // l'instance d'Oswald

cherche(Array(34, 55, 89, 144), 1); // L'instance d'Émmanuel
