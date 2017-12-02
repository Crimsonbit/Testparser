package at.crimsonbit.testparser.api;

import java.io.Serializable;

/**
 * A Class which can uniquely identify any question, using an int and a long
 * 
 * @author Alexander Daum
 *
 */
public class UniqueID implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7007810134943465474L;
	private final int pre;
	private final long seed;
	private transient String cachePrefixString;

	public UniqueID(int pre, long seed) {
		super();
		this.pre = pre;
		this.seed = seed;
	}

	public int getPre() {
		return pre;
	}

	public long getSeed() {
		return seed;
	}

	@Override
	public String toString() {
		if (cachePrefixString == null) {
			cachePrefixString = String.format("%08x%016x", pre, seed);
		}
		return cachePrefixString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pre;
		result = prime * result + (int) (seed ^ (seed >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniqueID other = (UniqueID) obj;
		if (pre != other.pre)
			return false;
		if (seed != other.seed)
			return false;
		return true;
	}

}
