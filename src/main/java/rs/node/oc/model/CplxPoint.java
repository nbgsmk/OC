package rs.node.oc.model;

import java.awt.*;

public class CplxPoint extends Point {
	
	private String strX;
	private String strY;
	
	public CplxPoint(Point p, String strX, String strY) {
		super(p);
		this.strX = strX;
		this.strY = strY;
	}
	
	public CplxPoint(int x, int y, String strX, String strY) {
		super(x, y);
		this.strX = strX;
		this.strY = strY;
	}
	
	public String getStrX() {
		return strX;
	}
	
	public String getStrY() {
		return strY;
	}
}
