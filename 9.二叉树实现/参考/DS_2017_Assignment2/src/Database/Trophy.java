package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
    public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM

	}

	public enum Rarity {
		RARE, ULTRA_RARE, UNCOMMON, COMMON

	}

	public Trophy() {}

    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    }

    public String toString() {
		return null;
    }

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRank() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRarity() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getObtained() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getGame() {
		// TODO Auto-generated method stub
		return null;
	}
}
