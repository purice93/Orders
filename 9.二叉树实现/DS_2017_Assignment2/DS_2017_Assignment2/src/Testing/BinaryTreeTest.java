package Testing;

import Database.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class BinaryTreeTest extends DSUnitTesting {
	public BinaryTree bt;

	// Start testing setup (see assignment spec for tree diagrams)
	Game massEffect, persona, yooka, yooka2, prey, prey2, prey3, crash, crash2;
	GameList gl1, gl2, gl3, gl4;

	Calendar c;
	Trophy massEffectT1, massEffectT2, personaT1, personaT2, personaT3, yookaT1, preyT1, preyT2, preyT3, preyT4, preyT5,
			crashT1, crashT2;

	ArrayList<Trophy> tl1, tl2, tl3, tl4, tl5;

	Calendar dob;
	
	User oberon, pippin, medraut, astaroth, lunete, guiomar, faust, sophronia, haidee, dulcinea, cosette, nelida;

	/**
	 * There's a LOT of setting up to do here. Refer to the assignment spec for
	 * a diagram explaining how this tree is constructed.
	 */
	@Before
	public void initialise() {
		massEffect = new Game("Mass Effect Andromeda", new GregorianCalendar(2017, 3, 21), 56);
		persona = new Game("Persona 5", new GregorianCalendar(2017, 4, 4), 49);
		yooka = new Game("Yooka-Laylee", new GregorianCalendar(2017, 4, 11), 36);
		yooka2 = new Game("Yooka-Laylee", new GregorianCalendar(2017, 4, 11), 36);
		prey = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
		prey2 = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
		prey3 = new Game("Prey", new GregorianCalendar(2017, 5, 5), 50);
		crash = new Game("Crash Bandicoot N. Sane Trilogy", new GregorianCalendar(2017, 6, 30), 88);
		crash2 = new Game("Crash Bandicoot N. Sane Trilogy", new GregorianCalendar(2017, 6, 30), 88);

		gl1 = new GameList(yooka); {
			gl1.addGame(massEffect);
			gl1.addGame(persona);
		}
		gl2 = new GameList(prey); {
			gl2.addGame(crash);
		}
		gl3 = new GameList(prey2);
		gl4 = new GameList(prey3); {
			gl4.addGame(crash2);
			gl4.addGame(yooka2);
		}

		c = new GregorianCalendar(2017, 2, 26); // Trophies can have the same date - we don't care for these tests

		massEffectT1 = new Trophy("Shady Trees", Trophy.Rank.GOLD, Trophy.Rarity.ULTRA_RARE, c, massEffect);
		massEffectT2 = new Trophy("Keep", Trophy.Rank.SILVER, Trophy.Rarity.RARE, c, massEffect);
		personaT1 = new Trophy("War Never Changes", Trophy.Rank.BRONZE, Trophy.Rarity.COMMON, c, persona);
		personaT2 = new Trophy("The Nuclear Option", Trophy.Rank.SILVER, Trophy.Rarity.UNCOMMON, c, persona);
		personaT3 = new Trophy("Prepared for the Future", Trophy.Rank.GOLD, Trophy.Rarity.UNCOMMON, c, persona);
		yookaT1 = new Trophy("Platinum", Trophy.Rank.PLATINUM, Trophy.Rarity.ULTRA_RARE, c, yooka);
		preyT1 = new Trophy("Xenonaut", Trophy.Rank.BRONZE, Trophy.Rarity.RARE, c, prey);
		preyT2 = new Trophy("Walked the Path", Trophy.Rank.GOLD, Trophy.Rarity.VERY_RARE, c, prey);
		preyT3 = new Trophy("Humpty Dumpty", Trophy.Rank.BRONZE, Trophy.Rarity.VERY_RARE, c, prey);
		preyT4 = new Trophy("Can't Touch This!", Trophy.Rank.BRONZE, Trophy.Rarity.RARE, c, prey);
		preyT5 = new Trophy("Overkill", Trophy.Rank.SILVER, Trophy.Rarity.RARE, c, prey);
		crashT1 = new Trophy("Expert Fortune Hunter", Trophy.Rank.BRONZE, Trophy.Rarity.UNCOMMON, c, crash);
		crashT2 = new Trophy("Platinum", Trophy.Rank.PLATINUM, Trophy.Rarity.ULTRA_RARE, c, crash);

		tl1 = new ArrayList<Trophy>(); {
			tl1.add(yookaT1);
			tl1.add(personaT1);
			tl1.add(massEffectT2);
		}
		tl2 = new ArrayList<Trophy>(); {
			tl2.add(personaT1);
			tl2.add(personaT2);
			tl2.add(personaT3);
			tl2.add(massEffectT2);
		}
		tl3 = new ArrayList<Trophy>(); {
			tl3.add(crashT1);
			tl3.add(crashT2);
			tl3.add(preyT4);
			tl3.add(preyT3);
			tl3.add(preyT1);
		}
		tl4 = new ArrayList<Trophy>(); {
			tl4.add(preyT1);
			tl4.add(preyT2);
			tl4.add(preyT5);
		}
		tl5 = new ArrayList<Trophy>(); {
			tl5.add(crashT2);
			tl5.add(yookaT1);
		}

		dob = new GregorianCalendar(1980, 4, 23); // Users can have the same dob - we don't care for these tests
		
		oberon = new User("Oberon", dob, 7);
		pippin = new User("Pippin", dob, 10);
		medraut = new User("Medraut", dob, 8);
		astaroth = new User("Astaroth", dob, 9);
		lunete = new User("Lunete", dob, 12);
		guiomar = new User("Guiomar", dob, 14);
		faust = new User("Faust", dob, 4);
		sophronia = new User("Sophronia", dob, 6);
		haidee = new User("Haidee", dob, 5);
		cosette = new User("Cosette", dob, 3);
		dulcinea = new User("Dulcinea", dob, 3);
		nelida = new User("Nelida", dob, 1);

		oberon.setGames(gl1);
		oberon.setTrophies(tl1);
		oberon.setLeft(faust);
		oberon.setRight(pippin);

		
		faust.setGames(gl2);
		faust.setTrophies(tl3);
		faust.setLeft(cosette);
		faust.setRight(sophronia);


		cosette.setGames(gl3);
		cosette.setTrophies(tl4);
		cosette.setLeft(nelida);
		cosette.setRight(dulcinea);


		nelida.setGames(gl3);
		nelida.setTrophies(tl4);
		
		
		dulcinea.setGames(gl1);
		dulcinea.setTrophies(tl1);
		

		sophronia.setGames(gl2);
		sophronia.setTrophies(tl3);
		sophronia.setLeft(haidee);
		
		
		haidee.setGames(gl3);
		haidee.setTrophies(tl4);
		
		
		pippin.setGames(gl1);
		pippin.setTrophies(tl2);
		pippin.setLeft(medraut);
		pippin.setRight(lunete);


		medraut.setGames(gl4);
		medraut.setTrophies(tl5);
		medraut.setRight(astaroth);


		astaroth.setGames(gl2);
		astaroth.setTrophies(tl3);


		lunete.setGames(gl1);
		lunete.setTrophies(tl1);
		lunete.setRight(guiomar);


		guiomar.setGames(gl2);
		guiomar.setTrophies(tl4);


		bt = new BinaryTree();
		bt.root = oberon;
	}

	@Test
	public void beFriendNullArg() {
		AssignmentMarker.marks.put("BinaryTree:beFriendNullArg", 1.0f);
		try {
			bt.beFriend(null);

			fail("Checking that beFriend throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void beFriendDuplicate() {
		AssignmentMarker.marks.put("BinaryTree:beFriendDuplicate", 1.0f);
		assertFalse("Checking that beFriend returns false when user already in tree (1)", bt.beFriend(sophronia));
	}

	@Test
	public void beFriend() {
		AssignmentMarker.marks.put("BinaryTree:beFriend", 6.0f);
		BinaryTree emptyBT = new BinaryTree();
		User bob = new User("bob", dob, 6);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		assertTrue("Checking that beFriend returns true when adding to an empty tree", emptyBT.beFriend(bob));
		assertEquals("Checking that the root has been correctly set", bob, emptyBT.root);

		assertTrue("Checking that beFriend returns true when adding a new user (1)", bt.beFriend(bob));
		assertEquals("Checking that Sophronia's right variable correctly set", bob, sophronia.getRight());

		sophronia.setRight(null);
		bob = new User("bpb", dob, 7);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		assertTrue("Checking that beFriend returns true when adding a new user (2)", bt.beFriend(bob));
		assertEquals("Checking that Medrauts's left variable correctly set", bob, medraut.getLeft());
	}

	@Test
	public void deFriendNullArg() {
		AssignmentMarker.marks.put("BinaryTree:deFriendNullArg", 1.0f);
		try {
			bt.deFriend(null);

			fail("Checking that deFriend throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void deFriendNonExistent() {
		AssignmentMarker.marks.put("BinaryTree:deFriendNonExistent", 1.0f);
		User bob = new User("bob", dob, 6);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		assertFalse("Checking that deFriend returns false when user not in tree", bt.deFriend(bob));
	}

	@Test
	public void deFriend() {
		AssignmentMarker.marks.put("BinaryTree:deFriend", 7.0f);
		assertTrue("Checking that deFriend returns true when removing a user (1)", bt.deFriend(astaroth));
		assertEquals("Checking that Medraut's right variable correctly set", null, medraut.getRight());

		assertTrue("Checking that deFriend returns true when removing a user (2)", bt.deFriend(haidee));
		assertEquals("Checking that Sophronia's left variable correctly set", null, sophronia.getLeft());

	 	assertTrue("Checking that deFriend returns true when removing a user (3)", bt.deFriend(faust));
		assertEquals("Checking that Oberon's right correctly set (1)", dulcinea.getKey(), oberon.getLeft().getKey(), 0.01);
		assertEquals("Checking that Oberon's right correctly set (2)", dulcinea.getUsername(), oberon.getLeft().getUsername());
		assertEquals("Checking that Oberon's right correctly set (3)", dulcinea.getTrophies(), oberon.getLeft().getTrophies());

		assertTrue("Checking that deFriend returns true when removing the root", bt.deFriend(oberon));
		assertEquals("Checking that Sophronia is the new root", sophronia.getKey(), bt.root.getKey(), 0.01);
		assertEquals("Checking that Sophronia is the new root", sophronia.getUsername(), bt.root.getUsername());
		assertEquals("Checking that Sophronia is the new root", sophronia.getTrophies(), bt.root.getTrophies());
	}

	@Test
	public void countBetterPlayersNullArg() {
		AssignmentMarker.marks.put("BinaryTree:countBetterPlayersNullArg", 1.0f);
		try {
			bt.countBetterPlayers(null);

			fail("Checking that deFriend throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void countBetterPlayersNonExistent() {
		AssignmentMarker.marks.put("BinaryTree:countBetterPlayersNonExistent", 1.0f);
		User bob = new User("bob", dob, 6);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		assertEquals("Checking that countBetterPlayers returns -1 when user not in tree", -1, bt.countBetterPlayers(bob));
	}

	@Test
	public void countBetterPlayers() {
		AssignmentMarker.marks.put("BinaryTree:countBetterPlayers", 4.0f);
		assertEquals("Checking that countBetterPlayers returns the correct number (1)", 11, bt.countBetterPlayers(nelida));
		assertEquals("Checking that countBetterPlayers returns the correct number (2)", 0, bt.countBetterPlayers(guiomar));
		assertEquals("Checking that countBetterPlayers returns the correct number (3)", 6, bt.countBetterPlayers(sophronia));
		assertEquals("Checking that countBetterPlayers returns the correct number (4)", 3, bt.countBetterPlayers(astaroth));

		User bob = new User("aaaaaa", dob, 7);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		sophronia.setRight(bob);
		assertEquals("Checking that countBetterPlayers handles same-level users correctly", 5, bt.countBetterPlayers(bob));
	}

	@Test
	public void countWorsePlayersNullArg() {
		AssignmentMarker.marks.put("BinaryTree:countWorsePlayersNullArg", 1.0f);
		try {
			bt.countWorsePlayers(null);

			fail("Checking that countWorsePlayers throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void countWorsePlayersNonExistent() {
		AssignmentMarker.marks.put("BinaryTree:countWorsePlayersNonExistent", 1.0f);
		User bob = new User("bob", dob, 6);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		assertEquals("Checking that countWorsePlayers returns -1 when user not in tree", -1, bt.countWorsePlayers(bob));
	}

	@Test
	public void countWorsePlayers() {
		AssignmentMarker.marks.put("BinaryTree:countWorsePlayers", 4.0f);
		assertEquals("Checking that countWorsePlayers returns the correct number (1)", 0, bt.countWorsePlayers(nelida));
		assertEquals("Checking that countWorsePlayers returns the correct number (2)", 11, bt.countWorsePlayers(guiomar));
		assertEquals("Checking that countWorsePlayers returns the correct number (3)", 5, bt.countWorsePlayers(sophronia));
		assertEquals("Checking that countWorsePlayers returns the correct number (4)", 8, bt.countWorsePlayers(astaroth));

		User bob = new User("bob", dob, 6);
		bob.setGames(gl1);
		bob.setTrophies(tl1);
		sophronia.setRight(bob);
		assertEquals("Checking that countWorsePlayers handles same-level users correctly", 5, bt.countWorsePlayers(bob));
	}

	@Test
	public void mostPlatinums() {
		AssignmentMarker.marks.put("BinaryTree:mostPlatinums", 4.0f);
		assertEquals("Checking that mostPlatinums returns the correct user", medraut, bt.mostPlatinums());

		ArrayList<Trophy> tl6 = new ArrayList<Trophy>(); {
			tl6.add(crashT2);
			tl6.add(yookaT1);
			tl6.add(preyT2);
		}
		pippin.setTrophies(tl6);

		assertEquals("Checking that mostPlatinums correctly resolves ties", pippin, bt.mostPlatinums());
	}

	@Test
	public void addGameNullArg() {
		AssignmentMarker.marks.put("BinaryTree:addGameNullArg", 1.0f);
		try {
			bt.addGame(null, null);

			fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
		try {
			bt.addGame("bob", null);

			fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
		try {
			bt.addGame(null, prey);

			fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void addGame() {
		AssignmentMarker.marks.put("BinaryTree:addGame", 4.0f);
		Game cod3 = new Game("Call of Duty: Black Ops III", new GregorianCalendar(2015, 11, 6), 48);
		bt.addGame("Sophronia", cod3);

		assertEquals("Checking that addGame correctly adds a game", prey, sophronia.getGames().head);
		assertEquals("Checking that addGame correctly adds a game", crash, sophronia.getGames().head.getNext());
		assertEquals("Checking that addGame correctly adds a game", cod3, sophronia.getGames().head.getNext().getNext());
	}

	@Test
	public void addTrophyNullArg() {
		AssignmentMarker.marks.put("BinaryTree:addTrophyNullArg", 1.0f);
		try {
			bt.addTrophy(null, null);

			fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
		try {
			bt.addTrophy("bob", null);

			fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
		try {
			bt.addTrophy(null, preyT1);

			fail("Checking that addTrophy throws an IllegalArgumentException when null arg supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void addTrophy() {
		AssignmentMarker.marks.put("BinaryTree:addTrophy", 4.0f);
		bt.addTrophy("Astaroth", preyT2);

		assertEquals("Checking that addTrophy correctly adds a trophy", crashT1, astaroth.getTrophies().get(0));
		assertEquals("Checking that addTrophy correctly adds a trophy", crashT2, astaroth.getTrophies().get(1));
		assertEquals("Checking that addTrophy correctly adds a trophy", preyT4, astaroth.getTrophies().get(2));
		assertEquals("Checking that addTrophy correctly adds a trophy", preyT3, astaroth.getTrophies().get(3));
		assertEquals("Checking that addTrophy correctly adds a trophy", preyT1, astaroth.getTrophies().get(4));
		assertEquals("Checking that addTrophy correctly adds a trophy", preyT2, astaroth.getTrophies().get(5));
	}

	@Test
	public void levelUpNullArgs() {
		AssignmentMarker.marks.put("BinaryTree:levelUpNullArgs", 1.0f);
		try {
			bt.levelUp(null);

			fail("Checking that levelUp throws IllegalArgumentException when null args are supplied");
		}
		catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void levelUp() {
		AssignmentMarker.marks.put("BinaryTree:levelUp", 7.0f);
		bt.levelUp("Sophronia");
		assertEquals("Checking that levelUp correctly modifies tree (1)", "Oberon", bt.root.getUsername());

		bt.levelUp("Oberon");
		assertEquals("Checking that levelUp correctly modifies tree (2)", "Sophronia", bt.root.getUsername());
	}

	@Test
	public void toStringTest() {
		AssignmentMarker.marks.put("BinaryTree:toString", 3.0f);
		assertEquals("Checking that toString returns the correct String", outputString, bt.toString());
	}

	// Ridiculously long return string
	private String outputString = "User: Nelida\n" +
			"\n" +
			"Trophies: \n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Cosette\n" +
			"\n" +
			"Trophies: \n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Dulcinea\n" +
			"\n" +
			"Trophies: \n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
			"\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Yooka-Laylee\", released on: May 11, 2017\n" +
			"\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
			"\"Persona 5\", released on: May 04, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Faust\n" +
			"\n" +
			"Trophies: \n" +
			"\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Haidee\n" +
			"\n" +
			"Trophies: \n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Sophronia\n" +
			"\n" +
			"Trophies: \n" +
			"\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Oberon\n" +
			"\n" +
			"Trophies: \n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
			"\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Yooka-Laylee\", released on: May 11, 2017\n" +
			"\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
			"\"Persona 5\", released on: May 04, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Medraut\n" +
			"\n" +
			"Trophies: \n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
			"\"Yooka-Laylee\", released on: May 11, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Astaroth\n" +
			"\n" +
			"Trophies: \n" +
			"\"Expert Fortune Hunter\", rank: BRONZE, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"Can't Touch This!\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Humpty Dumpty\", rank: BRONZE, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Pippin\n" +
			"\n" +
			"Trophies: \n" +
			"\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
			"\"The Nuclear Option\", rank: SILVER, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
			"\"Prepared for the Future\", rank: GOLD, rarity: UNCOMMON, obtained on: Mar 26, 2017\n" +
			"\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Yooka-Laylee\", released on: May 11, 2017\n" +
			"\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
			"\"Persona 5\", released on: May 04, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Lunete\n" +
			"\n" +
			"Trophies: \n" +
			"\"Platinum\", rank: PLATINUM, rarity: ULTRA_RARE, obtained on: Mar 26, 2017\n" +
			"\"War Never Changes\", rank: BRONZE, rarity: COMMON, obtained on: Mar 26, 2017\n" +
			"\"Keep\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Yooka-Laylee\", released on: May 11, 2017\n" +
			"\"Mass Effect Andromeda\", released on: Apr 21, 2017\n" +
			"\"Persona 5\", released on: May 04, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980\n" +
			"User: Guiomar\n" +
			"\n" +
			"Trophies: \n" +
			"\"Xenonaut\", rank: BRONZE, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\"Walked the Path\", rank: GOLD, rarity: VERY_RARE, obtained on: Mar 26, 2017\n" +
			"\"Overkill\", rank: SILVER, rarity: RARE, obtained on: Mar 26, 2017\n" +
			"\n" +
			"Games: \n" +
			"\"Prey\", released on: Jun 05, 2017\n" +
			"\"Crash Bandicoot N. Sane Trilogy\", released on: Jul 30, 2017\n" +
			"\n" +
			"Birth Date: May 23, 1980";
}
