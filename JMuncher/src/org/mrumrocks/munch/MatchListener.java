package org.mrumrocks.munch;

/**
 * A listener for attempted and successful matches.
 * 
 * @author William Chargin
 * 
 */
public interface MatchListener {

	/**
	 * Invoked when a correct match is preformed.
	 */
	public void correct();

	/**
	 * Invoked when an incorrect match is attempted.
	 */
	public void incorrect();

}
