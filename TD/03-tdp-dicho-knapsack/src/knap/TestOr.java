
//import java.lancs.*;
import java.lang.Math.*;

public class TestOr {      
    
  public static void main(String[] args) throws Exception{


    int Values [] = {2,12,9,3,5};
    int Capacity = 9;
    
    KnapsackOr KS = new KnapsackOr(Capacity, Values);
    KS.search(0,0);
    System.out.println(KS);
    
    /*
    int [] W = {7,5,4,4,1};    
    Knapsack KS2 = new Knapsack(10,W,W);
    KS2.search(0,0,0);
    System.out.println(KS2);    
    */
 
    /*
    int [] Weights = {3,8,6,4,2,3,8,6,4,2};
    int Values [] = {2,12,9,3,5,2,12,9,3,5};
    int Capacity = 18;
    
    Knapsack KS = new Knapsack(Capacity, Weights, Values);
    KS.search(0,0,0);
    System.out.println(KS);
    */

  }
}
