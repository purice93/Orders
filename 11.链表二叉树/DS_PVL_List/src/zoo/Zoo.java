package zoo;

import gehege.Gehege;
import list.MyLinkedList;
import list.MyListIterator;
import personen.Besucher;
import personen.Personal;
import tiere.Tier;

public class Zoo {
	private String name;
	private MyLinkedList<Gehege> gehegeList = new MyLinkedList<>();
	private MyLinkedList<Tier> tiereList = new MyLinkedList<>();
	private MyLinkedList<Personal> personalList = new MyLinkedList<>();
	private MyLinkedList<Besucher> besucherList = new MyLinkedList<>();

	// constructor
	public Zoo(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addGehege(Gehege gehege) {
		this.gehegeList.addLast(gehege);
	}

	public Gehege getGehege(String name) {
		Gehege g = null;
		MyListIterator<Gehege> gIterator = gehegeList.myListIterator();
		while(gIterator.hasNext()) {
			Gehege gtemp = gIterator.next();
			if (gtemp.getName().equals(name)) {
				g = gtemp;
			}
		}
		return g;
	}

	public void addTier(Tier tier) {
		this.tiereList.addLast(tier);
	}

	public Tier getTier(String name) {
		Tier t = null;
		MyListIterator<Tier> tIterator = tiereList.myListIterator();
		while(tIterator.hasNext()) {
			Tier ttemp = tIterator.next();
			if (ttemp.getName().equals(name)) {
				t = ttemp;
			}
		}
		return t;
	}

	public MyLinkedList<Tier> getTiere() {
		return tiereList;
	}

	public int getTiereAnzahl() {
		return tiereList.size();
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
		this.personalList.addLast(personal);
	}

	public Personal getPersonal(String name) {
		Personal p = null;
		MyListIterator<Personal> pIterator = personalList.myListIterator();
		while(pIterator.hasNext()) {
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
		this.besucherList.addLast(besucher);
	}

	// Geh�rt laut uml eigentlich nicht hier hin, scheint aber der beste Ort zu
	// sein
	public Besucher getBesucher(String name) {
		Besucher b = null;
		MyListIterator<Besucher> beIterator = besucherList.myListIterator();
		while(beIterator.hasNext()) {
			Besucher betemp = beIterator.next();
			if (betemp.getName() == name) {
				b = betemp;
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
		tmpString = tmpString.concat("Im Zoo gibt es " + this.gehegeList.size() + " Gehege: \r\n");

		MyListIterator<Gehege> gIterator = gehegeList.myListIterator();
		while(gIterator.hasNext()) {
			Gehege gtemp = gIterator.next();
			tmpString = tmpString.concat(gtemp.getName() + "[" + gtemp.getTierAnzahl() + "] " + "(");
			tmpString = tmpString.concat(gtemp.getTierListe() + ") ");

			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		// Personal
		tmpString = tmpString.concat("Personal:  \r\n");
		tmpString = tmpString.concat("Im Zoo arbeiten " + this.personalList.size() + " Personen: \r\n");
		
		MyListIterator<Personal> pIterator = personalList.myListIterator();
		while(pIterator.hasNext()) {
			Personal ptemp = pIterator.next();
			tmpString = tmpString.concat(ptemp.getName() + " betreut : ");
			tmpString = tmpString.concat(ptemp.getBetreuteTiereListe());

			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		// Besucher
		tmpString = tmpString.concat("Besucher:  \r\n");
		tmpString = tmpString.concat("Es sind " + this.besucherList.size() + " Besucher im Zoo: \r\n");
		
		MyListIterator<Besucher> beIterator = besucherList.myListIterator();
		while(beIterator.hasNext()) {
			Besucher betemp = beIterator.next();
			tmpString = tmpString.concat(betemp.getName() + " steht am Gehege: ");
			tmpString = tmpString.concat(betemp.getGehege().getName());
			tmpString = tmpString.concat(" und sieht: ");
			tmpString = tmpString.concat(betemp.getGehege().getTierListe());
			tmpString = tmpString.concat("\r\n");
		}
		tmpString = tmpString.concat("\r\n");

		return tmpString;
	}

}
