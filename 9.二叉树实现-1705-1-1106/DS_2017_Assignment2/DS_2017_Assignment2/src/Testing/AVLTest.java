package Testing;

import Database.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Tests an implementation of AVL tree balancing. Don't
 * be complacent if not every form of rotation is tested,
 * or the tests don't test the complete tree! A different,
 * unseen set of nodes will be used for marking so stick to
 * the algorithm and do your own extra testing.
 * UNSEEN TESTS WILL ALSO TEST RIGHT ROTATIONS!
 */
public class AVLTest extends DSUnitTesting {
	private BinaryTree bt;
	private User oberon, pippin, medraut, astaroth, lunete, guiomar, faust, sophronia, haidee, dulcinea, cosette, nelida;
	private Calendar dob;
	
	@Before
	public void initialise() {
		bt = new BinaryTree();
		dob = new GregorianCalendar(1980, 4, 23); // Users can have the same dob - we don't care for these tests
		Game massEffect = new Game("Mass Effect Andromeda", new GregorianCalendar(2017, 3, 21), 56);
		Calendar c = new GregorianCalendar(2016, 1, 28); // Trophies can have the same date - we don't care for these tests
		Trophy massEffectT1 = new Trophy("Shady Trees", Trophy.Rank.GOLD, Trophy.Rarity.ULTRA_RARE, c, massEffect);
		
		GameList gl = new GameList(massEffect);
		ArrayList<Trophy> tl = new ArrayList<Trophy>(); tl.add(massEffectT1);
		
		oberon = new User("Oberon", dob, 7); oberon.setGames(gl); oberon.setTrophies(tl);
		pippin = new User("Pippin", dob, 10); pippin.setGames(gl); pippin.setTrophies(tl);
		medraut = new User("Medraut", dob, 8); medraut.setGames(gl); medraut.setTrophies(tl);
		astaroth = new User("Astaroth", dob, 9); astaroth.setGames(gl); astaroth.setTrophies(tl);
		lunete = new User("Lunete", dob, 12); lunete.setGames(gl); lunete.setTrophies(tl);
		guiomar = new User("Guiomar", dob, 14); guiomar.setGames(gl); guiomar.setTrophies(tl);
		faust = new User("Faust", dob, 4); faust.setGames(gl); faust.setTrophies(tl);
		sophronia = new User("Sophronia", dob, 6); sophronia.setGames(gl); sophronia.setTrophies(tl);
		haidee = new User("Haidee", dob, 5); haidee.setGames(gl); haidee.setTrophies(tl);
		cosette = new User("Cosette", dob, 3); cosette.setGames(gl); cosette.setTrophies(tl);
		dulcinea = new User("Dulcinea", dob, 3); dulcinea.setGames(gl); dulcinea.setTrophies(tl);
		nelida = new User("Nelida", dob, 1); nelida.setGames(gl); nelida.setTrophies(tl);
	}
	
	@Test
	public void addAVLStage1() {
		AssignmentMarker.marks.put("AVL:addAVLStage1", 6.0f);
		bt.addAVL(nelida);
		bt.addAVL(cosette);
		bt.addAVL(dulcinea);
		
		assertEquals("Check root after rotation", cosette, bt.root);
		assertEquals("Check root left", nelida, bt.root.getLeft());
		assertEquals("Check root right", dulcinea, bt.root.getRight());
		
		bt.addAVL(faust);
		bt.addAVL(haidee);
		
		assertEquals("Check root after rotation", cosette, bt.root);
		assertEquals("Check root left", nelida, bt.root.getLeft());
		assertEquals("Check root right", faust, bt.root.getRight());
		assertEquals("Check Faust's left", dulcinea, faust.getLeft());
		assertEquals("Check Faust's right", haidee, faust.getRight());
		
		bt.addAVL(sophronia);
		
		assertEquals("Check new root", faust, bt.root);
		assertEquals("Check root left", cosette, bt.root.getLeft());
		assertEquals("Check root right", haidee, bt.root.getRight());
		assertEquals("Check that Faust's old left subtree moved", dulcinea, cosette.getRight());
	}
	
	@Test
	public void addAVLStage2() {
		AssignmentMarker.marks.put("AVL:addAVLStage2", 5.0f);
		// Stage 1 adds
		bt.addAVL(nelida);
		bt.addAVL(cosette);
		bt.addAVL(dulcinea);
		bt.addAVL(faust);
		bt.addAVL(haidee);
		bt.addAVL(sophronia);
		
		bt.addAVL(oberon);
		
		assertEquals("Check root right", sophronia, bt.root.getRight());
		assertEquals("Check Haidee's right", null, haidee.getRight());
		//assertEquals("Check Sophronia's left", haidee);
		assertEquals("Check Sophronia's left", haidee,sophronia.getLeft());
		
		bt.addAVL(medraut);
		bt.addAVL(astaroth);
		
		assertEquals("Check Oberon's right", null, oberon.getRight());
		assertEquals("Check Medraut's left", oberon, medraut.getLeft());
		assertEquals("Check Medraut's right", astaroth, medraut.getRight());
		
		bt.addAVL(pippin);
		
		assertEquals("Check Astaroth's right", pippin, astaroth.getRight());
		assertEquals("Check root right", medraut, bt.root.getRight());
		assertEquals("Check that Medraut's old left subtree moved", oberon, sophronia.getRight());
		assertEquals("Check Medraut's left", sophronia, medraut.getLeft());
		assertEquals("Check Medraut's right", astaroth, medraut.getRight());
	}
	
	@Test
	public void addAVLStage3() {
		AssignmentMarker.marks.put("AVL:addAVLStage3", 5.0f);
		// Stage 1 adds
		bt.addAVL(nelida);
		bt.addAVL(cosette);
		bt.addAVL(dulcinea);
		bt.addAVL(faust);
		bt.addAVL(haidee);
		bt.addAVL(sophronia);
		
		// Stage 2 adds
		bt.addAVL(oberon);
		bt.addAVL(medraut);
		bt.addAVL(astaroth);
		bt.addAVL(pippin);
		
		bt.addAVL(lunete);
		
		assertEquals("Check Pippin's right", lunete, pippin.getRight());
		assertEquals("Check Medraut's right", pippin, medraut.getRight());
		assertEquals("Check Pippin's left", astaroth, pippin.getLeft());
		assertEquals("Check Astaroth's left", null, astaroth.getLeft());
		assertEquals("Check Astaroth's right", null, astaroth.getRight());
		
		bt.addAVL(guiomar);
		
		assertEquals("Check root", medraut, bt.root);
		assertEquals("Check Medraut's left", faust, medraut.getLeft());
		assertEquals("Check Medraut's right", pippin, medraut.getRight());
		assertEquals("Check Faust's left", cosette, faust.getLeft());
		assertEquals("Check Faust's right", sophronia, faust.getRight());
		assertEquals("Check Sophronia's right", oberon, sophronia.getRight());
	}
}
