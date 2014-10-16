package Test;
import java.util.LinkedList;

import Database.*;
import KernalPanic_TriviaMaze.Trivia;
public class DBTester {
	public static void main(String[] args) throws ClassNotFoundException{
		QuestionDriver test = new QuestionDriver("trivia.db");
		Trivia x = (Trivia) test.getRandomQuestions(1, "all");
		System.out.println(x.getQuestion());
		test.close();
	}
}
