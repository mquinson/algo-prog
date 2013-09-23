/* Variante où poids = valeur */
public class KnapsackOr {

  private int bestValue;
  private int [] bestSolution;
  private int [] currentSolution;
  private int [] Value;
  private int Capacity;
  private int numberOfItems;
  private int calls;

  public KnapsackOr(int c, int [] V){
    Capacity = c;
    Value = V;
    bestValue = 0;
    numberOfItems = V.length;
    bestSolution = new int[numberOfItems];
    currentSolution = new int[numberOfItems];
    for (int i=0; i<numberOfItems; i++){
      bestSolution[i]=0;
      currentSolution[i]=0;
    }
    calls=0;
  }

  public int getBestValue(){return bestValue;}
  public int [] getBestSolution(){return bestSolution;}
  public int [] getValue(){return Value;}

  private void storeNewBestSolution(){
    for (int i=0;i<numberOfItems;i++)
      bestSolution[i]=currentSolution[i];
  }


  public String toString(){
    String s = "";
    s = s + "\nValues:  ";
    for (int i=0; i<numberOfItems; i++)
      s = s + Value[i] + " ";
    s = s + "\nTake:    ";
    for (int i=0; i<numberOfItems; i++)
      s = s + bestSolution[i] + " ";
    s = s + "\nCapacity: " + Capacity;
    s = s + "   Value: " + bestValue;
    s = s + "   Calls: " + calls;
    return s;
  }  

  private String showCurrent(int depth, int value){
    String s = "";
    for (int i=0;i<depth;i++)
      s = s + currentSolution[i];
    s = s + "  Value: " + value;
    if (value>bestValue && value<=Capacity)
      s = s + "   *** new best ***";
    return s;
  }
 
  public void search(int depth, int currentValue){
    calls++;
    System.out.println(showCurrent(depth, currentValue));
    if (currentValue<=Capacity && currentValue>bestValue){
      bestValue=currentValue;
      storeNewBestSolution();
    }
    if (depth<numberOfItems){
      currentSolution[depth]=1; // take, go left
      search(depth+1, currentValue+Value[depth]);
      currentSolution[depth]=0; // don't take, go right
      search(depth+1, currentValue);
    }
  }
}
	     
      
      
    
