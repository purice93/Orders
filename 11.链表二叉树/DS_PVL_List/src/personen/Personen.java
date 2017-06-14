package personen;

import gehege.Gehege;

public abstract class Personen {
	private String name;
	private boolean istPersonal;
	private Gehege gehege;
	
	
	public Personen(String name, boolean istPersonal) {
		this.name = name;
		this.istPersonal = istPersonal;
	}

	

	public String getName() {return this.name;}
	
	public boolean istPersonal() {return this.istPersonal;}
		public boolean istBesucher() {return this.istPersonal;}

	 
	public Gehege getGehege() {return gehege;}
	public void setGehege(Gehege gehege) {this.gehege = gehege;}	
		
	public boolean besuchtGehege(Gehege gehege) {
		System.out.println(	this.name +
							" steht am "+ gehege.getName()+ 
							" und sieht: " +gehege.getTierAnzahl() + " Tiere: ("
							+ gehege.getTierListe() + ").");
		this.setGehege(gehege);
		return true;
	}

}
