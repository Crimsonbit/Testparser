package at.crimsonbit.testparser.api;

import java.io.File;
import java.util.Random;

import at.crimsonbit.testparser.parser.QuestionFileParser;
import at.crimsonbit.testparser.parser.question.Question;

public class TestParser {
	private QuestionFileParser fileParser;
	private Random seedGenerator;

	public TestParser(File f) {
		fileParser = new QuestionFileParser(f);
		seedGenerator = new Random();
	}
	
	public APIQuestion getRandomQuestion() {
		//TODO
		long seed = seedGenerator.nextLong();
		Question q = fileParser.getRandomQuestion(seed);
		String prefix = fileParser.getPrefix();
		APIQuestion questionData = new APIQuestion(q, seed, prefix);
		return null;
	}
}
