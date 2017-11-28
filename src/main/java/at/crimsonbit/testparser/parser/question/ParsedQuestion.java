package at.crimsonbit.testparser.parser.question;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import at.crimsonbit.testparser.parser.dto.KeyDTO;
import at.crimsonbit.testparser.parser.dto.QuestionDTO;
import at.crimsonbit.testparser.parser.question.mapping.IKey;

public class ParsedQuestion extends AbstractQuestion {

	public ParsedQuestion(QuestionDTO data) {
		KeyDTO[] dataKeys = data.getKeys();
		keys = Arrays.stream(dataKeys).map(this::convertDTOKeyToParsedKey).toArray(IKey<?>[]::new);

		prefix = (data.getName() + data.getSubject()).hashCode();
		tasks = Task.createTasks(data.getTask());
		solve = Task.createTasks(data.getSolution());
		help = data.getHints();
		difficulty = data.getDifficulty();
		subject = data.getSubject();

	}

	private IKey<?> convertDTOKeyToParsedKey(KeyDTO t) {
		ParameterType type = ParameterType.valueOf(t.getType().toUpperCase(Locale.ROOT));
		return type.create(t);
	}

	public Question getRandomQuestion(long seed) {
		return new Question(this, new Random(seed));
	}

}
