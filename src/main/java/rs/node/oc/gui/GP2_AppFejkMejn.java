package rs.node.oc.gui;



import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GP2_AppFejkMejn {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				kreni();
			}
		});
		
		
	}
	
	
	private static void kreni(){
		
		Map<Integer, Double> data = DemoData.getDemoData();
		System.out.println(data);
		
		GP2 mainPanel = new GP2(data);
		mainPanel.setPreferredSize(new Dimension(800, 600));
		int w = mainPanel.getWidth();
		int h = mainPanel.getHeight();
		
		JPanel xo = new JPanel();
		xo.setPreferredSize(new Dimension(w, 50));
		xo.setBackground(Color.PINK);

		JPanel yo = new JPanel();
		yo.setPreferredSize(new Dimension(50, h));
		yo.setBackground(Color.GREEN);
		
		// XP xo = new XP(data);
		// xo.setPreferredSize(new Dimension(w, 50));
		// xo.setBackground(Color.PINK);
		//
		// YP yo = new YP(data);
		// yo.setPreferredSize(new Dimension(50, h));
		// yo.setBackground(Color.GREEN);
		
		JFrame frame = new JFrame("GP2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		// frame.getContentPane().add(xo, BorderLayout.SOUTH);
		// frame.getContentPane().add(yo, BorderLayout.WEST);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
		

		

	}
}
