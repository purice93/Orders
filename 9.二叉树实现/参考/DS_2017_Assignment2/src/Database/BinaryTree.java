package Database;

import java.util.ArrayList;

/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {

	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
	}

	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		return false;
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
	}
}
