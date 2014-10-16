package KernalPanic_TriviaMaze;

import Database.*;
import Maze_Setup.Maze;
import Maze_Setup.MazeBuilder;
import Maze_Setup.Room;

public class TriviaMazeGame {
	public static void main(String[] args) throws ClassNotFoundException {
		PromptUser prompt = new PromptUser();

		prompt.DisplayWelcome();
		String init = prompt.forInitials();
		QuestionDriver quizMaster = new QuestionDriver("trivia.db");

		Player currentPlayer = new Player(init);
		int mazeSize = prompt.forMazeSize();

		// Generates the maze based on the size from the user.
		MazeBuilder builder = new MazeBuilder(mazeSize);
		Maze gameMaze = builder.build();

		startGame(gameMaze, prompt, quizMaster, currentPlayer);

		prompt.close();
	}

	private static void startGame(Maze gameMaze, PromptUser prompt,
			QuestionDriver quizMaster, Player currentPlayer) {
		Room currentRoom;
		String direction, answer = "", correctAnswer = "";

		do {
			currentRoom = gameMaze.getCurrentRoom();

			System.out.println("\n\n" + gameMaze.toString());
			do {
				direction = prompt.forDirection();
				if (!doorLoopController(direction, currentRoom))
					System.out
							.println("That door is locked. Please select another direction.");
			} while (!doorLoopController(direction, currentRoom));

			if (!doorOpened(direction, currentRoom)) {
				correctAnswer = prompt.displayQuestion(quizMaster);
				answer = errorCheckAnswer(correctAnswer);

				if (answer.equalsIgnoreCase(correctAnswer)) {
					movePlayer(gameMaze, direction, currentPlayer);
					openDoor(currentRoom, direction);
					System.out.println("Correct");
				} else
					lockDoor(gameMaze, currentPlayer, direction, correctAnswer);
			} else
				movePlayer(gameMaze, direction, currentPlayer);
		} while (!gameMaze.playerInExit() && gameMaze.mazeTraversal());
		if (gameMaze.playerInExit())
			prompt.displayWinEnding(currentPlayer);
		else
			prompt.displayLoseEnding(currentPlayer);
	}

	private static void openDoor(Room currentRoom, String direction) {
		if(direction.equalsIgnoreCase("w"))
			currentRoom.getNorth().open();
		else if(direction.equalsIgnoreCase("s"))
			currentRoom.getSouth().open();
		else if(direction.equalsIgnoreCase("d"))
			currentRoom.getEast().open();
		else
			currentRoom.getWest().open();		
	}

	private static boolean doorOpened(String direction, Room currentRoom) {
		if (direction.equalsIgnoreCase("w"))
			return currentRoom.getNorth().isOpen();
		else if (direction.equalsIgnoreCase("s"))
			return currentRoom.getSouth().isOpen();
		else if (direction.equalsIgnoreCase("d"))
			return currentRoom.getEast().isOpen();
		return currentRoom.getWest().isOpen();
	}

	private static boolean doorLoopController(String direction, Room currentRoom) {
		if (direction.equalsIgnoreCase("w"))
			return currentRoom.getNorth().canPass();
		else if (direction.equalsIgnoreCase("s"))
			return currentRoom.getSouth().canPass();
		else if (direction.equalsIgnoreCase("d"))
			return currentRoom.getEast().canPass();
		return currentRoom.getWest().canPass();
	}

	private static void lockDoor(Maze gameMaze, Player currentPlayer,
			String direction, String correctAnswer) {
		Room currentRoom = gameMaze.getCurrentRoom();

		if (direction.equalsIgnoreCase("w"))
			currentRoom.lockNorth();
		else if (direction.equalsIgnoreCase("s"))
			currentRoom.lockSouth();
		else if (direction.equalsIgnoreCase("d"))
			currentRoom.lockEast();
		else
			currentRoom.lockWest();
		System.out.println("Your answer was incorrect. The correct answer was "
				+ correctAnswer);
		currentPlayer.setNumWrong(currentPlayer.getNumWrong() + 1);
	}

	private static void movePlayer(Maze gameMaze, String direction,
			Player currentPlayer) {
		if (direction.equalsIgnoreCase("w"))
			gameMaze.moveNorth();
		else if (direction.equalsIgnoreCase("s"))
			gameMaze.moveSouth();
		else if (direction.equalsIgnoreCase("d"))
			gameMaze.moveEast();
		else
			gameMaze.moveWest();
		currentPlayer.setNumCorrect(currentPlayer.getNumCorrect() + 1);
	}

	private static String errorCheckAnswer(String correctAnswer) {
		String answer = "";
		PromptUser prompt = new PromptUser();

		if (checkQuestionType(correctAnswer)) {
			do {
				answer = prompt.forAnswer();
				if (multipleChoiceLoopController(answer))
					System.out.println(answer
							+ " is not an option. Please try again.");
			} while (multipleChoiceLoopController(answer));
		} else {
			do {
				System.out.println("(T/F)");
				answer = prompt.forAnswer();
				if (trueFalseLoopController(answer))
					System.out.println(answer
							+ " was not an option. Please try again. (T/F)");
			} while (trueFalseLoopController(answer));
		}

		prompt.close();
		return answer;
	}

	private static boolean checkQuestionType(String correctAnswer) {
		return correctAnswer.equalsIgnoreCase("a")
				|| correctAnswer.equalsIgnoreCase("b")
				|| correctAnswer.equalsIgnoreCase("c")
				|| correctAnswer.equalsIgnoreCase("d");
	}

	private static boolean trueFalseLoopController(String answer) {
		return !answer.equalsIgnoreCase("t") && !answer.equalsIgnoreCase("f");
	}

	private static boolean multipleChoiceLoopController(String answer) {
		return !answer.equalsIgnoreCase("a") && !answer.equalsIgnoreCase("b")
				&& !answer.equalsIgnoreCase("c")
				&& !answer.equalsIgnoreCase("d");
	}
}
