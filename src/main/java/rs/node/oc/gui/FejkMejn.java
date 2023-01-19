package rs.node.oc.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FejkMejn {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				kreni();
			}
		});
		
		
	}
	
	
	private static void kreni(){
		Map<Integer, Double> scores = new TreeMap<>();
		Random randomX = new Random();
		Random randomY = new Random();
		int maxX = 20;
		int offsetX = 0;
		int maxScore = 7;
		int maxDataPoints = 40;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.put(randomX.nextInt(maxX) + offsetX, randomY.nextDouble() * maxScore);
			// scores.put(i, randomY.nextDouble() * maxScore);
		}
		GP2 mainPanel = new GP2(scores);
		mainPanel.setPreferredSize(new Dimension(800, 600));
		
		
		
		JFrame frame = new JFrame("DrawGraph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
