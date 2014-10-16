package Database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

import FileUtil.FileOpener;
import KernalPanic_TriviaMaze.Trivia;

public class createDataBase {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		String[] files = getFiles("themes/");
		initFiles(files);
	}
	
	//Get files in themes directory
	public static String[] getFiles(String directory) throws IOException{
		String files = "";
		final File folder = new File("themes/");
		files = getFilesInFolder(folder, files);
		return files.split(",");
	}
	
	public static String getFilesInFolder(final File folder, String input){
	    for (File file : folder.listFiles()) {
	    	input += file.getPath() + ",";
	    }
	    return input;
	}
	
	public static void initFiles(String[] paths) throws ClassNotFoundException, SQLException{
		QuestionDriver db = new QuestionDriver("trivia.db");
		db.setUpNewDB();
		for(String x:paths){
			int size = FileOpener.fileSize(x);
			Scanner fin = FileOpener.openFile(x);
			String theme = fin.nextLine();
			Trivia [] fileQuestions=FileOpener.readQuestions(fin, size);
			for(int i = 0; i < size; i++){
				//todo plug in database connections
				db.addQuestion(fileQuestions[i], theme);
			}
		}
		db.close();
	}
}
