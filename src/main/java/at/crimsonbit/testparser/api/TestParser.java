package at.crimsonbit.testparser.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;
import com.koloboke.collect.map.hash.HashObjObjMaps;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.QuestionFileParser;
import at.crimsonbit.testparser.parser.question.ParsedQuestion;
import at.crimsonbit.testparser.parser.question.Question;

/**
 * API Interface for parsing questions from JSON files of the TestParser Library
 * Can create random questions for given subject and difficulty, as well as
 * replicate questions when the UID is known
 * 
 * @author Alexander Daum
 *
 */
public class TestParser {
	private Random seedGenerator;

	private Map<IgnoreCaseString, List<ParsedQuestion>> knownQuestions;
	private HashIntObjMap<ParsedQuestion> preToQuestion;

	public TestParser() {
		knownQuestions = HashObjObjMaps.<IgnoreCaseString, List<ParsedQuestion>>newMutableMap();
		preToQuestion = HashIntObjMaps.<ParsedQuestion>newMutableMap();
		seedGenerator = new Random();
	}

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

	public APIResponse<APIQuestion> getRandomQuestion(String category, int difficulty) {
		List<ParsedQuestion> questions = knownQuestions.get(new IgnoreCaseString(category));
		ParsedQuestion question;
		if (questions.size() == 0) {
			return new APIResponse<APIQuestion>(null,
					"No Questions in category " + category + " with difficulty " + difficulty);
		}
		do {
			question = questions.get(seedGenerator.nextInt(questions.size()));
		} while (question == null || question.getDifficulty() != difficulty);

		long seed = seedGenerator.nextLong();
		Question q;
		try {
			q = question.getRandomQuestion(seed);
		} catch (IllegalQuestionFormatException e) {
			return new APIResponse<APIQuestion>(null, "Error creating question: " + e.getMessage());
		}
		int prefix = question.getPrefix();
		APIQuestion questionData = new APIQuestion(q, seed, prefix);
		return new APIResponse<APIQuestion>(questionData, APIResponse.SUCCESS_MESSAGE);
	}

	/**
	 * Replicates a question when an id is known, for more info look at
	 * {@link TestParser#replicateQuestion(UniqueID)}
	 * 
	 * @param id
	 *            the ID of the question as String
	 * @return a question the same as the original one
	 * @see TestParser#replicateQuestion(UniqueID)
	 */
	public APIResponse<APIQuestion> replicateQuestion(String id) {
		String pre, seed;
		pre = id.substring(0, 8);
		seed = id.substring(8);
		return replicateQuestion(new UniqueID(Integer.parseUnsignedInt(pre, 16), Long.parseUnsignedLong(seed, 16)));
	}

	/**
	 * Replicates a question with a given unique identifier. The UID exists of two
	 * parts, the first 8 characters identify the class of the question, the prefix,
	 * the 16 characters after that specify the values, that the question has, are
	 * called seed. First the prefix is used to determine the creator object from
	 * the {@link TestParser#preToQuestion} Map. Then the seed is used to create the
	 * same random results again
	 * 
	 * @param id
	 *            The ID of the question
	 * @return a question the same as the original
	 */
	public APIResponse<APIQuestion> replicateQuestion(UniqueID id) {
		ParsedQuestion pq = preToQuestion.get(id.getPre());
		if (pq == null) {
			return new APIResponse<APIQuestion>(null,
					"Error, no APIQuestion with prefix " + Integer.toHexString(id.getPre()));
		}
		Question q = null;
		try {
			q = pq.getRandomQuestion(id.getSeed());
		} catch (IllegalQuestionFormatException e) {
			return new APIResponse<APIQuestion>(null, "Error creating question: " + e.getMessage());
		}
		return new APIResponse<APIQuestion>(new APIQuestion(q, id.getSeed(), id.getPre()), APIResponse.SUCCESS_MESSAGE);
	}

	/**
	 * Reads questions from the folder specified by the string
	 * 
	 * @param string
	 *            The path to the folder, either absolute or relative
	 */
	public void readQuestions(String string) {
		readQuestions(new File(string));
	}

	/**
	 * Returns all subjects, where questions are available
	 * 
	 * @return
	 */
	public String[] getKnownSubjects() {
		return knownQuestions.keySet().stream().map(IgnoreCaseString::getS).toArray(String[]::new);
	}

	/**
	 * Returns the names of all questions in the subject, or null if there are none
	 * 
	 * @param subject
	 *            The Subject name CASE SENSITIVE
	 * @return
	 */
	public String[] getQuestionNames(IgnoreCaseString subject) {
		List<ParsedQuestion> list = knownQuestions.get(subject);
		if (list == null) {
			return null;
		}
		return list.stream().map(p -> p.getName()).toArray(String[]::new);
	}

	/**
	 * Returns the names of all questions in the subject, or null if there are none
	 * 
	 * @param subject
	 *            The Subject name CASE SENSITIVE
	 * @return
	 */
	public String[] getQuestionNames(String subject) {
		return getQuestionNames(new IgnoreCaseString(subject));
	}
}
