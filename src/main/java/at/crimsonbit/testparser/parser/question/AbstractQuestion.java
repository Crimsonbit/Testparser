package at.crimsonbit.testparser.parser.question;

import java.util.List;

import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public class AbstractQuestion {
	protected IKey<?>[] keys;
	protected int prefix;

	protected int difficulty;

	protected String subject;

	protected List<Task<?>> tasks;

	protected Solution<?>[] solve;

	protected List<String> help;

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

	public List<Task<?>> getTasks() {
		return tasks;
	}

	public Task<?>[] getSolve() {
		return solve;
	}

	public List<String> getHelp() {
		return help;
	}

	public int getPrefix() {
		return prefix;
	}
}
