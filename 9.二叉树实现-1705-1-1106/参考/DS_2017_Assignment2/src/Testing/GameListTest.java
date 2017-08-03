package Testing;

import Database.Game;
import Database.GameList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class GameListTest extends DSUnitTesting {

    private Game g1;
    private Game g2;
    private Game g3;
    private GameList gl;

    @Before
    public void initialise() {
        g1 = new Game("Assassin's Creed IV: Black Flag", new GregorianCalendar(2013, 10, 29), 10);
        g2 = new Game("Child of Light", new GregorianCalendar(2014, 4, 1), 24);
        g3 = new Game("Dragon Age: Inquisition", new GregorianCalendar(2014, 11, 18), 53);
        gl = new GameList(null);
    }

    @Test
    public void getGameNullArg() {
        AssignmentMarker.marks.put("GameList:getGameNullArg", 1.0f);
        try {
            gl.getGame(null);

            fail("Checking that getGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getGame() {
        AssignmentMarker.marks.put("GameList:getGame", 2.0f);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        Assert.assertEquals("Checking that getGame returns the correct Database.Game", g1, gl.getGame("Assassin's Creed IV: Black " +
                "Flag"));
        Assert.assertEquals("Checking that getGame returns the correct Database.Game", g3, gl.getGame("Dragon Age: Inquisition"));
        assertNull("Checking that getGame returns null if game not present", gl.getGame("Far Cry 4"));
    }

    @Test
    public void addGame() {
        AssignmentMarker.marks.put("GameList:addGame", 2.0f);
        gl.addGame(g2);
        gl.addGame(g1);

        Assert.assertEquals("Checking that the head is correctly set", g2, gl.head);
        Assert.assertEquals("Checking next set correctly", g1, g2.getNext());
        assertNull("Checking next set correctly", g1.getNext());
    }

    @Test
    public void addGameExists() {
        AssignmentMarker.marks.put("GameList:addGameExists", 1.0f);
        gl.addGame(g1);
        gl.addGame(g1);

        assertNull("Checking that a duplicate game is not added", g1.getNext());
    }

    @Test
    public void addGameNullArg() {
        AssignmentMarker.marks.put("GameList:addGameNullArg", 1.0f);
        try {
            gl.addGame(null);

            fail("Checking that addGame throws an IllegalArgumentException when null arg supplied");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void removeGameNullArg() {
        AssignmentMarker.marks.put("GameList:removeGameNullArg", 1.0f);
        try {
            String str = null;
            gl.removeGame(str);
            Game g = null;
            gl.removeGame(g);

            fail("Checking that both removeGame methods throw IllegalArgumentException when null args are supplied");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void removeGameString() {
        AssignmentMarker.marks.put("GameList:removeGameString", 2.0f);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        gl.removeGame("Crash Bandicoot");
        Assert.assertEquals("Checking that a non-existant name does not alter the list (1)", g1, gl.head);
        Assert.assertEquals("Checking that a non-existant name does not alter the list (2)", g2, gl.head.getNext());
        Assert.assertEquals("Checking that a non-existant name does not alter the list (3)", g3, gl.head.getNext().getNext());
        assertNull("Checking that a non-existant name does not alter the list (4)", gl.head.getNext().getNext().getNext());

        gl.removeGame(g1.getName());
        Assert.assertEquals("Checking that the head is correctly replaced", g2, gl.head);

        g3.setNext(g1);
        g1.setNext(null);
        gl.removeGame(g3.getName());
        Assert.assertEquals("Checking that middle game removed correctly", g1, g2.getNext());

        gl.removeGame(g1.getName());
        assertNull("Checking that last game correctly removed", g2.getNext());
    }

    @Test
    public void removeGameObject() {
        AssignmentMarker.marks.put("GameList:removeGameObject", 2.0f);
        gl.head = g1;
        g1.setNext(g2);
        g2.setNext(g3);

        gl.removeGame(new Game("Far Cry 4", new GregorianCalendar(2014, 11, 18), 47));
        Assert.assertEquals("Checking that a non-existant name does not alter the list (1)", g1, gl.head);
        Assert.assertEquals("Checking that a non-existant name does not alter the list (2)", g2, gl.head.getNext());
        Assert.assertEquals("Checking that a non-existant name does not alter the list (3)", g3, gl.head.getNext().getNext());
        assertNull("Checking that a non-existant name does not alter the list (4)", gl.head.getNext().getNext().getNext());

        gl.removeGame(g1);
        Assert.assertEquals("Checking that the head is correctly replaced", g2, gl.head);

        g3.setNext(g1);
        g1.setNext(null);
        gl.removeGame(g3);
        Assert.assertEquals("Checking that middle game removed correctly", g1, g2.getNext());

        gl.removeGame(g1);
        assertNull("Checking that last game correctly removed", g2.getNext());
    }

    @Test
    public void toStringTest() {
        AssignmentMarker.marks.put("GameList:toString", 3.0f);
        Assert.assertEquals("Testing that toString with an empty list returns the correct String", gl.toString(), "Empty game list");

        gl.head = g1;
        g1.setNext(g2);

        Assert.assertEquals("Testing that toString returns the correct String", "\"Assassin's " +
                "Creed IV: " +
                "Black Flag\", released on: Nov 29, 2013\n" + "\"Child of Light\", released on: May 01, 2014", gl.toString());
    }
}