package at.crimsonbit.textparser.test;

import java.util.Scanner;

import at.crimsonbit.testparser.api.APIQuestion;
import at.crimsonbit.testparser.api.TestParser;
import at.crimsonbit.testparser.api.UniqueID;

public class Test {
	public static void main(String[] args) {
		TestParser parser = new TestParser();
		parser.readQuestions("src/test/resources/questions");
		APIQuestion q = parser.getRandomQuestion("AM", 2).getResponse();
		UniqueID uid = q.getUID();
		System.out.println("Question: " + uid);
		System.out.println(q.getQ());

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
	}
}
