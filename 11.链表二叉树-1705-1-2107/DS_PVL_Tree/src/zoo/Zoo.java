package zoo;

import java.util.Iterator;

import gehege.Gehege;
import personen.Besucher;
import personen.Personal;
import tiere.Tier;
import tree.MyTree;
import tree.MyTreeIterator;

public class Zoo {
	private String name;
	private MyTree<Gehege> gehegeTree = new MyTree<>();
	private MyTree<Tier> tiereTree = new MyTree<>();
	private MyTree<Personal> personalTree = new MyTree<>();
	private MyTree<Besucher> besucherTree = new MyTree<>();

	// constructor
	public Zoo(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addGehege(Gehege gehege) {
		this.gehegeTree.add(gehege);
	}

	public Gehege getGehege(String name) {
		Gehege g = null;
		MyTreeIterator<Gehege> gIterator = gehegeTree.myTreeIterator();
		while(gIterator.hasNext()) {
			Gehege gtemp = gIterator.next();
			if(gtemp.getName().equals(name)) {
				g= gtemp;
			}
		}
		return g;
	}

	public void addTier(Tier tier) {
		this.tiereTree.add(tier);
	}

	public Tier getTier(String name) {
		Tier t = null;
		MyTreeIterator<Tier> tIterator = tiereTree.myTreeIterator();
		while(tIterator.hasNext()) { 
			Tier ttemp = tIterator.next();
			if(ttemp.getName().equals(name)) {
				t= ttemp;
			}
		}
		return t;
	}

	public MyTree<Tier> getTiere() {
		return tiereTree;
	}

	public int getTiereAnzahl() {
		return tiereTree.size();
	}

	public boolean moveTierToGehege(Tier tier, Gehege gehege) {
		if (gehege.addTier(tier)) {
			if (tier.getGehege() != null) {
				tier.getGehege().removeTier(tier);
			}
			tier.setGehege(gehege);
			return true;
		}
		return false;
	}

	public void addPersonal(Personal personal) {
		this.personalTree.add(personal);
	}

	public Personal getPersonal(String name) {
		Personal p = null;
		MyTreeIterator<Personal> pIterator = personalTree.myTreeIterator();
		while (pIterator.hasNext()) {
			Personal ptemp = pIterator.next();
			if (ptemp.getName() == name) {
				p = ptemp;
			}
		}
		return p;
	}

	// Geh�rt laut uml eigentlich nicht hier hin, scheint aber der beste Ort zu
	// sein
	public void addBesucher(Besucher besucher) {
		this.besucherTree.add(besucher);
	}

	// Geh�rt laut uml eigentlich nicht hier hin, scheint aber der beste Ort zu
	// sein
	public Besucher getBesucher(String name) {
		Besucher b = null;
		MyTreeIterator<Besucher> bIterator = besucherTree.myTreeIterator();
		while (bIterator.hasNext()) {
			Besucher ptemp = bIterator.next();
			if (ptemp.getName() == name) {
				b = ptemp;
			}
		}
		return b;
	}

	// Status in Stringform f�r die Textausgabe in der Main Klasse
	public String getStatus() {
		String tmpString = this.getName() + "\r\n \r\n"; // \r\n for text stream

		// Tiere
		tmpString = tmpString.concat("Tiere: \r\n");
		tmpString = tmpString.concat("Es leben " + this.getTiereAnzahl() + " Tiere im Zoo  \r\n");
		tmpString = tmpString.concat("\r\n");

		// Gehege
		tmpString = tmpString.concat("Gehege:  \r\n");
		tmpString = tmpString.concat("Im Zoo gibt es " + this.gehegeTree.size() + " Gehege: \r\n");
		
		MyTreeIterator<Gehege> gIterator = gehegeTree.myTreeIterator();
		while (gIterator.hasNext()) {
			Gehege gtemp = gIterator.next();
			tmpString = tmpString.concat(gtemp.getName() + "[" + gtemp.getTierAnzahl() + "] " + "(");
			tmpString = tmpString.concat(gtemp.getTierTreee() + ") ");
			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		// Personal
		tmpString = tmpString.concat("Personal:  \r\n");
		tmpString = tmpString.concat("Im Zoo arbeiten " + this.personalTree.size() + " Personen: \r\n");
		
		MyTreeIterator<Personal> pIterator = personalTree.myTreeIterator();
		while (pIterator.hasNext()) {
			Personal ptemp = pIterator.next();
			tmpString = tmpString.concat(ptemp.getName() + " betreut : ");
			tmpString = tmpString.concat(ptemp.getBetreuteTiereTreee());

			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		// Besucher
		tmpString = tmpString.concat("Besucher:  \r\n");
		tmpString = tmpString.concat("Es sind " + this.besucherTree.size() + " Besucher im Zoo: \r\n");

		MyTreeIterator<Besucher> beIterator = besucherTree.myTreeIterator();
		while (beIterator.hasNext()) {
			Besucher betemp = beIterator.next();
			tmpString = tmpString.concat(betemp.getName() + " steht am Gehege: ");
			tmpString = tmpString.concat(betemp.getGehege().getName());
			tmpString = tmpString.concat(" und sieht: ");
			tmpString = tmpString.concat(betemp.getGehege().getTierTreee());
			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		return tmpString;
	}

}
