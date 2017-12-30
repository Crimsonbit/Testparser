package at.crimsonbit.testparser.api.sheetinterface;

import java.util.List;

public interface IQuestionData {
	int getDifficulty();

	String getName();

	String getSubject();

	List<String> getTasks();

	List<String> getHints();

	List<? extends ISolutionData> getSolutions();

	List<? extends IKeyData> getKeys();
}
