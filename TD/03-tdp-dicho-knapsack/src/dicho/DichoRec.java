public class DichoRec {
  public static int dichoRec(int tab[], int min, int max, int elem) {
    System.out.println("min: "+min+"; max: "+max);
    if (max - min <= 1) {
      if (tab[min] == elem) {
        return min;
      } else if (tab[max] == elem) {
        return max;
      } else {
        return -1;
      }
    } else {
      int milieu = (min + max) / 2;
      
      if (elem < tab[milieu]) {
        System.out.println("Debut ("+min+";"+milieu+")");
        return dichoRec(tab,min,milieu,elem);
      } else {
        System.out.println("Fin   ("+milieu+";"+max+")");
        return dichoRec(tab,milieu,max,elem);
      }
    }
  }
  public static void main(String[] args) {
     int tab[] = {1,3,6,7,8,9,10};
     int min=0,max=6,elem;
     System.out.println("RES: L'élément 0 se trouve en position "+
			dichoRec(tab,min,max,0)+" Attendu: -1");
     for (int cpt=0;cpt <=max; cpt++) {
       System.out.println("RES: L'élément "+tab[cpt]+" se trouve en position "+
			  dichoRec(tab,min,max,tab[cpt])+" Attendu: "+cpt);
     }
     System.out.println("RES: L'élément 5 se trouve en position "+ 
			dichoRec(tab,min,max,5)+" Attendu: -1");
     System.out.println("RES: L'élément 11 se trouve en position "+ 
			dichoRec(tab,min,max,11)+" Attendu: -1");
  }
}
