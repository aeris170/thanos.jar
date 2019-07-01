package thanos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import jline.TerminalFactory;

public final class Main {

	private static String jarDir;
	private static String jarName;
	private static List<File> allFiles;
	private static int commandLineWidth;

	public static void main(String[] args) {
		try {
			// OS L&F.
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			// AVENGERS Font.
			GraphicsEnvironment.getLocalGraphicsEnvironment()
			        .registerFont(Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/fonts/AVENGEANCE HEROIC AVENGER.otf")));

			// Get command line width. This is here purely for cosmetic reasons.
			commandLineWidth = TerminalFactory.get().getWidth();
			if (commandLineWidth < 100) {
				commandLineWidth = 100;
			}

			// Get Jar name and directory.
			jarDir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			jarName = jarDir.substring(jarDir.lastIndexOf("\\") + 1);
			jarDir = jarDir.substring(0, jarDir.lastIndexOf("\\"));

			// Flavor text.
			for (int i = 0; i < commandLineWidth; i++) {
				System.out.print("½");
			}
			System.out.println();
			System.out.println("-Tell me his name again.");
			System.out.println("-Thanos.");

			// Run GUI on EDT.
			SwingUtilities.invokeLater(() -> createAndShowGUI());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | FontFormatException | IOException
		        | URISyntaxException ex) {
			ex.printStackTrace();
		}
	}

	private static final void createAndShowGUI() {
		// Create window.
		JFrame f = new JFrame();

		// Create wrapper.
		JPanel p = new CustomPanel();

		// Create buttons, the first button is useless. The second button
		// is what does the job.
		JButton snap = new JButton("SNAP FINGERS");
		JButton snapWithGauntlet = new JButton("SNAP FINGERS WITH INFINITY GAUNTLET");
		snapWithGauntlet.setVisible(false);

		// Create snap warning.
		JLabel l = new ShadowLabel();
		l.setForeground(Color.WHITE);
		l.setFont(new Font("AVENGEANCE HEROIC AVENGER", Font.PLAIN, 25));

		// Define button behaviours. When the first button is pressed
		// it shows the snap warning alongside with the second button. When the second
		// button is pressed, everything becomes perfectly balanced.
		snap.addActionListener(e -> {
			l.setText("  no point snapping without the infinity gauntlet  ");
			snapWithGauntlet.setVisible(true);
		});
		snapWithGauntlet.addActionListener(e -> {
			// THIS IS WHERE THE MAGIC HAPPENS!!!
			// Get all the files.
			allFiles = listf(jarDir);
			System.out.println();
			System.out.println("Files in directory: (Total Count: " + allFiles.size() + ")");
			allFiles.forEach(file -> System.out.println(file.getName()));

			// Assign a probability to each file.
			Map<File, Double> probabilities = new HashMap<>();
			allFiles.forEach(file -> probabilities.put(file, Math.random()));
			Map<File, Double> sortedProbabilities = new HashMap<>();

			// Sort ascending/descending by chance.
			if (Math.random() < 0.5d) {
				sortedProbabilities = ascendingSortByValue(probabilities);
			} else {
				sortedProbabilities = descendingSortByValue(probabilities);
			}

			// Get files sorted by probabilities.
			allFiles = new ArrayList<>(sortedProbabilities.keySet());

			// Exactly half of the files.
			int numberOfFilesToBeDeleted = allFiles.size() / 2;

			for (int i = 0; i < numberOfFilesToBeDeleted; i++) {
				// Check if Thanos is inside the crowd.
				if (allFiles.get(i).getName().equals(jarName)) {
					// Thanos is picked, this means Tony happened.
					try {
						System.out.println();
						System.out.println("-I am inevitable.");
						System.out.println();
						System.out.println("-I... am... Iron Man!");
						for (int j = 0; j < commandLineWidth; j++) {
							System.out.print("½");
						}
						System.out.println();

						// Delete thanos.jar, this bit of code is needed because thanos.jar cannot be
						// directly deleted since it is open.
						Runtime.getRuntime().exec("cmd /c ping localhost -n 2 > nul && del " + jarName);
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						System.exit(0);
					}
				}
			}

			// If Thanos is not picked, delete half of the files.
			System.out.println("\nFiles deleted: (Total Count: " + numberOfFilesToBeDeleted + ")");
			for (int i = 0; i < numberOfFilesToBeDeleted; i++) {
				File fileToBeDeleted = allFiles.get(i);
				System.out.println(fileToBeDeleted.getName());
				fileToBeDeleted.delete();
			}

			System.out.println();
			System.out.println("-Daughter?");
			System.out.println("-Did you do it?");
			System.out.println("-Yes.");
			System.out.println("-What did it cost?");
			System.out.println("Everything...");
		});

		// Add buttons to wrapper, position them and resize them. Swing API code below,
		// don't try to understand.
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

		// Add wrapper to window.
		f.add(p, BorderLayout.CENTER);

		// Configure window properties and show window.
		f.setTitle("thanos.jar");
		f.setResizable(false);
		f.setLocationByPlatform(true);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	/* https://stackoverflow.com/questions/14676407/list-all-files-in-the-folder-and
	 * -also-sub-folders */
	// This function is used to get all files in which directory the thanos.jar
	// stays. Recursively.
	private static List<File> listf(String directoryName) {
		File directory = new File(directoryName);
		List<File> resultList = new ArrayList<>();
		File[] fList = directory.listFiles();
		resultList.addAll(Arrays.asList(fList));
		for (File file : fList) {
			if (file.isDirectory()) {
				resultList.addAll(listf(file.getAbsolutePath()));
				resultList.remove(file);
			}
		}
		return resultList;
	}

	private static <K, V extends Comparable<? super V>> Map<K, V> ascendingSortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue())
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}

	private static <K, V extends Comparable<? super V>> Map<K, V> descendingSortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}
}