package at.crimsonbit.testparser.parser.question;

import at.crimsonbit.testparser.parser.question.mapping.IKey;

public class AbstractQuestion {
	protected IKey<?>[] keys;
	protected int prefix;

	public int getPrefix() {
		return prefix;
	}

	protected int difficulty;
	protected String subject;

	public IKey<?>[] getKeys() {
		return keys;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getSubject() {
		return subject;
	}

	public Task[] getTasks() {
		return tasks;
	}

	public Task[] getSolve() {
		return solve;
	}

	public String[] getHelp() {
		return help;
	}

	protected Task[] tasks;
	protected Task[] solve;
	protected String[] help;
}
