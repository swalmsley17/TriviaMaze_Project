package FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import KernalPanic_TriviaMaze.Trivia;

public class FileOpener {
	public static String promptForFile() {
		Scanner kb = new Scanner(System.in);
		String fname;

		System.out.print("File name: ");
		fname = kb.nextLine();

		return fname;
	}

	public static Scanner openFile(String fname) {
		boolean exist = false;
		Scanner fin;

		do {
			fin = null;
			try {
				fin = new Scanner(new File(fname));
				exist = true;
			} catch (FileNotFoundException e) {
				System.out
						.println("The file was not found --- Please try again");
				exist = false;
				fname = promptForFile();
			}

		} while (!exist);
		return fin;
	}

	public static String getTheme(Scanner fin) {
		return fin.nextLine();
	}

	public static int fileSize(String fname) {
		int size = 0;
		Scanner fin = openFile(fname);

		while (fin.hasNext()) {
			fin.nextLine();
			size++;
		}
		if (size == 0)
			return 0;
		return (size - 1) / 3;
	}

	public static Trivia[] readQuestions(Scanner fin, int numQuestions) {
		Trivia[] fileQuestions = new Trivia[numQuestions];
		String question;
		String[] options;
		String answer;
		Trivia que;

		for (int i = 0; i < numQuestions; i++) {
			question = fin.nextLine();
			options = fin.nextLine().split(",");
			answer = fin.nextLine();
			que = new Trivia(question, options, answer);
			fileQuestions[i] = que;
		}
		return fileQuestions;
	}
}
