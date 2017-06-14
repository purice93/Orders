package personen;

import zoo.*;
import list.MyLinkedList;
import tiere.*;

public class Personal extends Personen {
	private final static boolean personal = true;
	private Zoo arbeitsplatz;
	private MyLinkedList<Tier> betreutList = new MyLinkedList<>();

	public Personal(String name) {
		super(name, personal);
		this.arbeitsplatz = null;
	}

	public Personal(String name, Zoo arbeitsplatz) {
		super(name, personal);
		this.arbeitsplatz = arbeitsplatz;
	}

	public void setBetreut(String art) {
		MyLinkedList<Tier> tmpTiereList = arbeitsplatz.getTiere();
		for (int i = 0; i < arbeitsplatz.getTiereAnzahl(); i++) {
			if (tmpTiereList.get(i).getArt() == art) {
				betreutList.addLast(tmpTiereList.get(i));
			}
		}
	}

	public void printBetreuteTiere() {
		System.out.print(this.getName() + " betreut: ");
		for (int i = 0; i < betreutList.size(); i++) {
			System.out.print(betreutList.get(i).getName() + ", ");
		}
		System.out.print("\n");
	}

	public String getBetreuteTiereListe() {
		String tierListe = "";
		for (int i = 0; i < betreutList.size(); i++) {
			tierListe = tierListe.concat(betreutList.get(i).getName()); // concat =>
																// f�gt String
																// an ende des
																// Strings hinzu
			if (i != (betreutList.size() - 1)) {
				tierListe = tierListe.concat(", ");
			}
		}

		return tierListe;
	}

}
