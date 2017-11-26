package at.crimsonbit.textparser.test;

import java.io.File;
import java.util.Random;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.QuestionFileParser;
import at.crimsonbit.testparser.parser.question.Question;

public class Test {
	public static void main(String[] args) {
		QuestionFileParser qfp = new QuestionFileParser(new File("src/test/resources/questions/am/Addition.json"));
		try {
			qfp.parseQuestion();
		} catch (IllegalQuestionFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question q = qfp.getRandomQuestion(new Random().nextLong());
		System.out.println(q);
		System.out.println(q.getSolutions());
	}
}
