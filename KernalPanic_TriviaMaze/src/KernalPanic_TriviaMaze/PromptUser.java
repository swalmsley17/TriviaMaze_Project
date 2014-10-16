package KernalPanic_TriviaMaze;

import java.util.Scanner;

import Database.QuestionDriver;

public class PromptUser {
	private Scanner kb;

	public PromptUser() {
		this.kb = new Scanner(System.in);
	}

	public String forInitials() {
		String initials = null;

		do {
			System.out.print("Please enter your initials: ");
			initials = kb.nextLine();
			if (initials.length() > 5)
				System.out
						.println("Please only enter your initials (5 chars maz).");

		} while (initials.length() > 5);

		return initials;
	}

	public int forMazeSize() {
		char mazeChar;
		String kbInput;

		do {
			displayMazeSizes();
			System.out.print("Size (S,M,L): ");
			kbInput = kb.nextLine();
			mazeChar = kbInput.toLowerCase().charAt(0);

			if (mazeCharLoopController(mazeChar))
				System.out.println(mazeChar + " is not an option.\n");

		} while (mazeCharLoopController(mazeChar));

		return mazeInt(mazeChar);
	}

	private boolean mazeCharLoopController(char mazeChar) {
		return mazeChar != 's' && mazeChar != 'm' && mazeChar != 'l';
	}

	private int mazeInt(char mazeChar) {

		if (mazeChar == 's')
			return 6;
		else if (mazeChar == 'm')
			return 10;
		return 14;
	}

	private void displayMazeSizes() {
		System.out.println("Please select a maze size");
		System.out.println("Small 6x6");
		System.out.println("Medium 10x10");
		System.out.println("Large 14x14");
	}

	public String forDirection() {
		String direction;

		do {
			System.out
					.println("Which direction would you like to travel? \nW, A, S, or D: ");
			direction = kb.nextLine();
			if (directionLoopController(direction))
				System.out.println(direction
						+ " is not a direction. Please try again.");
		} while (directionLoopController(direction));
		return direction;
	}

	private boolean directionLoopController(String direction) {
		return !direction.equalsIgnoreCase("w")
				&& !direction.equalsIgnoreCase("a")
				&& !direction.equalsIgnoreCase("s")
				&& !direction.equalsIgnoreCase("d");
	}

	public String forAnswer() {
		String answer;
		System.out.println("Your answer: ");
		answer = kb.nextLine();
		return answer;
	}

	public void DisplayWelcome() {
		System.out.println("Welcome to Kernal Panic's Trivia Maze");
	}

	public void displayWinEnding(Player currentPlayer) {
		System.out.println("Congratulations " + currentPlayer.getInitials()
				+ ", You've won!");
		System.out.println("Number of questions correct: "
				+ currentPlayer.getNumCorrect());
		System.out.println("Number of questions wrong: "
				+ currentPlayer.getNumWrong());
		endGame();
	}

	public void displayLoseEnding(Player currentPlayer) {
		System.out.println("You were unable to complete the maze. You lose.");
		System.out.println("Number of questions correct: "
				+ currentPlayer.getNumCorrect());
		System.out.println("Number of questions wrong: "
				+ currentPlayer.getNumWrong());
		endGame();
	}

	private void endGame() {
		System.out.println("Press any key to close the game.");
		kb.nextLine();
	}

	public String displayQuestion(QuestionDriver quizMaster) {
		Trivia randomQuestion = (Trivia) quizMaster
				.getRandomQuestions(1, "all");
		System.out.println(randomQuestion.getQuestion());

		String[] str = randomQuestion.getOptions();

		printOptions(str);

		return randomQuestion.getAnswer();

	}

	private void printOptions(String[] str) {
		for (int i = 0; i < str.length; i++)
			System.out.println(str[i]);
	}

	public void close() {
		this.kb = null;
	}
}
