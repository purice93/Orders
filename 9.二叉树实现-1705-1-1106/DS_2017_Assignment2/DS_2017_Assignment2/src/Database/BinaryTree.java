package Database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Uses a binary search tree to store a database of PlayStation users. Nodes are
 * ordered by user unique key (see the User class for more detail). Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {

	public User root;

	/**
	 * Making new friends is great. This method should add your new bestie to
	 * your database (tree). Remember that they should be added according to
	 * their key.
	 * 
	 * @param friend
	 *            The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException
	 *             if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		double key = friend.getKey();
		User parentNode = null;
		User newNode = friend;
		User tmpNode = root;
		if (root == null) {
			root = newNode;
			return true;
		}
		while (tmpNode != null) {
			parentNode = tmpNode;
			if (key < tmpNode.getKey()) {
				tmpNode = tmpNode.getLeft();
			} else if (key > tmpNode.getKey()) {
				tmpNode = tmpNode.getRight();
			} else {
				return false;
			}
		}
		if (key < parentNode.getKey()) {
			parentNode.setLeft(newNode);
			// newNode.parent = parentNode;
		} else {
			parentNode.setRight(newNode);
		}
		return true;
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best to remove
	 * that "friend" altogether. This method should remove all trace of that
	 * "friend" in the database (tree).
	 * 
	 * @param friend
	 *            the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException
	 *             if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if (friend == null || root == null) {
			throw new IllegalArgumentException();
		}
		boolean non = preOrder(root, friend);
		if (!non) {
			return false;
		}
		root = delete(root, friend);
		return true;
	}

	private boolean preOrder(User root, User friend) {
		boolean non1 = false;
		boolean non2 = false;
		boolean non3 = false;
		if (root != null) {
			if (friend.getKey() == root.getKey()) {
				return non1 = true;
			}
			non2 = preOrder(root.getLeft(), friend);
			non3 = preOrder(root.getRight(), friend);
		}
		return (non1 || non2 || non3);
	}

	private User delete(User x, User friend) {
		if (x == null) {
			return null;
		}
		double cmp = friend.getKey() - x.getKey();
		if (cmp < 0) {
			x.setLeft(delete(x.getLeft(), friend));
		} else if (cmp > 0) {
			x.setRight(delete(x.getRight(), friend));
		} else {
			if (x.getRight() == null) {
				return x.getLeft();
			}
			if (x.getLeft() == null) {
				return x.getRight();
			}
			User t = x;
			x = max(t.getLeft());
			x.setLeft(deleteMax(t.getLeft()));
			x.setRight(t.getRight());
		}
		return x;
	}

	private User deleteMax(User x) {
		if (x.getRight() == null) {
			return x.getLeft();
		}
		x.setRight(deleteMax(x.getRight()));
		return x;
	}

	private User max(User x) {
		if (x.getRight() == null) {
			return x;
		}
		return max(x.getRight());
	}

	/**
	 * In your quest to be the very best you need to know how many of your
	 * friends are ranked higher than you. This method should return the number
	 * of higher ranked users that the provided reference user, or zero if there
	 * are none (woot!).
	 * 
	 * @param reference
	 *            The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException
	 *             if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}

		int count = 0;

		if (root == null) {
			return 0;
		}

		boolean non = preOrder(root, reference);
		if (!non) {
			return -1;
		}

		User temp = root;
		count = preOrderBetter(temp, reference, count);
		return count;
	}

	public int preOrderBetter(User subTree, User reference, int count) {
		if (subTree != null) {
			if (reference.getLevel() < subTree.getLevel()) {
				count++;
			}
			count = preOrderBetter(subTree.getLeft(), reference, count);
			count = preOrderBetter(subTree.getRight(), reference, count);
		}
		return count;
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're
	 * actually better than those over whom you're lording your achievements.
	 * This method should find all those friends who have a lower level than
	 * you, or zero if there are none (you suck).
	 * 
	 * @param reference
	 *            The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException
	 *             if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}

		int count = 0;

		if (root == null) {
			return 0;
		}

		boolean non = preOrder(root, reference);
		if (!non) {
			return -1;
		}

		User temp = root;
		count = preOrderWorse(temp, reference, count);
		return count;
	}

	private int preOrderWorse(User subTree, User reference, int count) {
		if (subTree != null) {
			if (reference.getLevel() > subTree.getLevel()) {
				count++;
			}
			count = preOrderWorse(subTree.getLeft(), reference, count);
			count = preOrderWorse(subTree.getRight(), reference, count);
		}
		return count;
	}

	/**
	 * The best player may not necessarily be measured by who has the highest
	 * level. Platinum trophies are the holy grail, so it would be good to know
	 * who has the most. This method should return the user with the highest
	 * number of platinum trophies.
	 * 
	 * @return the user with the most platinum trophies, or null if there are
	 *         none
	 */
	public User mostPlatinums() {
		List<User> userList = new LinkedList<>();
		preOrderGPlat(root, userList);

		List<User> platUserList = new LinkedList<>();
		int mixPlatNum = 0;
		for (User user : userList) {
			ArrayList<Trophy> trList = user.getTrophies();
			int platNum = 0;
			for (Trophy trophy : trList) {
				if (trophy.getRank().equals(Trophy.Rank.PLATINUM)) {
					platNum++;
				}
			}
			if (platNum > mixPlatNum) {
				mixPlatNum = platNum;
				platUserList.add(user);
			} else if (platNum == mixPlatNum) {
				platUserList.add(user);
			}
		}

		if (mixPlatNum == 0) {
			return null;
		}

		User winUser = null;
		int mixGoldNum = 0;
		for (User user : platUserList) {
			ArrayList<Trophy> trList = user.getTrophies();
			int glodNum = 0;
			for (Trophy trophy : trList) {
				if (trophy.getRank().equals(Trophy.Rank.GOLD)) {
					glodNum++;
				}
			}
			if (glodNum >= mixGoldNum) {
				mixGoldNum = glodNum;
				winUser = user;
			}
		}

		return winUser;
	}

	private void preOrderGPlat(User subTree, List<User> userList) {
		if (subTree != null) {
			userList.add(subTree);
			preOrderGPlat(subTree.getLeft(), userList);
			preOrderGPlat(subTree.getRight(), userList);
		}
	}

	/**
	 * You or one of your friends bought a new game! This method should add it
	 * to their GameList.
	 * 
	 * @param username
	 *            The user who has bought the game
	 * @param game
	 *            The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if (username == null || game == null) {
			throw new IllegalArgumentException();
		}
		preOrderGame(root, username, game);
	}

	private void preOrderGame(User subTree, String username, Game game) {
		if (subTree != null) {
			if (subTree.getUsername().equals(username)) {
				subTree.getGames().addGame(game);
			}
			preOrderGame(subTree.getLeft(), username, game);
			preOrderGame(subTree.getRight(), username, game);
		}
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add
	 * it to their trophies.
	 * 
	 * @param username
	 *            The user who has earned a new trophy
	 * @param trophy
	 *            The trophy to be added
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if (username == null || trophy == null) {
			throw new IllegalArgumentException();
		}
		preOrderTrophy(root, username, trophy);
	}

	private void preOrderTrophy(User subTree, String username, Trophy trophy) {
		if (subTree != null) {
			if (subTree.getUsername().equals(username)) {
				subTree.getTrophies().add(trophy);
			}
			preOrderTrophy(subTree.getLeft(), username, trophy);
			preOrderTrophy(subTree.getRight(), username, trophy);
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news,
	 * except that it may have ruined your tree structure! A node move may be in
	 * order.
	 * 
	 * @param username
	 *            The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if (username == null) {
			throw new IllegalArgumentException();
		}
		User user = new User();
		user = preOrderUsername(root, user, username);
		deFriend(user);
		if (user != null) {
			user.setLevel(user.getLevel() + 1);
		}
		beFriend(user);
	}

	private User preOrderUsername(User root2, User user, String username) {
		if (root2 != null) {
			if (root2.getUsername().equals(username)) {
				user = root2;
				return user;
			}
			user = preOrderUsername(root2.getLeft(), user, username);
			user = preOrderUsername(root2.getRight(), user, username);
		}
		return user;
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this is
	 * to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according
	 * to AVL rules.
	 * 
	 * @param friend
	 *            The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException
	 *             if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		root = insert(friend, root);
		return true;
	}

	private User insert(User friend, User root) {
		if (root == null) {
			root = friend;
		} else if (friend.getKey() - root.getKey() < 0) {
			root.setLeft(insert(friend, root.getLeft()));

			if (height(root.getLeft()) - height(root.getRight()) == 2) {
				if ((friend.getKey() - root.getKey()) < 0) {
					root = singleRotateLeft(root);
				} else {
					root = doubleRotateWithLeft(root);
				}
			}
		} else if (friend.getKey() - root.getKey() > 0) {
			root.setRight(insert(friend, root.getRight()));

			if (height(root.getRight()) - height(root.getLeft()) == 2) {
				if (friend.getKey() - root.getRight().getKey() < 0) {
					root = doubleRotateWithRight(root);
				} else {
					root = singleRotateRight(root);
				}
			}
		}

        root.height = Math.max( height( root.getLeft() ), height( root.getRight() ) ) + 1;
		return root;
	}

	private User doubleRotateWithRight(User x) {
		x.setRight(singleRotateLeft(x.getRight()));
		return singleRotateRight(x);
	}

	private User doubleRotateWithLeft(User x) {
		x.setLeft(singleRotateRight(x.getLeft()));
		return singleRotateLeft(x);
	}

	private User singleRotateRight(User w) {
		User x = w.getRight();

		w.setRight(x.getLeft());
		x.setLeft(w);

        x.height=Math.max(height(x.getLeft()),w.height)+1;
        w.height=Math.max(height(w.getLeft()),height(w.getRight()))+1;
		return x;
	}

	private User singleRotateLeft(User x) {
		User w = x.getLeft();
		x.setLeft(w.getRight());
		w.setRight(x);
		
        x.height=Math.max(height(x.getLeft()),height(x.getRight()))+1;
        w.height=Math.max(height(w.getLeft()),x.height)+1;
		return w;
	}

	public int height(User p){
        return p == null ? -1 : p.height;
    }

	public int getMaxDepth(User root, User user) {
		if (root == null||root==user)
			return 0;
		else {
			int left = 0;
			int right =0;
			if(user.getKey()<root.getKey()) {
				left = getMaxDepth(root.getLeft(),user);
			} else {
				right = getMaxDepth(root.getRight(),user);
			}
			return 1 + Math.max(left, right);
		}
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret
	 * scrap-book that you keep hidden underneath your pillow. This method
	 * should print out the details of each user, traversing the tree in order.
	 * 
	 * @return A string version of the tree
	 */
	public String toString() {
		List<User> userList = new LinkedList<>();
		preOrderString(root, userList);

		String str = "";
		for (User user : userList) {
			str += user.toString() + "\n";
		}
		return str.substring(0, str.length() - 1);
	}

	private void preOrderString(User root2, List<User> userList) {
		if (root2 != null) {
			preOrderString(root2.getLeft(), userList);
			userList.add(root2);
			preOrderString(root2.getRight(), userList);
		}
	}
}
