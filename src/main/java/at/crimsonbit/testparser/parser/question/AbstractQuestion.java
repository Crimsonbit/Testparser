package at.crimsonbit.testparser.parser.question;

import at.crimsonbit.testparser.parser.question.mapping.IKey;

public class AbstractQuestion {
	protected IKey<?>[] keys;
	protected int prefix;
	protected Task[] tasks;
	protected Task[] solve;
	protected String[] help;
}
