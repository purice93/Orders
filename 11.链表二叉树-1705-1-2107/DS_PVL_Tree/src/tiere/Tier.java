package tiere;
import gehege.*;

public abstract class Tier implements ITier,Comparable{

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int compareTo(Tier o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	
	private String name;		//name des tieres
	private String typ;			//Typ des Tiers, unter der art (Z.b Adler, Papagei)
	private String art;			//Tier Art (rasse) z.b. Vogel, Wassertier, Landsauegetier;
	private Gehege gehege;
	
	public Tier (String name, String typ, String art){
		this.name= name;
		this.typ = typ;
		this.art = art;
		this.gehege = null;
	}
	
	
	public String getName() {return this.name;}
	public String getTyp() {return this.typ;}
	public String getArt() {return this.art;}
	public Gehege getGehege() {return this.gehege;}
	public void setGehege(Gehege gehege) {this.gehege = gehege;}

}
