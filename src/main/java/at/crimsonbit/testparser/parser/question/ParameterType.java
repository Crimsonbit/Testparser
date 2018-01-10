package at.crimsonbit.testparser.parser.question;

import java.util.function.Function;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.mapping.DoubleKey;
import at.crimsonbit.testparser.parser.question.mapping.ExprKey;
import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.mapping.IntKey;
import at.crimsonbit.testparser.parser.question.mapping.ShuffleKey;
import at.crimsonbit.testparser.parser.question.mapping.StringKey;
import at.crimsonbit.testparser.parser.question.mapping.MChoice;

/**
 * A List of all Parameters, that the keys of Testparser Questions can have
 * 
 * @author Alexander Daum
 *
 */
public enum ParameterType {
	DOUBLE(DoubleKey::create),
	INT(IntKey::create),
	STRING(StringKey::create),
	EXPRESSION(ExprKey::create),
	SHUFFLE(ShuffleKey::create),
	MCHOICE(MChoice::create);

	private Function<IKeyData, IKey<?>> creator;

	ParameterType(Function<IKeyData, IKey<?>> func) {
		creator = func;
	}

	public IKey<?> create(IKeyData data) {
		return creator.apply(data);
	}
}
