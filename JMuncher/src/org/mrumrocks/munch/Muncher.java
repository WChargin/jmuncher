package org.mrumrocks.munch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import jgame.Context;
import jgame.GObject;
import jgame.GSprite;
import jgame.controller.MouseRotationController;
import jgame.listener.GlobalKeyListener;

/**
 * The main character of the muncher game.
 * 
 * @author William Chargin
 * 
 */
public class Muncher extends GSprite {

	/**
	 * Creates a muncher of the given color.
	 * 
	 * @param color
	 *            the color for this muncher
	 */
	public Muncher(Color color, int size) {
		super(createMuncherImages(color, size, size));
		setSize(size, size);
		addController(new MouseRotationController());

		addListener(new GlobalKeyListener(KeyEvent.VK_SPACE) {
			@Override
			public void invoke(GObject target, Context context) {
				List<MunchBlock> blocksHit = context
						.hitTestClass(MunchBlock.class);
				for (MunchBlock block : blocksHit) {
					doMunch(block);
				}
			}
		});
	}

	/**
	 * Munches the specified block.
	 * 
	 * @param block
	 *            the block to munch
	 */
	private void doMunch(MunchBlock block) {
		MuncherBoard board = getFirstAncestorOf(MuncherBoard.class);
		board.attemptMunch(block);
	}

	/**
	 * Creates the muncher animation for a given color.
	 * 
	 * @param color
	 *            the color of the muncher to create
	 * @param width
	 *            the width of the muncher to create
	 * @param height
	 *            the height of the muncher to create
	 * @return the muncher animation
	 */
	private static List<Image> createMuncherImages(Color color, int width,
			int height) {
		List<Image> images = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			BufferedImage munch = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) munch.getGraphics();
			antialias(g2d);

			final double theta = 45d / 30 * i;
			Arc2D arc = new Arc2D.Double(0, 0, width - 1, height - 1, theta,
					360 - 2 * theta, Arc2D.PIE);
			g2d.setPaint(new RadialGradientPaint(width / 2f, height,
					1.5f * Math.max(width, height), new float[] { 0, 1 },
					new Color[] { color, Color.WHITE }));
			g2d.fill(arc);

			g2d.setColor(Color.BLACK);
			final float stroke = 2;
			g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));
			Arc2D arcStroke = new Arc2D.Double(stroke / 2, stroke / 2, width
					- 1 - stroke, height - 1 - stroke, arc.getAngleStart(),
					arc.getAngleExtent(), arc.getArcType());
			g2d.draw(arcStroke);

			Point2D eyeCenter = new Point2D.Double(width * 0.6, height * 0.25);

			double eyeRadius = ((width + height) / 2d) / 8;
			Ellipse2D eye = new Ellipse2D.Double(eyeCenter.getX() - eyeRadius,
					eyeCenter.getY() - eyeRadius, 2 * eyeRadius, 2 * eyeRadius);
			g2d.setColor(Color.WHITE);
			g2d.fill(eye);

			double pupilRadius = eyeRadius * 3 / 5;
			Ellipse2D pupil = new Ellipse2D.Double(eyeCenter.getX()
					- pupilRadius, eyeCenter.getY() - pupilRadius,
					2 * pupilRadius, 2 * pupilRadius);
			g2d.setColor(Color.BLACK);
			g2d.fill(pupil);

			g2d.dispose();
			images.add(munch);
		}
		// add the reverse
		for (int i = images.size() - 1; i >= 0; i--) {
			images.add(images.get(i));
		}
		return images;
	}
}
