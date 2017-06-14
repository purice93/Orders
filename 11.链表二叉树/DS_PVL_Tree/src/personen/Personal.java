package personen;

import java.util.Iterator;

import tiere.Tier;
import tree.MyTree;
import tree.MyTreeIterator;
import zoo.Zoo;

public class Personal extends Personen {
	private final static boolean personal = true;
	private Zoo arbeitsplatz;
	private MyTree<Tier> betreutTree = new MyTree<>();

	public Personal(String name) {
		super(name, personal);
		this.arbeitsplatz = null;
	}

	public Personal(String name, Zoo arbeitsplatz) {
		super(name, personal);
		this.arbeitsplatz = arbeitsplatz;
	}

	public void setBetreut(String art) {
		MyTree<Tier> tmpTiereTree = arbeitsplatz.getTiere();
		MyTreeIterator<Tier> tIterator = tmpTiereTree.myTreeIterator();
		while (tIterator.hasNext()) {
			Tier ttemp = tIterator.next();
			if (ttemp.getArt() == art) {
				betreutTree.add(ttemp);
			}
		}
	}

	public void printBetreuteTiere() {
		System.out.print(this.getName() + " betreut: ");
		MyTreeIterator<Tier> bIterator = betreutTree.myTreeIterator();
		while (bIterator.hasNext()) {
			Tier ttemp = bIterator.next();
			System.out.print(ttemp.getName() + ", ");
		}
		System.out.print("\n");
	}

	public String getBetreuteTiereTreee() {
		String tierTreee = "";
		MyTreeIterator<Tier> bIterator = betreutTree.myTreeIterator();
		while (bIterator.hasNext()) {
			Tier ttemp = bIterator.next();
			tierTreee = tierTreee.concat(ttemp.getName());
			tierTreee = tierTreee.concat(", ");
		}
		tierTreee = tierTreee.substring(0, tierTreee.length()-1);
		return tierTreee;
	}

}
