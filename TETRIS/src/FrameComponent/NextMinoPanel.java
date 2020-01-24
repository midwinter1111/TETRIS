package FrameComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class NextMinoPanel extends JPanel {
	public static final int WIDTH = 96;
	public static final int HEIGHT = 400;

	private Mino nextMino;
	private Image minoImage;

	/**
	 *
	 */
	public NextMinoPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	/**
	 *
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// ネクストを描画
		if (nextMino != null) {
			nextMino.drawInPanel(g, minoImage);
		}
	}

	/**
	 *
	 * @param nextMino
	 * @param minoImage
	 */
	public void set(Mino nextMino, Image minoImage) {
		this.nextMino = nextMino;
		this.minoImage = minoImage;
		repaint();
	}

}
