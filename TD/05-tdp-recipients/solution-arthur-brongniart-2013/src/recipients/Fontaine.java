package recipients;

public class Fontaine extends Recipient {
	
	/// une fontaine est un recipient particulier,
	/// elle a toujours suffisament d'eau pour remplir n'importe qu'elle recipient ( donc le plus grand )
	/// elle a toujours suffisament de place pour receuillir toute l'eau de n'import qu'elle recipient( donc le plus grans rempli au maximum )
	
	
	
	private int capaciteMax;
	
	
	public Fontaine(int capaciteMax ) {
		super(0);
		
		this.capaciteMax = capaciteMax;
	}
	
	public int getContenu(){
		return capaciteMax;
	}
	public int getCapaciteDisponible(){
		return capaciteMax;
	}
	
	public String toString(){
		return "fontaine";
	}
	
	public Recipient clone(){
		return new Fontaine( capaciteMax );
	}

}
