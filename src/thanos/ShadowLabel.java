package thanos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

/* http://www.java2s.com/Code/Java/Swing-Components/ShadowLabel.htm */
/* Slightly modified for use! */
/* This class is used to add drop shadows to the "snap warning" to make it
 * easier to read. Nothing more to look at here. */
public class ShadowLabel extends JLabel {

	private String text;
	private Font f;
	private boolean invertColors = false;

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		// ////////////////////////////////////////////////////////////////
		// antialiasing
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// ////////////////////////////////////////////////////////////////

		/**
		 * draw text
		 */
		g2D.setFont(f);
		g2D.setColor(new Color(0, 0, 0));
		g2D.drawString(this.text, 1, 23);
		g2D.setColor(new Color(255, 255, 255, 230));
		g2D.drawString(this.text, 0, 22);

		g2D.dispose();

	}

	public void invertColors() {
		invertColors = !invertColors;
	}

	@Override
	public void setText(String text) {
		super.setText(text);
		this.text = text;
		repaint();
	}

	@Override
	public void setFont(Font f) {
		super.setFont(f);
		this.f = f;
		repaint();
	}

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;
}