package KernalPanic_TriviaMaze;

public class Player {
	private String initials;
	private int numCorrect;
	private int numWrong;

	public Player(String init) {
		if (init == null)
			throw new NullPointerException("Cannot store null a String.");
		this.initials = init.toUpperCase();
		this.numCorrect = 0;
		this.numWrong = 0;
	}

	public String getInitials() {
		return this.initials;
	}

	public int getNumCorrect() {
		return this.numCorrect;
	}

	public int getNumWrong() {
		return this.numWrong;
	}

	private void setInitals(String str) {
		if (str == null)
			throw new NullPointerException("Cannot store a null String.");
		this.initials = str;
	}

	public void setNumCorrect(int correct) {
		if (correct < 0)
			throw new IllegalStateException(
					"Cannot have a negative number in numCorrect or numWrong.");
		this.numCorrect = correct;
	}

	public void setNumWrong(int wrong) {
		if (wrong < 0)
			throw new IllegalStateException(
					"Cannot have a negative number in numCorrect or numWrong.");
		this.numWrong = wrong;
	}

	public String toString() {
		String playerStr;

		playerStr = "Initals: " + this.initials
				+ "\nNumber of questions correct: " + this.numCorrect
				+ "\nNumber of questions wrong: " + this.numWrong;

		return playerStr;
	}
}
