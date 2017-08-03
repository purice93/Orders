package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game. Created for Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class Game {

	private String name; // title
	private Calendar released; // released date
	private Game next = null; // another Game class
	private int totalTrophies; // total of Trophies

	public Game() {
	}

	public Game(String name, Calendar released, int totalTrophies) {
		this.name = name;
		this.released = released;
		this.totalTrophies = totalTrophies;
	}

	public Game getNext() {
		return next;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReleased(Calendar released) {
		this.released = released;
	}

	public void setTotalTrophies(int totalTrophies) {
		this.totalTrophies = totalTrophies;
	}

	/*
	 * print log (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		String dateStr = formatter.format(released.getTime());
		String log = "\"" + name + "\"" + "," + " released on: " + dateStr;
		return log;
	}

	public String getName() {
		return name;
	}

	public Calendar getReleased() {
		return released;
	}

	public int getTotalTrophies() {
		return totalTrophies;
	}

	public void setNext(Game next) {
		this.next = next;
	}

}
