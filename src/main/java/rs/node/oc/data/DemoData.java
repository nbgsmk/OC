package rs.node.oc.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class DemoData {
	
	public static Map<Integer, Double> getDemoData(){
		Map<Integer, Double> tacke = new TreeMap<>();
		Random randomX = new Random();
		Random randomY = new Random();
		int minX = 70;
		int maxX = 150;
		
		int minY = 44;
		int maxY = 74;
		
		int maxDataPoints = 20;
		for (int i = minX; i <= maxX; i += ( (maxX - minX) / maxDataPoints) ) {
			int spanX = maxX-minX;
			int ofsX = minX;
			int spanY = maxY-minY;
			int ofsY = minY;
			
			tacke.put(randomX.nextInt(spanX) + ofsX, (double) (randomY.nextInt(spanY) + ofsY));
			// tacke.put(i, randomY.nextDouble() * maxScore);
		}
		
		// tacke = new TreeMap<>();
		// tacke.put(12, 10.0);
		// tacke.put(15, 22.0);
		// tacke.put(20, 11.0);
		// tacke.put(24, 20.0);
		// tacke.put(30, 10.0);
		return tacke;
	}
}
