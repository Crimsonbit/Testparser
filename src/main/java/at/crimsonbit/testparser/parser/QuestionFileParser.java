package at.crimsonbit.testparser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.dto.QuestionDTO;
import at.crimsonbit.testparser.parser.question.ParsedQuestion;
import at.crimsonbit.testparser.parser.question.Question;

public class QuestionFileParser {
	private ParsedQuestion parsedQuestion = null;
	private File file;

	/**
	 * Create a new Question file Parser, you should call
	 * {@link QuestionFileParser#parseQuestion()} next
	 * 
	 * @param f
	 */
	public QuestionFileParser(File f) {
		file = f;
	}

	/**
	 * Parse the question in the File, has to be called before any other function
	 * 
	 * @return
	 */
	public ParsedQuestion parseQuestion() throws IllegalQuestionFormatException {
		Gson gson = new Gson();
		QuestionDTO qdto;
		try {
			qdto = gson.fromJson(new FileReader(file), QuestionDTO.class);

		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			throw new IllegalQuestionFormatException(e);
		}

		parsedQuestion = new ParsedQuestion(qdto);

		return parsedQuestion;
	}

	/**
	 * Create the parsed Question with random fields, according to the Question file
	 * 
	 * @param seed
	 *            The seed for the random generator for the question
	 * @return a "random" question
	 * @throws IllegalQuestionFormatException
	 */
	public Question getRandomQuestion(long seed) throws IllegalQuestionFormatException {
		if (parsedQuestion == null)
			return null;
		return parsedQuestion.getRandomQuestion(seed);
	}

	/**
	 * Returns the prefix, which is unique for the question type
	 * 
	 * @return
	 */
	public String getPrefix() {
		return Integer.toHexString(parsedQuestion.getPrefix());
	}
}
