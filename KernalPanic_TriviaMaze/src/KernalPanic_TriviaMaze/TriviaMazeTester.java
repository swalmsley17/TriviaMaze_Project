package KernalPanic_TriviaMaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TriviaMazeTester {
	Player testPlayer;
	Trivia testTrivia;
	PromptUser prompt;

	@Before
	public void setUp() throws Exception {
		testPlayer = new Player("ADB");
		prompt = new PromptUser();
		// testTrivia=new Trivia();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitals() {
		Player p1 = new Player("Sts");
		assertEquals("STS", p1.getInitials());
		assertEquals("ADB", testPlayer.getInitials());
	}

	@Test
	public void testNumCorrect() {
		assertEquals(0, testPlayer.getNumCorrect());
		testPlayer.setNumCorrect(10);
		assertEquals(10, testPlayer.getNumCorrect());
	}

	@Test
	public void testNumWrong() {
		assertEquals(0, testPlayer.getNumWrong());
		testPlayer.setNumWrong(20);
		assertEquals(20, testPlayer.getNumWrong());
	}

	
}
