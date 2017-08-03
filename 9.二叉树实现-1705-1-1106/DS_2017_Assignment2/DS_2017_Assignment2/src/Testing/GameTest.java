package Testing;

import Database.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GameTest extends DSUnitTesting {
	private String name;
	private Calendar released;
	private int totalTrophies;

	@Before
	public void initialise() {
		name = "Assassin's Creed IV: Black Flag";
		released = new GregorianCalendar(2013, 10, 29);
		totalTrophies = 14;
	}

	@Test
	public void testConstructor() {
		AssignmentMarker.marks.put("Game:Constructor", 2.0f);
		try {
			Game g = new Game(name, released, totalTrophies);

			assertEquals("Checking that the name has been correctly set", name, g.getName());
			assertEquals("Checking that the release date has been correctly set", released, g.getReleased());
			assertEquals("Checking that the total trophies has been correctly set", totalTrophies, g.getTotalTrophies());
		}
		catch (Exception e) {
			fail("Checking that the Game constructor does not throw any exceptions");
		}
	}

	@Test
	public void toStringTest() {
		AssignmentMarker.marks.put("Game:toString", 3.0f);
		Game g = new Game(name, released, totalTrophies);

		assertEquals("Checking that toString returns the correct String", "\"Assassin's " +
				"Creed IV: Black Flag\", released on: Nov 29, 2013", g.toString());
	}
}
