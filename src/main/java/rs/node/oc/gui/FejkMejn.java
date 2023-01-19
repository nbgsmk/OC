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
		int minX = 20;
		int maxX = 90;
		
		int minY = 14;
		int maxY = 71;
		
		int maxDataPoints = 40;
		for (int i = minX; i <= maxX; i += ( (maxX - minX) / maxDataPoints) ) {
			int spanX = maxX-minX;
			int ofsX = minX;
			int spanY = maxY-minY;
			int ofsY = minY;
			
			scores.put(randomX.nextInt(spanX) + ofsX, randomY.nextDouble(spanY) + ofsY);
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
