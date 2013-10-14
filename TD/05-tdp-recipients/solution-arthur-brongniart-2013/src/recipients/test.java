package recipients;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] tab = { 2 , 3 , 8 };
		
		
		StockagePolynome st = new StockagePolynome(tab);
		
		Solution s = new Solution( tab );
		
		Solution ss = new Solution( s , new Operation( 2 , 0 ) );
		//ss = new Solution( ss , new Operation( 0 , 2 ) );
		
		
		
		System.out.println( ss.getNbOperations() );
		
		System.out.println( st.contains(ss) );
		
		System.out.println( st.contains(s) );
		
		System.out.println( st.contains(ss) );
		
	}

}
