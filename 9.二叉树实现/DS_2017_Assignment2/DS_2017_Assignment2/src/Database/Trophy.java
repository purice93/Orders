package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in four ranks:
 * bronze, silver, gold and platinum. The date the trophy was earned and its
 * respective game is also stored. Created for Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {

	private String name;	//  name of trophy
	private Rank rank;	// rank
	private Rarity rarity;	// rarity
	private Calendar obtained;	// date obtained
	private Game game;	//the game from which it was earnt

	public enum Rank {
		BRONZE, SILVER, GOLD, PLATINUM

	}

	public enum Rarity {
		COMMON, UNCOMMON, RARE, VERY_RARE, ULTRA_RARE

	}
	
	public Trophy() {
	}
	
	public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
		this.name = name;
		this.rank = rank;
		this.rarity = rarity;
		this.obtained = obtained;
		this.game = game;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public void setObtained(Calendar obtained) {
		this.obtained = obtained;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	/*
	 * print log
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		String dateStr = formatter.format(obtained.getTime());
    	String rtnStr = "\""+name +"\"" +", rank: "+rank+", rarity: "+rarity+", obtained on: "+dateStr;
		return rtnStr;
	}

	public String getName() {
		return name;
	}

	public Rank getRank() {
		return rank;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Calendar getObtained() {
		return obtained;
	}

	public Game getGame() {
		return game;
	}


}
