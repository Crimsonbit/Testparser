package at.crimsonbit.testparser;

import java.util.Scanner;

import at.crimsonbit.testparser.api.APIQuestion;
import at.crimsonbit.testparser.api.APIResponse;
import at.crimsonbit.testparser.api.JsonTestParser;
import at.crimsonbit.testparser.api.UniqueID;

public class Test {
	public static void main(String[] args) {
		JsonTestParser parser = new JsonTestParser();
		parser.readQuestions("src/test/resources/questions");
		APIResponse<APIQuestion> r = parser.getRandomQuestion("MCh", 2);
		APIQuestion q = r.getResponse();
		if (q == null) {
			System.out.println(r.getMessage());
			System.exit(1);
		}
		UniqueID uid = q.getUID();
		System.out.println("Question: " + uid);
		System.out.println(q.getQ());
		System.out.println(q.getAnswer());

		Scanner scanner = new Scanner(System.in);
		Object result;
		boolean isSolution = false;
		do {
			String reply = scanner.nextLine();
			try {
				if (reply.contains(".") || reply.contains("e")) {
					result = Double.parseDouble(reply);
				} else {
					result = Integer.parseInt(reply);
				}
			} catch (NumberFormatException e) {
				result = reply;
			}

			q = parser.replicateQuestion(uid).getResponse();
			isSolution = q.getQ().isSolution(result, 0);
			if (!isSolution) {
				System.out.println("Wrong, try again");
			}
		} while (!isSolution);
		scanner.close();
		System.out.println("Correct");
		System.out.println(q.getQ().getSolutionsAsString());
	}
}
