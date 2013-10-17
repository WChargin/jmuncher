package org.mrumrocks.munch;

import java.awt.Color;

import jgame.GContainer;

/**
 * Creates the game board for the muncher.
 * 
 * @author William Chargin
 * 
 */
public class MuncherBoard extends GContainer {

	/**
	 * The matcher used for this board.
	 */
	private MunchMatcher matcher;

	/**
	 * The match listener used for this board.
	 */
	private MatchListener listener;

	/**
	 * Creates the board.
	 */
	public MuncherBoard(MatchListener listener) {
		this.listener = listener;
		initializeTiles();
		setSize(362, 362);
		addAtCenter(new Muncher(Color.BLUE.darker(), 40));
	}

	/**
	 * Gets the matcher used for this board.
	 * 
	 * @return the matcher
	 */
	public MunchMatcher getMatcher() {
		return matcher;
	}

	/**
	 * Initializes the tiles for the board.
	 */
	private void initializeTiles() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				MunchBlock block = new MunchBlock(
						(int) (Math.random() * 99) + 1);
				double pad = 0.1;
				addAt(block,
						block.getWidth() * ((1 + 2 * pad) * i + 0.5 + pad),
						block.getHeight() * ((1 + 2 * pad) * j + 0.5 + pad));
			}
		}
	}

	/**
	 * Sets the matcher used for this board.
	 * 
	 * @param matcher
	 *            the new matcher
	 */
	public void setMatcher(MunchMatcher matcher) {
		this.matcher = matcher;
	}

}
