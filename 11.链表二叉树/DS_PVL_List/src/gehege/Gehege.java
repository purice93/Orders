package gehege;
import list.MyLinkedList;
import tiere.*;

public abstract class Gehege {
	private static final int maxTiere = 10;
	
	private String name;
	private String art; //Die Art des Geheges, bzw. Tierart die in diesem Gehege gehalten wird. 
						//"Vogel" , "Wassertier" , "Landsaeugetier"	

    private MyLinkedList<Tier> tiereList = new MyLinkedList<>(); //Jedes Gehege 10 Tieren

    
    public Gehege (String name, String art)
    {
    	this.name = name; 
    	this.art = art;
    }
    

    public String getName() {return this.name;}
    

    public String getTierListe() { 
    	String tierListe = "";
    	for(int i=0; i<tiereList.size();i++){
    		tierListe = tierListe.concat(tiereList.get(i).getName());		//concat => f�gt String an ende des Strings hinzu
    		if(i!=(tiereList.size()-1)) {
    			tierListe = tierListe.concat(", ");	
    		}
    	}
			
    	
		return tierListe;
	}
    
    
    public int getTierAnzahl() {return this.tiereList.size();}
    
    public boolean addTier (Tier tier)  {
    	if(freierPlatz()) {
			if(tier.getArt() == this.art) {
				if(check(tier)) {
				    tiereList.addLast(tier);
					System.out.println(tier.getTyp()+": '" +tier.getName() + "' ist in Gehege('"+getName()+"') eingezogen.");	
					return true;
				}else {
					System.out.println(tier.getName()+ " (" + tier.getTyp()+ ") kann nicht mit Tieren des Types '"+ tiereList.get(0).getTyp() + "' zusammenleben.");	
				}

			}else {
				System.out.println(tier.getName()+ " (" + tier.getArt()+ ") kann nicht in ein Gehege der Art '" +this.art+ "' zugeordnet werden.");	
			}
			
		}else {
			System.out.println("Kein Platz im Gehege, Zuordnung nicht erfolgt.");
		}
		return false;	

	}
   
	
	public boolean removeTier (Tier tier){
		for(int i=0; i<tiereList.size();i++){
			if(tier == tiereList.get(i)){
				tiereList.remove(i);
				return true;
			}
		}
		return false; 
	}

    private boolean freierPlatz(){	
    	if (tiereList.size() < maxTiere) {
    		return true;
    	}
    	return false;
	}
    
    //schaut ob bereits tiere eines anderen Typs im Gehege leben
    private boolean check(Tier tier) {
    	boolean possible = true;
    	for(int i=0; i<tiereList.size();i++){
    		if(((Tier) tiereList.get(i)).getTyp() != tier.getTyp()) {
    			possible = false;
    		}
    	}
    	
    	return possible;
    }
    

}