package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.parser.question.ParameterType;

public interface IKey<T> {
	/**
	 * Returns a random value for this key based on the Random given
	 * 
	 * @param random
	 * @return
	 */
	T get(Random random);

	/**
	 * Returns the Type of this Key
	 * 
	 * @return
	 */
	ParameterType getType();
}
