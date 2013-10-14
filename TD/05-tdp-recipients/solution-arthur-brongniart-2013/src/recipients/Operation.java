package recipients;

public class Operation {
	
	/// puisque la fontaine est un recipient a part entiere, on peut reduire le probleme a une seule operation:
	/// tranvaser depuis la source vers la cible
	
	public int cible;
	public int source;
	
	public Operation(int cible, int source) {
		
		this.cible = cible;
		this.source = source;
	}
	public String toString(){
		if(cible==0) return "on vide "+source+" dans la fontaine";
		if(source==0) return "on remplit "+cible+" a partir de la fontaine";
		return "on transvase "+source+" dans "+cible;
	}
}
