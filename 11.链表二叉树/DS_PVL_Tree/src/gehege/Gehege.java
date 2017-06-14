package gehege;

import java.util.Comparator;

import tiere.Tier;
import tree.MyTree;
import tree.MyTreeIterator;

public abstract class Gehege implements Comparator{
	private static final int maxTiere = 10;

	private String name;
	private String art; // Die Art des Geheges, bzw. Tierart die in diesem
						// Gehege gehalten wird.
						// "Vogel" , "Wassertier" , "Landsaeugetier"

	private MyTree<Tier> tiereTree = new MyTree<>(); // Jedes Gehege 10 Tieren

	public Gehege(String name, String art) {
		this.name = name;
		this.art = art;
	}

	public String getName() {
		return this.name;
	}

	public String getTierTreee() {
		String tierTreee = "";
		MyTreeIterator<Tier> tMyTreeIterator = tiereTree.myTreeIterator();
		while (tMyTreeIterator.hasNext()) {
			Tier ttemp = tMyTreeIterator.next();
			tierTreee = tierTreee.concat(ttemp.getName());
			tierTreee = tierTreee.concat(", ");
		}
		tierTreee = tierTreee.substring(0, tierTreee.length());
		return tierTreee;
	}

	public int getTierAnzahl() {
		return this.tiereTree.size();
	}

	public boolean addTier(Tier tier) {
		if (freierPlatz()) {
			if (tier.getArt() == this.art) {
				if (check(tier)) {
					tiereTree.add(tier);
					System.out.println(tier.getTyp() + ": '" + tier.getName() + "' ist in Gehege('" + getName()
							+ "') eingezogen.");
					return true;
				} else {
					MyTreeIterator<Tier> tMyTreeIterator = tiereTree.myTreeIterator();
					if (tMyTreeIterator.hasNext()) {
						Tier ttemp = tMyTreeIterator.next();
						System.out.println(tier.getName() + " (" + tier.getTyp() + ") kann nicht mit Tieren des Types '"
								+ ttemp.getTyp() + "' zusammenleben.");
					}
				}

			} else {
				System.out.println(tier.getName() + " (" + tier.getArt() + ") kann nicht in ein Gehege der Art '"
						+ this.art + "' zugeordnet werden.");
			}

		} else {
			System.out.println("Kein Platz im Gehege, Zuordnung nicht erfolgt.");
		}
		return false;

	}

	public boolean removeTier(Tier tier) {
		MyTreeIterator<Tier> tMyTreeIterator = tiereTree.myTreeIterator();
		while (tMyTreeIterator.hasNext()) {
			Tier ttemp = tMyTreeIterator.next();
			if (tier == ttemp) {
				tiereTree.remove(ttemp);
				return true;
			}
		}
		return false;
	}

	private boolean freierPlatz() {
		if (tiereTree.size() < maxTiere) {
			return true;
		}
		return false;
	}

	private boolean check(Tier tier) {
		boolean possible = true;
		MyTreeIterator<Tier> tMyTreeIterator = tiereTree.myTreeIterator();
		while (tMyTreeIterator.hasNext()) {
			Tier ttemp = tMyTreeIterator.next();
			if (ttemp.getTyp() != tier.getTyp()) {
				possible = false;
			}
		}
		return possible;
	}

}