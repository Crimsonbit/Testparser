package at.crimsonbit.testparser.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


class TestTestParser {

	static JsonTestParser parser;

	@BeforeAll
	static void initParser() {
		parser = new JsonTestParser();
		assertEquals(0, parser.readQuestions("src/test/resources/questions"));
	}

	@RepeatedTest(50)
	void recreateCorrectQuestion() {
		APIResponse<APIQuestion> question = parser.getRandomQuestion();
		if (question.getResponse() == null) {
			System.err.println("Error: " + question.getMessage());
			throw new RuntimeException("Error, question == null");
		}

		APIQuestion q = question.getResponse();
		UniqueID uid = q.getUID();

		APIResponse<APIQuestion> replicatedQuestion = parser
				.replicateQuestion(new UniqueID(uid.getPre(), System.currentTimeMillis()));
		if (question.getResponse() == null) {
			System.err.println("Error: " + question.getMessage());
			throw new RuntimeException("Error, question == null");
		}

		APIQuestion rq = replicatedQuestion.getResponse();
		assertEquals(rq.getQ().getName(), q.getQ().getName());
	}

	@Test
	void testMultiAnswer() {
		Random random = new Random();
		long l = System.currentTimeMillis();
		random.setSeed(l);
		APIResponse<APIQuestion> r = parser.replicateQuestion(new UniqueID(0x74a86387, l));
		APIQuestion q = r.getResponse();
		if (q == null) {
			System.out.println(r.getMessage());
			System.exit(1);
		}

		String[] answers = new String[q.getQ().numSolutions()];
		for (int i = 0; i < q.getQ().numSolutions(); i++) {
			String solution = q.getQ().getSolution(i);
			answers[i] = solution;
		}
		boolean[] expected = new boolean[q.getQ().numSolutions()];
		Arrays.fill(expected, true);
		assertArrayEquals(expected, q.areSolutions(answers));

	}

	@Test
	void testSubjectList() throws Exception {
		JsonTestParser reducedParser = new JsonTestParser();
		reducedParser.readQuestions("src/test/resources/questions/test_questions");
		String[] subj = reducedParser.getKnownSubjects();
		assertArrayEquals(new String[] { "Test" }, subj);
		String[] questions = reducedParser.getQuestionNames(subj[0]);
		assertNotNull(questions);
	}

}
