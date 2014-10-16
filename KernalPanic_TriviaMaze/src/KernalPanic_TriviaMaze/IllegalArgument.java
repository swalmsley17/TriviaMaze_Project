package KernalPanic_TriviaMaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalArgument {
	Player testPlayer;

	@Before
	public void setUp() throws Exception {
		testPlayer = new Player("ADB");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldThrowNullPointerException() {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("Cannot store null a String.");
		Player p1 = new Player(null);
	}

	@Test
	public void shouldThrowIllegalStateException() {
		expectedEx.expect(IllegalStateException.class);
		expectedEx
				.expectMessage("Cannot have a negative number in numCorrect or numWrong.");
		testPlayer.setNumCorrect(-1);
		testPlayer.setNumWrong(-12);
	}

}
