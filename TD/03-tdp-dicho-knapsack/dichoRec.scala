def dicho(tab:Array[Int], elm:Int):Int = {

    def dichoRec(tab:Array[Int], elm:Int, min:Int, max:Int):Int = {
        if (max-min <= 1) {
            if (tab(min) == elm) {
                return min
            } else if (tab(max) == elm) {
                return max
            } else {
                return -1
            }
        } else {
            val milieu = (min+max) / 2;

            if (elm < tab(milieu)) {
                // println("Debut  ("+min+";"+milieu+")");
                return dichoRec(tab,elm,min,milieu);
            } else {
                // println("Fin    ("+milieu+";"+max+")");
                return dichoRec(tab,elm,milieu,max);
            }
        }
    }


    return dichoRec(tab, elm,0, tab.length-1);
}

def test(tab:Array[Int]) {
    def cherche(searched:Int, expected:Int) {
        val found = dicho(tab, searched);
        if (found != expected) 
            println("ERREUR: elm "+searched+" trouvé en "+found+" (attendu en "+expected+").");
        else
            println("elm "+searched+" trouvé en "+found+" comme attendu.");
    }
    cherche(0,-1);
    for (cpt <- 0 to tab.length-1) 
        cherche(tab(cpt),cpt);
    cherche(5,-1);
    cherche(11,-1);
}

test(Array(1,3,6,7,8,9,10))
test(Array(1,3,6,7,9,10))
