package KernalPanic_TriviaMaze;

public class Trivia {
	private String question;
	private String [] options;
	private String answer;

	public Trivia(String q, String [] choices, String a) {
		this.question = q;
		this.options = choices;
		this.answer = a;
	}

	public void setQuestion(String q) {
		if (q == null)
			throw new NullPointerException("Cannot store a null String.");
		this.question = q;
	}

	public void setOptions(String[] choices) {
		if (choices.length==0)
			throw new IllegalStateException("The input list is empty.");
		this.options = choices;
	}

	public void setAnser(String a) {
		if (a == null)
			throw new NullPointerException("Cannot store a null String.");
		this.answer = a;
	}

	public String getQuestion() {
		return this.question;
	}

	public String[] getOptions() {
		return this.options;
	}

	public String getAnswer() {
		return this.answer;
	}
}
