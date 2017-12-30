package at.crimsonbit.testparser.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.QuestionFileParser;
import at.crimsonbit.testparser.parser.question.ParsedQuestion;

/**
 * API Interface for parsing questions from JSON files of the TestParser Library
 * Can create random questions for given subject and difficulty, as well as
 * replicate questions when the UID is known
 * 
 * @author Alexander Daum
 *
 */
public class JsonTestParser extends TestInterpreter {

	public JsonTestParser() {
		super();
	}

	/**
	 * Recursively read all Questions from the Folder parentFolder and its
	 * sub-folders
	 * 
	 * @param parentFolder
	 *            The parent Folder
	 * @return The amount of questions, that could not be parsed correctly
	 */
	public int readQuestions(File parentFolder) {
		return addQuestionsToMap(parentFolder);
	}

	private int addQuestionsToMap(File folder) {
		int returnCode = 0;
		for (File f : folder.listFiles()) {
			if (f.isDirectory())
				addQuestionsToMap(f);
			else if (f.getName().endsWith(".json")) {
				QuestionFileParser qfp = new QuestionFileParser(f);
				ParsedQuestion question = null;
				try {
					question = qfp.parseQuestion();
					IgnoreCaseString subject = new IgnoreCaseString(question.getSubject());
					List<ParsedQuestion> questionsInCategory = knownQuestions.get(subject);
					if (questionsInCategory == null) {
						questionsInCategory = new ArrayList<>();
						knownQuestions.put(subject, questionsInCategory);
					}
					questionsInCategory.add(question);
					preToQuestion.put(question.getPrefix(), question);
				} catch (IllegalQuestionFormatException e) {
					e.printStackTrace();
					returnCode++;
				}
			}
		}
		return returnCode;
	}

	/**
	 * Reads questions from the folder specified by the string
	 * 
	 * @param string
	 *            The path to the folder, either absolute or relative
	 */
	public int readQuestions(String string) {
		return readQuestions(new File(string));
	}
}
