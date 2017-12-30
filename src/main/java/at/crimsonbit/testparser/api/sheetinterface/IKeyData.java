package at.crimsonbit.testparser.api.sheetinterface;

public interface IKeyData {
	String getType();

	double getMin();

	double getMax();

	String getExpr();

	String[] getValues();

	int getDigits();
}
