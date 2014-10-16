package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import KernalPanic_TriviaMaze.Trivia;

public class QuestionDriver implements Questions {
	Connection conn;
	Statement statement;
	public QuestionDriver(String path) throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:"+path);
			statement = conn.createStatement();
			statement.setQueryTimeout(30);
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	public boolean close(){
		try{
			if(this.conn != null){
				conn.close();
				return true;
			}
			return false;
		}
		catch(SQLException e){
			return false;
		}
	}

	public List returnCategory(String cat) {
		try{
			List<Integer> questions = new LinkedList<Integer>();
			ResultSet rs = statement.executeQuery("select * from questions where category = '" + cat + "'");
			while(rs.next()){
				questions.add(rs.getInt("qID"));
		    }
			return questions;
		}
		catch(SQLException e){
			return null;
		}
	}
	
	public Object getQuestion(int qID, String cat) {
		try{
			Trivia t = null;
			ResultSet rs = statement.executeQuery("select * from questions where qID = " + qID + " and category like \"" + cat + "\"");
			while(rs.next()){
				String q = rs.getString("question");
				String options[] = rs.getString("options").split(",");
				String ans = rs.getString("answer");
				t = new Trivia(q, options, ans);
		    }
			return t;
		}
		catch(SQLException e){
			return null;
		}
	}

	
	public String[] answerPossibilites(int qID) {
		try{
			ResultSet rs = statement.executeQuery("select possibilities from questions where qID = " + qID);
			List<String> poss = new LinkedList<String>();
			String holder = "";
			String[] temp = null; 
			while(rs.next()){
				holder =  rs.getString("possibilities");
		    }
			temp = holder.split(",");
			return temp;
		}
		catch(SQLException e){
			return null;
		}
	}

	
	public String answer(int qID) {
		try{
			ResultSet rs = statement.executeQuery("select answer from questions where qID = " + qID);
			while(rs.next()){
				return rs.getString("answer");
		    }
			return null;
		}
		catch(SQLException e){
			return null;
		}
	}

	
	public String questionType(int qID) {
		try{
			ResultSet rs = statement.executeQuery("select type from questions where qID = " + qID);
			while(rs.next()){
				return rs.getString("type");
		    }
			return null;
		}
		catch(SQLException e){
			return null;
		}
	}
	
	public String getCategory(int qID) {
		// TODO Auto-generated method stub
		try{
			ResultSet rs = statement.executeQuery("select category from questions where qID = " + qID);
			while(rs.next()){
				return rs.getString("category");
		    }
			return null;
		}
		catch(SQLException e){
			return null;
		}
	}

	public void setUpNewDB() throws SQLException {
		this.statement.executeUpdate("drop table if exists questions");
		this.statement.executeUpdate("CREATE TABLE questions (`qID`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "question TEXT NOT NULL,options TEXT NOT NULL,"
				+ "answer TEXT NOT NULL,"
				+ "category	TEXT NOT NULL)");
		
	}

	
	public void addQuestion(Object o, String cat) throws SQLException {
		{
			String[] s = ((Trivia)o).getOptions();
			String options = "";
			for(String x:s){
				options+=x + ",";
			}
			options = options.substring(0, options.length() - 1);
			
			this.statement.executeUpdate("insert into questions values(null,'" + ((Trivia)o).getQuestion() + "','" + options + "','" + ((Trivia)o).getAnswer() + "','" + cat + "')");
			System.out.println("");
		}
	}

	
	public Object getRandomQuestions(int numOfQuestions, String cat) {
		Trivia ara = null;
		int min = this.minqID();
		int max = this.maxqID();
		LinkedList<Integer> shuffled = new LinkedList<Integer>();
		for(int i = 0; i < (max-min); i++){
			shuffled.add(min + i);
		}
		Collections.shuffle(shuffled);
			if(cat.compareToIgnoreCase("all") == 0){
				ara = (Trivia) this.getQuestion(shuffled.get(0), "%");
			}
			else{
				
			}
		return ara;
	}

	
	public int minqID() {
		try{
			int min = 0;
			ResultSet rs = statement.executeQuery("select qID from questions where qID = (select min(qID) from questions)");
			min = rs.getInt("qID");
			return min;
		}
		catch(SQLException e){
			return 0;
		}
	}
	
	public int maxqID() {
		try{
			int max = 0;
			ResultSet rs = statement.executeQuery("select qID from questions where qID = (select max(qID) from questions)");
			while(rs.next()){
				max = rs.getInt("qID");
		    }
			return max;
		}
		catch(SQLException e){
			return 0;
		}
	}
}
