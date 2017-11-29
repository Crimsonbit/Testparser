package at.crimsonbit.testparser.parser.question;

import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public class AbstractQuestion {
	protected IKey<?>[] keys;
	protected int prefix;

	protected int difficulty;

	protected String subject;

	protected Task<?>[] tasks;

	protected Solution<?>[] solve;

	protected String[] help;

	protected String name;

	public String getName() {
		return name;
	}

	public IKey<?>[] getKeys() {
		return keys;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getSubject() {
		return subject;
	}

	public Task<?>[] getTasks() {
		return tasks;
	}

	public Task<?>[] getSolve() {
		return solve;
	}

	public String[] getHelp() {
		return help;
	}

	public int getPrefix() {
		return prefix;
	}
}
