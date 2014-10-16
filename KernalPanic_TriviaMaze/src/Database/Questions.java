package Database;

import java.sql.SQLException;
import java.util.List;

public interface Questions {
	List returnCategory(String Cat);
	Object getQuestion(int qID, String cat);
	String[] answerPossibilites(int qID);
	String answer(int qID);
	String questionType(int qID);
	String getCategory(int qID);
	void setUpNewDB() throws SQLException;
	void addQuestion(Object o, String cat) throws SQLException;
	Object getRandomQuestions(int numOfQuestions, String cat);
	int minqID();
	int maxqID();
}
