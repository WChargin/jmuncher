package org.mrumrocks.munch;

import java.awt.Color;
import java.awt.Graphics2D;

import jgame.GMessage;
import jgame.GObject;

/**
 * A block that can be "munched" by the muncher.
 * 
 * @author William Chargin
 * 
 */
public class MunchBlock extends GObject {

	/**
	 * The value of this block.
	 */
	private int value;

	/**
	 * The text indicator for this block.
	 */
	private GMessage msg = new GMessage();

	/**
	 * Creates the munch block with the given value.
	 * 
	 * @param value
	 *            the value for this block
	 */
	public MunchBlock(int value) {
		super();
		setSize(50, 50);
		setValue(value);

		msg.setAlignmentX(0.5);
		msg.setAlignmentY(0.5);
		msg.setSize(getWidth(), getHeight());
		addAtCenter(msg);
	}

	/**
	 * Gets the value of this block.
	 * 
	 * @return the value of this block
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of this block.
	 * 
	 * @param value
	 *            the value of this block
	 */
	public void setValue(int value) {
		this.value = value;
		msg.setText(Integer.toString(value));
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getIntWidth(), getIntHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getIntWidth() - 1, getIntHeight() - 1);
		super.paint(g); // paint children
	}

}
