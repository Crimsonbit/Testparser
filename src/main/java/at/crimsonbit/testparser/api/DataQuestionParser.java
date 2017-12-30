package at.crimsonbit.testparser.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import at.crimsonbit.testparser.api.sheetinterface.IQuestionData;
import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.ParsedQuestion;

/**
 * Parses Questions from Data Interfaces like {@link IQuestionData}
 * 
 * @author Alexander Daum
 *
 */
public class DataQuestionParser extends TestInterpreter {
	/**
	 * Adds a Question to the {@link TestInterpreter}
	 * 
	 * @param data
	 * @throws IllegalQuestionFormatException
	 */
	public void addQuestion(IQuestionData data) throws IllegalQuestionFormatException {
		ParsedQuestion q;
		q = new ParsedQuestion(data);
		IgnoreCaseString subject = new IgnoreCaseString(q.getSubject());
		List<ParsedQuestion> questionsInCategories = knownQuestions.get(subject);
		if (questionsInCategories == null) {
			questionsInCategories = new ArrayList<>();
			knownQuestions.put(subject, questionsInCategories);
		}
		questionsInCategories.add(q);
		preToQuestion.put(q.getPrefix(), q);
	}

	/**
	 * Add all Questions in the Collection
	 * 
	 * @param questionData
	 * @return A List of errors thrown, because of illegal question formats
	 */
	public List<IllegalQuestionFormatException> addQuestions(Collection<? extends IQuestionData> questionData) {
		List<IllegalQuestionFormatException> errors = new LinkedList<>();
		for (IQuestionData data : questionData) {
			try {
				addQuestion(data);
			} catch (IllegalQuestionFormatException e) {
				errors.add(e);
			}
		}
		return errors;
	}

}
