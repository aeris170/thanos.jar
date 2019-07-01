package thanos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/* This class is used to draw a big thanos image to the background of the
 * window. Nothing more to look at here. */
public class CustomPanel extends JPanel {

	private static final long serialVersionUID = 8037082827058855295L;

	private BufferedImage image;
	private int w, h;

	public CustomPanel() {
		try {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			image = ImageIO.read(Main.class.getResourceAsStream("/bg.jpg"));
			w = image.getWidth() / 2;
			h = image.getHeight() / 2;
			setMinimumSize(new Dimension(w, h));
			setPreferredSize(new Dimension(w, h));
			setMaximumSize(new Dimension(w, h));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, w, h, null);
	}
}
