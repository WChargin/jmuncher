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
	 * Creates the board.
	 */
	public MuncherBoard() {
		initializeTiles();
		setSize(362, 362);
		addAtCenter(new Muncher(Color.BLUE.darker(), 40));
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

}
