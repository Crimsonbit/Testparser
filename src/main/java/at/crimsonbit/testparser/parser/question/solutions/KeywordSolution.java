package at.crimsonbit.testparser.parser.question.solutions;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.koloboke.collect.set.hash.HashObjSets;

import at.crimsonbit.testparser.api.IgnoreCaseString;
import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;
import at.crimsonbit.testparser.parser.question.tasks.Task;

/**
 * Solution, which requires the string to have multiple keywords, each seperated
 * with a comma. e.g. <br>
 * test, no, yes <br>
 * This would only be true if the solution string contained all of the keywords
 * seperated by spaces Additionally, you can add a number of keywords that have
 * to be matched with a colon: e.g. <br>
 * 2:test, no, yes <br>
 * Here only 2 or more of the keywords have to be contained in the solution
 * 
 * @author alex
 *
 */
public class KeywordSolution extends Solution<String> {
	private static final EnumTaskType type = EnumTaskType.KEYWORD;
	private Set<IgnoreCaseString> keywords;
	private int count;

	private static Pattern numRegex;

	public KeywordSolution(String task) {
		super(task.replace(" ", ""));
	}

	@Override
	public boolean isSolution(Object obj) {
		String[] words = obj.toString().split(" ");
		int ctr = 0;
		for (String s : words) {
			if (keywords.contains(new IgnoreCaseString(s))) {
				ctr++;
				if (ctr >= count) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (IgnoreCaseString s : keywords) {
			sb.append(s);
		}
		return sb.toString();
	}

	@Override
	public Task<String> parse(QMap map) throws IllegalQuestionFormatException {
		if (numRegex == null) {
			numRegex = Pattern.compile("[0-9]+:");
		}
		int i = 0;
		Matcher m = numRegex.matcher(task);

		if (m.find()) {
			if (m.start() == 0) {
				i = m.end();
				count = Integer.parseInt(task.substring(0, i-1));
			}
		}
		String[] array = task.substring(i).split(",");
		IgnoreCaseString[] icsa = new IgnoreCaseString[array.length];
		for (int j = 0; j < array.length; j++) {
			icsa[j] = new IgnoreCaseString(array[j]);
		}
		keywords = HashObjSets.newImmutableSet(icsa);
		if (count == 0)
			count = keywords.size();
		return this;
	}

	@Override
	public EnumTaskType getType() {
		return type;
	}

	@Override
	public String evaluate() {
		return toString();
	}

}
