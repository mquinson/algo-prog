package recipients;

public class Recipient {
	
	
	
	private int capacite;
	
	private int contenu=0;
	
	public Recipient(int capacite) {
		this.capacite = capacite;
	}
	
	
	public void tranvaseDans( Recipient cible){
		
		Recipient source = this;
		
		int volumeADeplacer = Math.min( source.getContenu() , cible.getCapaciteDisponible() );
		
		source.setContenu( source.getContenu() - volumeADeplacer );
		cible.setContenu( cible.getContenu() + volumeADeplacer );
	}
	
	public int getCapaciteDisponible(){
		return capacite - contenu;
	}
	public int getContenu() {
		return contenu;
	}

	public void setContenu(int contenu) {
		this.contenu = contenu;
	}

	public int getCapacite() {
		return capacite;
	}
	
	
	
	public String toString(){
		
		
		return contenu+"/"+capacite;
		
		
		
	}
	public String asciiArt(){
		String re = "|";
		for(int k = 0 ; k < capacite ; k ++){
			if( k < contenu ){
				re+="#";
			}else{
				re+=" ";
			}
		}
		return re+"|";
	}
	
	public Recipient clone(){
		Recipient clone = new Recipient( capacite );
		clone.setContenu( contenu );
		
		return clone;
	}
	
}
