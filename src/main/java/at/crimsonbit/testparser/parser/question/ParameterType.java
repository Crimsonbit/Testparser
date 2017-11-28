package at.crimsonbit.testparser.parser.question;

import java.util.function.Function;

import at.crimsonbit.testparser.parser.dto.KeyDTO;
import at.crimsonbit.testparser.parser.question.mapping.DoubleKey;
import at.crimsonbit.testparser.parser.question.mapping.ExprKey;
import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.mapping.IntKey;
import at.crimsonbit.testparser.parser.question.mapping.StringKey;

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
	EXPRESSION(ExprKey::create);

	private Function<KeyDTO, IKey<?>> creator;

	ParameterType(Function<KeyDTO, IKey<?>> func) {
		creator = func;
	}

	public IKey<?> create(KeyDTO data) {
		return creator.apply(data);
	}
}
