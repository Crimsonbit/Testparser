package at.crimsonbit.testparser.api.sheetinterface;

import at.crimsonbit.testparser.parser.question.EnumTaskType;

public interface ISolutionData {
	EnumTaskType getType();

	String getText();
}
