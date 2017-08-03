package Database;

/**
 * Class to represent a single linked-list of Database.Game objects. Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

	public Game head;

	public GameList(Game head) {
		this.head = head;
	}

	public String toString() {
		// "Assassin's Creed IV: Black Flag", released on: Nov 29, 2013
		// "Child of Light", released on: May 01, 2014
		String str = "";
		Game temp = head;
		if (temp == null) {
			return "Empty game list";
		}
		while (temp.getNext()!=null) {
			str += temp.toString() + "\n";
			temp = temp.getNext();
		}
		str += temp.toString();
		return str;
	}

	public void addGame(Game game) {
		// This method should add the provided game to the end of your
		// linkedlist.
		if (game == null) {
			throw new IllegalArgumentException();
		}
		if (head == null) {
			head = game;
		} else {
			Game temp = head;
			while (temp.getNext() != null) {
				if (temp == game) {
					return;
				}
				temp = temp.getNext();
			}
			if (temp == game) {
				return;
			}
			temp.setNext(game);
		}
	}

	public Game getGame(String name) {
		// getGame should traverse the linked list to find a game with a
		// matching name
		if (name == null) {
			throw new IllegalArgumentException();
		}

		Game temp = head;
		while (temp != null) {
			if (temp.getName().equals(name)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}

	public void removeGame(String name) {
		// search the linked list for the target game and remove it from the
		// list.
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (head.getName().equals(name)) {
			head = head.getNext();
			return;
		}
		Game temp = head;
		while (temp.getNext() != null) {
			if (temp.getNext().getName().equals(name)) {
				Game node = temp.getNext().getNext();
				temp.setNext(node);
				return;
			}
			temp = temp.getNext();
		}
	}

	public void removeGame(Game game) {
		// search the linked list for the target game and remove it from the
		// list.
		if (game == null) {
			throw new IllegalArgumentException();
		}

		if (head == game) {
			head = head.getNext();
			return;
		}
		Game temp = head;
		while (temp.getNext() != null) {
			if (temp.getNext() == game) {
				Game node = temp.getNext().getNext();
				temp.setNext(node);
				return;
			}
			temp = temp.getNext();
		}
	}
}
