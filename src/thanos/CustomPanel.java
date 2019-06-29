package thanos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	private static final long serialVersionUID = 8037082827058855295L;

	private BufferedImage image;
	private int w, h;

	public CustomPanel() throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		image = ImageIO.read(Main.class.getResourceAsStream("/bg.jpg"));
		w = image.getWidth() / 2;
		h = image.getHeight() / 2;
		setMinimumSize(new Dimension(w, h));
		setPreferredSize(new Dimension(w, h));
		setMaximumSize(new Dimension(w, h));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, w, h, null);
	}
}
