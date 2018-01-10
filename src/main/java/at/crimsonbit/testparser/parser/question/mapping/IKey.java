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
	T get();
	
	/**
	 * Returns a Key with solution
	 * @param random
	 * @return
	 */
	IKey<T> parse(Random random);
	
	default T getForSol() {
		return get();
	}

	/**
	 * Returns the Type of this Key
	 * 
	 * @return
	 */
	ParameterType getType();
}
