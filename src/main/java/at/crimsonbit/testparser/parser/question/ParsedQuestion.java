package at.crimsonbit.testparser.parser.question;

import java.util.Arrays;
import java.util.Random;

import at.crimsonbit.testparser.parser.dto.KeyDTO;
import at.crimsonbit.testparser.parser.dto.QuestionDTO;
import at.crimsonbit.testparser.parser.question.mapping.ExprKey;
import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.mapping.NumKey;
import at.crimsonbit.testparser.parser.question.mapping.StringKey;

public class ParsedQuestion extends AbstractQuestion {

	public ParsedQuestion(QuestionDTO data) {
		KeyDTO[] dataKeys = data.getKeys();
		keys = Arrays.stream(dataKeys).map(this::convertDTOKeyToParsedKey).toArray(IKey<?>[]::new);

		prefix = (data.getName() + data.getSubject()).hashCode();
		tasks = Task.createTasks(data.getTask());
		solve = Task.createTasks(data.getSolution());
		help = data.getHints();

	}

	private IKey<?> convertDTOKeyToParsedKey(KeyDTO t) {
		switch (t.getType().toUpperCase()) {
		case "NUMBER":
			return new NumKey(t.getMinimum(), t.getMaximum());
		case "STRING":
			return new StringKey(t.getValues());
		case "EXPRESSION":
			return new ExprKey(t.getValue());
		default:
			return null;
		}
	}

	public Question getRandomQuestion(long seed) {
		return new Question(this, new Random(seed));
	}

}
