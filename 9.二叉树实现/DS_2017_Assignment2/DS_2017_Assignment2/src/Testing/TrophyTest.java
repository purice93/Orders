package Testing;

import Database.Game;
import Database.Trophy;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TrophyTest extends DSUnitTesting {

	private String name;
	private Trophy.Rank rank;
	private Trophy.Rarity rarity;
	private Calendar obtained;
	private Game game;

	@Before
	public void initialise() {
		name = "What Did You Call Me?";
		rank = Trophy.Rank.BRONZE;
		rarity = Trophy.Rarity.RARE;
		obtained = new GregorianCalendar(2014, 4, 4);
		game = new Game("inFamous: Second Son", new GregorianCalendar(2014, 3, 21), 48);
	}

	@Test
	public void testConstructor() {
		AssignmentMarker.marks.put("Trophy:Constructor", 2.0f);
		try {
			Trophy t = new Trophy(name, rank, rarity, obtained, game);

			assertEquals("Checking that the name was correctly set", name, t.getName());
			assertEquals("Checking that the rank was correctly set", rank, t.getRank());
			assertEquals("Checking that the rarity was correctly set", rarity, t.getRarity());
			assertEquals("Checking that the obtained date was correctly set", obtained, t.getObtained());
			assertEquals("Checking that the parent game was correctly set", game, t.getGame());
		}
		catch (Exception e) {
			fail("Checking that the User constructor does not throw any exceptions");
		}
	}

	@Test
	public void toStringTest() {
		AssignmentMarker.marks.put("Trophy:toString", 3.0f);
		Trophy t = new Trophy(name, rank, rarity, obtained, game);
		assertEquals("Checking that tostring returns the correct string", "\"What Did You Call Me?\", rank: BRONZE, " +
				"rarity: RARE, obtained on: May 04, 2014", t.toString());
	}
}
