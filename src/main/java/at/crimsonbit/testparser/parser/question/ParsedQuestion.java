package at.crimsonbit.testparser.parser.question;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.api.sheetinterface.IQuestionData;
import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.exceptions.RuntimeQuestionException;
import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public class ParsedQuestion extends AbstractQuestion {

	public ParsedQuestion(IQuestionData data) throws IllegalQuestionFormatException {
		List<? extends IKeyData> dataKeys = data.getKeys();
		keys = dataKeys.stream().map(this::convertDTOKeyToParsedKey).toArray(IKey<?>[]::new);

		prefix = (data.getName() + data.getSubject()).hashCode();
		try {
			tasks = Task.createTasks(data.getTasks().stream());
			solve = Solution.createSolutions(data.getSolutions().stream());
		} catch (RuntimeQuestionException e) {
			throw new IllegalQuestionFormatException(
					String.format("Illegal Question format in question %s", data.getName()), e);
		}
		help = data.getHints();
		difficulty = data.getDifficulty();
		subject = data.getSubject();
		name = data.getName();

	}

	private IKey<?> convertDTOKeyToParsedKey(IKeyData t) {
		ParameterType type = ParameterType.valueOf(t.getType().toUpperCase(Locale.ROOT));
		return type.create(t);
	}

	public Question getRandomQuestion(long seed) throws IllegalQuestionFormatException {
		return new Question(this, new Random(seed));
	}

}
