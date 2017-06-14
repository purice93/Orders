package zoo;
import gehege.*;
import tiere.*;
import personen.*;

public class SmokeTest {
	
	private Zoo zoo;

	public SmokeTest(final Zoo zoo) {
		this.zoo = zoo;
	}
	
	
	
	public void RunTest() {
	    System.out.println("Zoo Name: "+zoo.getName());
	    
	    //Aufgabe 1:
	    zoo.addGehege(new Vogelgehege("Papageiengehege"));
	    zoo.addGehege(new Vogelgehege("Adlergehege"));
	    
	    zoo.addGehege(new Landsaeugetiergehege("Landgehege1"));
	    zoo.addGehege(new Landsaeugetiergehege("Landgehege2"));
	    zoo.addGehege(new Landsaeugetiergehege("Landgehege3"));
	    zoo.addGehege(new Landsaeugetiergehege("Landgehege4"));
	    
	    zoo.addGehege(new Aquarium("Walhaibecken"));
	    zoo.addGehege(new Aquarium("Kugelfischtank"));
	    
	    
	    //Aufgabe 2:
	    zoo.addTier(new Papagei("Tick"));
	    zoo.addTier(new Papagei("Trick"));
	    zoo.addTier(new Papagei("Track"));
	    
	    zoo.moveTierToGehege(zoo.getTier("Tick"), zoo.getGehege("Papageiengehege"));
	    zoo.moveTierToGehege(zoo.getTier("Trick"), zoo.getGehege("Papageiengehege"));
	    zoo.moveTierToGehege(zoo.getTier("Track"), zoo.getGehege("Papageiengehege"));
	    
	    
	    //Aufgabe 3:
	    zoo.addTier(new Zebra("Zalana"));
	    zoo.addTier(new Zebra("Eddard"));
	    zoo.addTier(new Zebra("Bran"));
	    zoo.addTier(new Zebra("Robb"));
	    zoo.addTier(new Zebra("Arya"));
	    
	    zoo.moveTierToGehege(zoo.getTier("Eddard"), zoo.getGehege("Landgehege1"));
	    zoo.moveTierToGehege(zoo.getTier("Robb"), zoo.getGehege("Landgehege1"));   
	    zoo.moveTierToGehege(zoo.getTier("Zalana"), zoo.getGehege("Landgehege2"));
	    zoo.moveTierToGehege(zoo.getTier("Bran"), zoo.getGehege("Landgehege2"));    
	    zoo.moveTierToGehege(zoo.getTier("Arya"), zoo.getGehege("Landgehege2"));

	    //Aufgabe 4:
	    zoo.addTier(new Loewe("Tywin"));
	    zoo.addTier(new Loewe("Jamie"));
	    zoo.addTier(new Loewe("Cersei"));
	    zoo.addTier(new Loewe("Joffrey"));
	    
	    zoo.moveTierToGehege(zoo.getTier("Tywin"), zoo.getGehege("Landgehege3"));
	    zoo.moveTierToGehege(zoo.getTier("Jamie"), zoo.getGehege("Landgehege3"));    
	    zoo.moveTierToGehege(zoo.getTier("Cersei"), zoo.getGehege("Landgehege3"));
	    zoo.moveTierToGehege(zoo.getTier("Joffrey"), zoo.getGehege("Landgehege3"));
	    
	    //Aufgabe 5
	    for (int i = 0; i<10; i++) {
	    	String tierName = "Kugelfisch_"+String.valueOf(i+1);
		    zoo.addTier(new Kugelfisch(tierName));
		    zoo.moveTierToGehege(zoo.getTier(tierName), zoo.getGehege("Kugelfischtank"));
	    }
	    
	
	    //Aufgabe 6:
	    zoo.addTier(new Walhai("Moby"));
	    zoo.addTier(new Walhai("Dick"));
	    zoo.moveTierToGehege(zoo.getTier("Moby"), zoo.getGehege("Walhaibecken"));
	    zoo.moveTierToGehege(zoo.getTier("Dick"), zoo.getGehege("Walhaibecken"));
	    
	    
	    //Aufgabe 7:
	    zoo.addTier(new Adler("American"));
	    zoo.addTier(new Adler("Dream"));
	    zoo.moveTierToGehege(zoo.getTier("American"), zoo.getGehege("Adlergehege"));
	    zoo.moveTierToGehege(zoo.getTier("Dream"), zoo.getGehege("Adlergehege"));
	    
	    
	    //Aufgabe 8:
	    zoo.addPersonal(new Personal("Ramsay", zoo));
	    zoo.addPersonal(new Personal("Sam", zoo));
	    zoo.addPersonal(new Personal("Daenerys", zoo));
	    
	    		///WER BETREUT WAS?

	    		zoo.getPersonal("Ramsay").setBetreut("Landsaeugetier");
	    			zoo.getPersonal("Ramsay").printBetreuteTiere();
	    			
	    		zoo.getPersonal("Sam").setBetreut("Wassertier");
	    			zoo.getPersonal("Sam").printBetreuteTiere();
	    		zoo.getPersonal("Daenerys").setBetreut("Vogel");
	    			zoo.getPersonal("Daenerys").printBetreuteTiere();
	    
	    //Aufgabe 9:
	    zoo.addBesucher(new Besucher("Catelyn"));
	    	zoo.getBesucher("Catelyn").besuchtGehege(zoo.getGehege("Landgehege1"));
	    zoo.addBesucher(new Besucher("Aerys"));
	    	zoo.getBesucher("Aerys").besuchtGehege(zoo.getGehege("Adlergehege"));
	 	zoo.addBesucher(new Besucher("Bronn"));
	 		zoo.getBesucher("Bronn").besuchtGehege(zoo.getGehege("Kugelfischtank"));
	 	zoo.addBesucher(new Besucher("Sansa"));
	 		zoo.getBesucher("Sansa").besuchtGehege(zoo.getGehege("Walhaibecken"));
	 	zoo.addBesucher(new Besucher("Willy"));
	 		zoo.getBesucher("Willy").besuchtGehege(zoo.getGehege("Landgehege2"));
	 		zoo.getBesucher("Willy").besuchtGehege(zoo.getGehege("Kugelfischtank"));
	 		
	 		
	 		//Aufgabe 10-13
	 	    zoo.moveTierToGehege(zoo.getTier("Joffrey"), zoo.getGehege("Walhaibecken"));
	 	    zoo.moveTierToGehege(zoo.getTier("Tywin"), zoo.getGehege("Landgehege1"));
	 	    	zoo.getBesucher("Sansa").besuchtGehege(zoo.getGehege("Landgehege1"));
	 	    zoo.moveTierToGehege(zoo.getTier("Cersei"), zoo.getGehege("Adlergehege"));

	}
	
	

	

	
	
}