package at.crimsonbit.testparser.api;

import java.util.Locale;

/**
 * A Class intended for use in hashMap keys. It acts as a string, but if two
 * IgnoreCaseStrings are compared, they are equal according to
 * {@link String#equalsIgnoreCase(String)} and the hashCode gets computet using
 * {@link String#toUpperCase()}
 * 
 * @author Alexander Daum
 *
 */
public class IgnoreCaseString {
	private final String s;

	public IgnoreCaseString(String s) {
		super();
		this.s = s;
	}

	public String getS() {
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.toUpperCase(Locale.ROOT).hashCode());
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
		IgnoreCaseString other = (IgnoreCaseString) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equalsIgnoreCase(other.s))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return s;
	}

}
