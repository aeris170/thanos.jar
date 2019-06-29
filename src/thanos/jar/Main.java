package thanos.jar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public final class Main {

	private static JFrame f;

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font customFont;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/fonts/AVENGEANCE HEROIC AVENGER.otf"));
			ge.registerFont(customFont);
		} catch (FontFormatException | IOException ex1) {
			ex1.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {
			try {
				createAndShowGUI();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}

	private static final void createAndShowGUI() throws IOException {
		// create window
		f = new JFrame();

		// create wrapper
		JPanel p = new CustomPanel();

		// create buttons
		JButton snap = new JButton("SNAP FINGERS");
		JButton snapWithGauntlet = new JButton("SNAP FINGERS WITH INFINITY GAUNTLET");
		snapWithGauntlet.setVisible(false);

		// create snap warning, someone please add font-shadow to this and initiate a
		// pull request
		JLabel l = new JLabel();
		l.setForeground(Color.WHITE);
		l.setFont(new Font("AVENGEANCE HEROIC AVENGER", Font.PLAIN, 25));

		// define button behaviors
		snap.addActionListener(e -> {
			l.setText("no point snapping without the infinity gauntlet");
			snapWithGauntlet.setVisible(true);
		});
		snapWithGauntlet.addActionListener(e -> {

		});

		// add buttons to wrapper, position them and resize them
		snap.setAlignmentX(Component.CENTER_ALIGNMENT);
		snapWithGauntlet.setAlignmentX(Component.CENTER_ALIGNMENT);
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		for (int i = 0; i < 100; i++) {
			p.add(Box.createVerticalGlue());
		}
		p.add(snap);
		p.add(Box.createVerticalGlue());
		p.add(snapWithGauntlet);
		p.add(Box.createVerticalGlue());
		p.add(l);
		for (int i = 0; i < 50; i++) {
			p.add(Box.createVerticalGlue());
		}
		snap.setMinimumSize(snapWithGauntlet.getMinimumSize());
		snap.setPreferredSize(snapWithGauntlet.getPreferredSize());
		snap.setMaximumSize(snapWithGauntlet.getMaximumSize());

		// add wrapper to window;
		f.add(p, BorderLayout.CENTER);

		// configure window properties and show window
		f.setTitle("thanos.jar");
		f.setResizable(false);
		f.setLocationByPlatform(true);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}