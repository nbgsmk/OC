package rs.node.oc.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GP2 extends JPanel {
	
	private int width = 800;
	private int heigth = 400;
	private int padding = 25;
	private int labelPadding = 25;
	private Color lineColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(200, 200, 200, 200);
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int pointWidth = 4;
	private int numberYDivisions = 10;
	private Map<Integer, Double> scores;
	
	public GP2(Map<Integer, Double> scores) {
		this.scores = scores;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
		double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());
		
		List<Point> graphPoints = new ArrayList<>();
		// for (int i = 0; i < scores.size(); i++) {
		// 	int x1 = (int) (i * xScale + padding + labelPadding);
		// 	int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
		// 	graphPoints.add(new Point(x1, y1));
		// }
		
		for (Map.Entry<Integer, Double> score : scores.entrySet()) {
			int x1 = (int) (score.getKey() * xScale + padding + labelPadding);
			int y1 = (int) ((getMaxScore() - score.getValue()) * yScale + padding);
			graphPoints.add(new Point(x1, y1));
		}
		
		// draw white background
		g2.setColor(Color.WHITE);
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
		g2.setColor(Color.BLACK);
		
		// create hatch marks and grid lines for y axis.
		for (int i = 0; i < numberYDivisions + 1; i++) {
			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
			int y1 = y0;
			if (scores.size() > 0) {
				g2.setColor(gridColor);
				g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
				g2.setColor(Color.BLACK);
				String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
			}
			g2.drawLine(x0, y0, x1, y1);
		}
		
		// and for x axis
		for (int i = 0; i < scores.size(); i++) {
			if (scores.size() > 1) {
				int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
				int x1 = x0;
				int y0 = getHeight() - padding - labelPadding;
				int y1 = y0 - pointWidth;
				if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
					g2.setColor(gridColor);
					g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
					g2.setColor(Color.BLACK);
					String xLabel = i + "";
					FontMetrics metrics = g2.getFontMetrics();
					int labelWidth = metrics.stringWidth(xLabel);
					g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
				}
				g2.drawLine(x0, y0, x1, y1);
			}
		}
		
		// create x and y axes
		g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
		g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
		
		Stroke oldStroke = g2.getStroke();
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}
		
		g2.setStroke(oldStroke);
		g2.setColor(pointColor);
		for (int i = 0; i < graphPoints.size(); i++) {
			int x = graphPoints.get(i).x - pointWidth / 2;
			int y = graphPoints.get(i).y - pointWidth / 2;
			int ovalW = pointWidth;
			int ovalH = pointWidth;
			g2.fillOval(x, y, ovalW, ovalH);
			g2.drawString((i + 1) + ", (" + x + ", " + y + ")", x , y);
		}
		
		g2.drawString("x: " + getMinX() + " - " + getMaxX() + ", " + scores.size() + " ", 10, 10);
		g2.drawString("y: " + getMinScore() + " - " + getMaxScore() + ", " + " ", 10, 20);
	}
	

	private double getMinScore() {
		double minScore = Double.MAX_VALUE;
		for (Map.Entry<Integer, Double> score : scores.entrySet()){
			minScore = Math.min(minScore, score.getValue());
		}
		return minScore;
	}
	
	private double getMaxScore() {
		double maxScore = Double.MIN_VALUE;
		for (Map.Entry<Integer, Double> score : scores.entrySet()){
			maxScore = Math.max(maxScore, score.getValue());
		}
		return maxScore;
	}
	
	private int getMinX(){
		int minX = Integer.MAX_VALUE;
		for (Integer x : scores.keySet()){
			minX = Math.min(minX, x);
		}
		return minX;
	}
	
	
	private int getMaxX(){
		int maX = Integer.MIN_VALUE;
		for (Integer x : scores.keySet()){
			maX = Math.max(maX, x);
		}
		return maX;
	}
	
	
	public void setScores(TreeMap<Integer, Double> scores) {
		this.scores = scores;
		invalidate();
		this.repaint();
	}
	
	public Map<Integer, Double> getScores() {
		return scores;
	}
	

	

}