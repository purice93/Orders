package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {
    private Object username;
	private double level;

	public User(String username, Calendar dob, int level) {
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {
		return null;}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDob() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setGames(GameList games) {
		// TODO Auto-generated method stub
		
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		// TODO Auto-generated method stub
		
	}
}
