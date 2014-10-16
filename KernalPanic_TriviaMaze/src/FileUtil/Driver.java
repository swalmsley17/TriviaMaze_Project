package FileUtil;

import java.util.ArrayList;
import java.util.Scanner;

import KernalPanic_TriviaMaze.Trivia;

public class Driver {

	public static void main(String[] args) {
		String fname = FileOpener.promptForFile();
		int numQuestions = FileOpener.fileSize(fname);
		System.out.println("Number of questions: " + numQuestions);

		Scanner fin = FileOpener.openFile(fname);
		String theme = fin.nextLine();
		Trivia [] fileQuestions=FileOpener.readQuestions(fin, numQuestions);
	}

}
