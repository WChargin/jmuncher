package org.mrumrocks.munch;

/**
 * Tests if a given value can be munched.
 * 
 * @author William Chargin
 * 
 */
public interface MunchMatcher {

	/**
	 * Tests if this value can be munched by the muncher.
	 * 
	 * @param x
	 *            the value to test
	 * @return {@code true} if the muncher can munch this value, or
	 *         {@code false} if it cannot
	 */
	public boolean matches(int x);

}
